package com.liferay.workflow.commerce.catalog.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.workflow.extensions.common.configuration.BaseEntityCreatorActionExecutorConfigurationWrapper;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import java.util.Map;
import java.util.stream.Collectors;

@Component(
        configurationPid = CommerceCatalogCreatorConfiguration.PID,
        immediate = true, service = CommerceCatalogCreatorConfigurationWrapper.class
)
public class CommerceCatalogCreatorConfigurationWrapper extends BaseEntityCreatorActionExecutorConfigurationWrapper<CommerceCatalogCreatorConfiguration> {

    @Activate
    @Modified
    protected void activate(final Map<String, Object> properties) {
        _log.trace("Activating {} : {}", getClass().getSimpleName(), properties.keySet().stream().map(key -> key + "=" + properties.get(key).toString()).collect(Collectors.joining(", ", "{", "}")));
        final CommerceCatalogCreatorConfiguration configuration = ConfigurableUtil.createConfigurable(
                CommerceCatalogCreatorConfiguration.class, properties);
        super.setConfiguration(configuration);
    }
}
