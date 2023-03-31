package com.liferay.workflow.commerce.catalog.action.executor;

import com.liferay.portal.kernel.workflow.WorkflowStatusManager;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutorException;
import com.liferay.workflow.commerce.catalog.configuration.CommerceCatalogCreatorConfiguration;
import com.liferay.workflow.commerce.catalog.configuration.CommerceCatalogCreatorConfigurationWrapper;
import com.liferay.workflow.commerce.catalog.settings.CommerceCatalogCreatorSettingsHelper;
import com.liferay.workflow.extensions.common.action.executor.BaseWorkflowActionExecutor;
import com.liferay.workflow.extensions.common.context.WorkflowActionExecutionContext;
import com.liferay.workflow.extensions.common.context.service.WorkflowActionExecutionContextService;
import org.osgi.service.component.annotations.Reference;

public class CommerceCatalogCreator extends BaseWorkflowActionExecutor<CommerceCatalogCreatorConfiguration, CommerceCatalogCreatorConfigurationWrapper, CommerceCatalogCreatorSettingsHelper> implements ActionExecutor {
    @Reference
    private CommerceCatalogCreatorSettingsHelper commerceCatalogCreatorSettingsHelper;

    @Reference
    private WorkflowActionExecutionContextService workflowActionExecutionContextService;

    @Reference
    private WorkflowStatusManager workflowStatusManager;

    @Override
    protected WorkflowActionExecutionContextService getWorkflowActionExecutionContextService() {
        return workflowActionExecutionContextService;
    }

    @Override
    protected void execute(KaleoAction kaleoAction, ExecutionContext executionContext, WorkflowActionExecutionContext workflowActionExecutionContext, CommerceCatalogCreatorConfigurationWrapper commerceCatalogCreatorConfigurationWrapper) throws ActionExecutorException {

    }

    @Override
    public String[] getActionExecutorKeys() {
        return new String[0];
    }

    @Override
    protected CommerceCatalogCreatorSettingsHelper getSettingsHelper() {
        return commerceCatalogCreatorSettingsHelper;
    }
}