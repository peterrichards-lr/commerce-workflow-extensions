package com.liferay.workflow.commerce.catalog.configuration;

import com.liferay.workflow.extensions.common.configuration.BaseActionExecutorConfigurationWrapper;
import org.osgi.service.component.annotations.Component;

@Component(
        configurationPid = CommerceCatalogCreatorConfiguration.PID,
        immediate = true, service = CommerceCatalogCreatorConfigurationWrapper.class
)
public class CommerceCatalogCreatorConfigurationWrapper extends BaseActionExecutorConfigurationWrapper<CommerceCatalogCreatorConfiguration> {
}
