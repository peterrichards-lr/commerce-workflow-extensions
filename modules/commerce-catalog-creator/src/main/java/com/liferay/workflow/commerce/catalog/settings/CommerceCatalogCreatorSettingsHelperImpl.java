package com.liferay.workflow.commerce.catalog.settings;

import com.liferay.workflow.commerce.catalog.configuration.CommerceCatalogCreatorConfiguration;
import com.liferay.workflow.commerce.catalog.configuration.CommerceCatalogCreatorConfigurationWrapper;
import com.liferay.workflow.extensions.common.settings.BaseSettingsHelper;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

public class CommerceCatalogCreatorSettingsHelperImpl extends BaseSettingsHelper<CommerceCatalogCreatorConfiguration, CommerceCatalogCreatorConfigurationWrapper> implements CommerceCatalogCreatorSettingsHelper {
    @Reference(
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            policyOption = ReferencePolicyOption.GREEDY
    )
    protected void addCommerceCatalogCreatorConfigurationWrapper(
            final CommerceCatalogCreatorConfigurationWrapper
                    configurationWrapper) {
        _log.debug("Adding a Commerce Catalog creator configuration\n[{}]", configurationWrapper.toString());
        super.addConfigurationWrapper(configurationWrapper);
    }

    @SuppressWarnings("unused")
    protected void removeCommerceCatalogCreatorConfigurationWrapper(
            final CommerceCatalogCreatorConfigurationWrapper
                    configurationWrapper) {
        _log.debug("Removing a Commerce Catalog creator configuration\n[{}]", configurationWrapper.toString());
        super.removeConfigurationWrapper(configurationWrapper);
    }
}
