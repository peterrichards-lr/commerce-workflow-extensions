package com.liferay.workflow.commerce.catalog.constants;

public class CommerceCatalogCreatorConstants {
    public static final Object CATALOG_DEFAULT_LANGUAGE_ID = "en_US";
    public static final Object COMMERCE_CURRENCY_CODE = "USD";
    public static final String CONFIG_CREATED_ENTITY_IDENTIFIER_WORKFLOW_CONTEXT_KEY_DEFAULT = "newCommerceCatalogId";
    public static final String CONFIG_ENTITY_CREATION_ATTRIBUTES_DEFAULT = "{\"entityAttributeName\":\"name\"\\,\"useWorkflowContextKey\":true\\,\"workflowContextKey\":\"catalogName\"\\,\"defaultValue\":\"\"}|{\"entityAttributeName\":\"external-reference-code\"\\,\"useWorkflowContextKey\":true\\,\"workflowContextKey\":\"catalogErc\"\\,\"defaultValue\":\"\"}|{\"entityAttributeName\":\"commerce-currency-code\"\\,\"useWorkflowContextKey\":false\\,\"workflowContextKey\":\"catalogCurrency\"\\,\"defaultValue\":\"USD\"}|{\"entityAttributeName\":\"catalog-default-language-identifier\"\\,\"useWorkflowContextKey\":false\\,\"workflowContextKey\":\"catalogLanguage\"\\,\"defaultValue\":\"en_US\"}|{\"entityAttributeName\":\"system-catalog\"\\,\"useWorkflowContextKey\":false\\,\"workflowContextKey\":\"systemCatalog\"\\,\"defaultValue\":false}|{\"entityAttributeName\":\"account-entry-identifier\"\\,\"useWorkflowContextKey\":false\\,\"workflowContextKey\":\"newAccountEntryId\"\\,\"defaultValue\":0}";
    public static final String CONFIG_RETURN_EXISTING_ENTITY_IDENTIFIER_IF_FOUND_DEFAULT = "true";
    public static final String METHOD_PARAM_ACCOUNT_ENTRY_ID = "account-entry-identifier";
    public static final String METHOD_PARAM_CATALOG_DEFAULT_LANGUAGE_ID = "catalog-default-language-identifier";
    public static final String METHOD_PARAM_COMMERCE_CURRENCY_CODE = "commerce-currency-code";
    public static final String METHOD_PARAM_EXTERNAL_REFERENCE_CODE = "external-reference-code";
    public static final String METHOD_PARAM_NAME = "name";
    public static final String METHOD_PARAM_SYSTEM_CATALOG = "system-catalog";
    public static final boolean SYSTEM_CATALOG = false;
}
