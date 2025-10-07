#!/bin/bash

source ./env-config.sh

az monitor app-insights component create --app $APP_INSIGHTS_NAME --location $LOCATION --resource-group $RESOURCE_GROUP --application-type web

export APP_INSIGHTS_CONNECTION_STRING=$(az monitor app-insights component show --app "$APP_INSIGHTS_NAME" --resource-group "$RESOURCE_GROUP_NAME" --query connectionString --output tsv)

az appservice plan create --name $APP_SERVICE_PLAN --resource-group $RESOURCE_GROUP --location $LOCATION --sku B1 --is-linux

az webapp create --name "$WEBAPP_NAME" --resource-group $RESOURCE_GROUP --plan $APP_SERVICE_PLAN --runtime $RUNTIME

az resource update --resource-group $RESOURCE_GROUP --namespace Microsoft.Web --resource-type basicPublishingCredentialsPolicies --name scm --parent sites/$WEBAPP_NAME --set properties.allow=true

# Configurações App Insights e Variáveis de Ambiente
az webapp config appsettings set \
  --name $WEBAPP_NAME \
  --resource-group $RESOURCE_GROUP \
  --settings \
  APPLICATIONINSIGHTS_CONNECTION_STRING="$CONNECTION_STRING" \
  ApplicationInsightsAgent_EXTENSION_VERSION="~3" \
  XDT_MicrosoftApplicationInsights_Mode="Recommended" \
  XDT_MicrosoftApplicationInsights_PreemptSdk="1" \
  DB_ADMIN=$DB_ADMIN \
  DB_PASSWORD=$DB_PASSWORD \
  JDBC_CONNECTION_STRING="$JDBC_CONNECTION_STRING"

az webapp restart --name $WEBAPP_NAME --resource-group $RESOURCE_GROUP

# Criar a conexão do nosso Web App com o Application Insights
az monitor app-insights component connect-webapp \
  --app "$APP_INSIGHTS_NAME" \
  --web-app "$WEBAPP_NAME" \
  --resource-group "$RESOURCE_GROUP_NAME"

az webapp deployment github-actions add --name $WEBAPP_NAME --resource-group $RESOURCE_GROUP --repo $GITHUB_REPO --branch $BRANCH --login-with-github

