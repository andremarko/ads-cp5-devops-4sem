#!/bin/bash

export RESOURCE_GROUP="rg-cashflow"
export SERVER_NAME="cashflow-sqlserver"
export DB_ADMIN="cashflow-admin"
export DB_NAME="cashflow-backend-entities"
export DB_PASSWORD="FluxoCaixa@2025"

export WEBAPP_NAME="cashflow-api-restful"
export APP_SERVICE_PLAN="planCashFlow"

export APP_INSIGHTS_NAME="ai-cashflow"

export LOCATION="westus"
export RUNTIME="JAVA:21-java21"
export GITHUB_REPO="andremarko/ads-cp5-devops-4sem"
export BRANCH="main"

export MY_PUBLIC_IP=$(curl -s ifconfig.me)

export JDBC_CONNECTION_STRING=$(az sql db show-connection-string --client jdbc --name $DB_NAME --server $SERVER_NAME --output tsv)
