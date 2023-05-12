package com.liferay.workflow.commerce.catalog.configuration.persistance;

import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListener;
import com.liferay.workflow.commerce.catalog.configuration.CommerceCatalogCreatorConfiguration;
import com.liferay.workflow.extensions.common.configuration.persistence.listener.BaseConfigurationModelListener;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = "model.class.name=" + CommerceCatalogCreatorConfiguration.PID,
        service = ConfigurationModelListener.class
)
public class CommerceCatalogCreatorConfigurationModelListener extends BaseConfigurationModelListener<CommerceCatalogCreatorConfiguration> {
    @Reference
    private ConfigurationAdmin _configurationAdmin;

    @Override
    protected ConfigurationAdmin getConfigurationAdmin() {
        return _configurationAdmin;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected Class getConfigurationClass() {
        return CommerceCatalogCreatorConfiguration.class;
    }
}
