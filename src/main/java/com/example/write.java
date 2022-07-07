package com.example;

import com.example.model.Page;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.CosmosDBOutput;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;


public class write {
    @FunctionName("Write")
    @CosmosDBOutput(name = "database",
            databaseName = "pagesubscription",
            collectionName = "mycollection",
            connectionStringSetting = "AzureCosmosDBConnection")
    public String run(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
                    HttpRequestMessage<Optional<Page>> request,
            final ExecutionContext context) {


        context.getLogger().info("Parameters are: " + request.getQueryParameters());


        String query = request.getQueryParameters().get("desc");

        if(request.getBody().isPresent()) {
            Page page = request.getBody().get();


            final String jsonDocument = "{\"name\":\"" + page.getName() + "\", " +
                    "\"link\": \"" + page.getLink() + "\"}";

            context.getLogger().info("Document to be saved: " + jsonDocument);

            return jsonDocument;
        }
        return null;
    }
}
