package com.liferay.workflow.commerce.catalog.action.executor;

import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowStatusManager;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutorException;
import com.liferay.workflow.commerce.catalog.configuration.CommerceCatalogCreatorConfiguration;
import com.liferay.workflow.commerce.catalog.configuration.CommerceCatalogCreatorConfigurationWrapper;
import com.liferay.workflow.commerce.catalog.constants.CommerceCatalogCreatorConstants;
import com.liferay.workflow.commerce.catalog.settings.CommerceCatalogCreatorSettingsHelper;
import com.liferay.workflow.extensions.common.action.executor.BaseWorkflowEntityCreatorActionExecutor;
import com.liferay.workflow.extensions.common.configuration.model.MethodParameterConfiguration;
import com.liferay.workflow.extensions.common.context.WorkflowActionExecutionContext;
import com.liferay.workflow.extensions.common.context.service.WorkflowActionExecutionContextService;
import com.liferay.workflow.extensions.common.util.WorkflowExtensionsUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author peterrichards
 */
@Component(property = "com.liferay.portal.workflow.kaleo.runtime.action.executor.language=java", service = ActionExecutor.class, configurationPid = CommerceCatalogCreatorConfiguration.PID)
public final class CommerceCatalogCreator extends BaseWorkflowEntityCreatorActionExecutor<CommerceCatalogCreatorConfiguration, CommerceCatalogCreatorConfigurationWrapper, CommerceCatalogCreatorSettingsHelper> implements ActionExecutor {
    @Reference
    private CommerceCatalogCreatorSettingsHelper _commerceCatalogCreatorSettingsHelper;
    @Reference
    private CommerceCatalogLocalService _commerceCatalogLocalService;
    @Reference
    private UserLocalService _userLocalService;
    @Reference
    private WorkflowActionExecutionContextService _workflowActionExecutionContextService;
    @Reference
    private WorkflowStatusManager _workflowStatusManager;

    private boolean createCommerceCatalog(final User creator, final Map<String, Serializable> workflowContext, final ServiceContext serviceContext, final CommerceCatalogCreatorConfigurationWrapper configuration) throws ActionExecutorException {
        final Map<String, Object> methodParameters = buildMethodParametersMap(workflowContext, serviceContext, configuration);
        final String externalReferenceCode = (String) methodParameters.get(CommerceCatalogCreatorConstants.METHOD_PARAM_EXTERNAL_REFERENCE_CODE);
        final String name = (String) methodParameters.get(CommerceCatalogCreatorConstants.METHOD_PARAM_NAME);
        final String commerceCurrencyCode = (String) methodParameters.get(CommerceCatalogCreatorConstants.METHOD_PARAM_COMMERCE_CURRENCY_CODE);
        final String catalogDefaultLanguageId = (String) methodParameters.get(CommerceCatalogCreatorConstants.METHOD_PARAM_CATALOG_DEFAULT_LANGUAGE_ID);
        final boolean system = (boolean) methodParameters.get(CommerceCatalogCreatorConstants.METHOD_PARAM_SYSTEM_CATALOG);
        try {
            CommerceCatalog commerceCatalog = configuration.getReturnExistingEntityIdentifierIfFound() ? fetchCommerceCatalog(name) : null;
            if (commerceCatalog == null) {
                serviceContext.setUserId(creator.getUserId());
                commerceCatalog = _commerceCatalogLocalService.addCommerceCatalog(externalReferenceCode, name, commerceCurrencyCode, catalogDefaultLanguageId, system, serviceContext);
                WorkflowExtensionsUtil.runIndexer(commerceCatalog, serviceContext);
                _log.debug("New Commerce catalog created");
            } else {
                _log.debug("Existing Commerce catalog returned");
            }
            if (commerceCatalog != null) {
                final String identifierWorkflowKey = configuration.getCreatedEntityIdentifierWorkflowContextKey();
                final long commerceCatalogId = commerceCatalog.getCommerceCatalogId();
                _log.debug("Returning commerce catalog identifier {} in {}", commerceCatalogId, identifierWorkflowKey);
                workflowContext.put(identifierWorkflowKey, commerceCatalogId);
                return true;
            }

            _log.warn("The addCommerceCatalog returned null");
            return false;
        } catch (final PortalException e) {
            _log.error("Unable to create commerce catalog entry", e);
            return false;
        }
    }

    @Override
    protected void execute(KaleoAction kaleoAction, ExecutionContext executionContext, WorkflowActionExecutionContext workflowActionExecutionContext, CommerceCatalogCreatorConfigurationWrapper configuration, User actionUser) throws ActionExecutorException {
        final Map<String, Serializable> workflowContext = executionContext.getWorkflowContext();
        try {
            final ServiceContext serviceContext = executionContext.getServiceContext();
            final boolean success = createCommerceCatalog(actionUser, workflowContext, serviceContext, configuration);
            if (configuration.isWorkflowStatusUpdatedOnSuccess() && success) {
                updateWorkflowStatus(configuration.getSuccessWorkflowStatus(), workflowContext);
            }
        } catch (final PortalException | RuntimeException e) {
            if (configuration == null) {
                throw new ActionExecutorException("Unable to determine if workflow status is updated on exception. Configuration is null");
            } else if (configuration.isWorkflowStatusUpdatedOnException()) {
                _log.error("Unexpected exception. See inner exception for details", e);
                try {
                    updateWorkflowStatus(configuration.getExceptionWorkflowStatus(), workflowContext);
                } catch (final WorkflowException ex) {
                    throw new ActionExecutorException("See inner exception", ex);
                }
            } else {
                _log.error("Unexpected exception. See inner exception for details", e);
            }
        }
    }

    private CommerceCatalog fetchCommerceCatalog(String name) {
        final DynamicQuery query = _commerceCatalogLocalService.dynamicQuery().add(RestrictionsFactoryUtil.eq("name", name));
        final List<CommerceCatalog> commerceCatalogList = _commerceCatalogLocalService.dynamicQuery(query);
        if (commerceCatalogList == null || commerceCatalogList.isEmpty()) {
            return null;
        } else if (commerceCatalogList.size() > 1) {
            _log.debug("Found more than one....");
        }
        return commerceCatalogList.get(0);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected Map<String, MethodParameterConfiguration> getEntityCreationAttributeMap() {
        return new HashMap<>() {{
            put(CommerceCatalogCreatorConstants.METHOD_PARAM_NAME, new MethodParameterConfiguration(CommerceCatalogCreatorConstants.METHOD_PARAM_NAME, String.class, true, null));
            put(CommerceCatalogCreatorConstants.METHOD_PARAM_EXTERNAL_REFERENCE_CODE, new MethodParameterConfiguration(CommerceCatalogCreatorConstants.METHOD_PARAM_EXTERNAL_REFERENCE_CODE, String.class, true, null));
            put(CommerceCatalogCreatorConstants.METHOD_PARAM_COMMERCE_CURRENCY_CODE, new MethodParameterConfiguration(CommerceCatalogCreatorConstants.METHOD_PARAM_COMMERCE_CURRENCY_CODE, String.class, false, CommerceCatalogCreatorConstants.COMMERCE_CURRENCY_CODE));
            put(CommerceCatalogCreatorConstants.METHOD_PARAM_CATALOG_DEFAULT_LANGUAGE_ID, new MethodParameterConfiguration(CommerceCatalogCreatorConstants.METHOD_PARAM_CATALOG_DEFAULT_LANGUAGE_ID, String.class, false, CommerceCatalogCreatorConstants.CATALOG_DEFAULT_LANGUAGE_ID));
            put(CommerceCatalogCreatorConstants.METHOD_PARAM_SYSTEM_CATALOG, new MethodParameterConfiguration(CommerceCatalogCreatorConstants.METHOD_PARAM_SYSTEM_CATALOG, Boolean.class, false, CommerceCatalogCreatorConstants.SYSTEM_CATALOG));
        }};
    }

    @Override
    protected CommerceCatalogCreatorSettingsHelper getSettingsHelper() {
        return _commerceCatalogCreatorSettingsHelper;
    }

    @Override
    protected UserLocalService getUserLocalService() {
        return _userLocalService;
    }

    @Override
    protected WorkflowActionExecutionContextService getWorkflowActionExecutionContextService() {
        return _workflowActionExecutionContextService;
    }

    @Override
    protected WorkflowStatusManager getWorkflowStatusManager() {
        return _workflowStatusManager;
    }
}
