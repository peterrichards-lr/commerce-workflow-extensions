package com.liferay.workflow.commerce.catalog.action.executor;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.workflow.WorkflowStatusManager;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutorException;
import com.liferay.workflow.commerce.catalog.configuration.CommerceCatalogCreatorConfiguration;
import com.liferay.workflow.commerce.catalog.configuration.CommerceCatalogCreatorConfigurationWrapper;
import com.liferay.workflow.commerce.catalog.settings.CommerceCatalogCreatorSettingsHelper;
import com.liferay.workflow.extensions.common.action.executor.BaseWorkflowEntityCreatorActionExecutor;
import com.liferay.workflow.extensions.common.context.WorkflowActionExecutionContext;
import com.liferay.workflow.extensions.common.context.service.WorkflowActionExecutionContextService;
import org.osgi.service.component.annotations.Reference;

public class CommerceCatalogCreator extends BaseWorkflowEntityCreatorActionExecutor<CommerceCatalogCreatorConfiguration, CommerceCatalogCreatorConfigurationWrapper, CommerceCatalogCreatorSettingsHelper> implements ActionExecutor {
    @Reference
    private CommerceCatalogCreatorSettingsHelper _commerceCatalogCreatorSettingsHelper;
    @Reference
    private UserLocalService _userLocalService;
    @Reference
    private WorkflowActionExecutionContextService _workflowActionExecutionContextService;
    @Reference
    private WorkflowStatusManager _workflowStatusManager;

    @Override
    protected void execute(KaleoAction kaleoAction, ExecutionContext executionContext, WorkflowActionExecutionContext workflowActionExecutionContext, CommerceCatalogCreatorConfigurationWrapper commerceCatalogCreatorConfigurationWrapper, User user) throws ActionExecutorException {
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