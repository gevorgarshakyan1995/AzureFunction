package com.example;

import com.example.model.Page;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.CosmosDBInput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

public class read {
    @FunctionName("ReadAll")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "Read/All")
                    HttpRequestMessage<Optional<String>> request,
            @CosmosDBInput(name = "database",
                    databaseName = "pagesubscription",
                    collectionName = "mycollection",
                    sqlQuery = "SELECT * FROM c ",
                    connectionStringSetting = "AzureCosmosDBConnection")
                    Page[] item,
            final ExecutionContext context) {


        context.getLogger().info("Parameters are: " + request.getQueryParameters());
        context.getLogger().info("Items from the database are " + item);


        if (item == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Document not found.")
                    .build();
        }
        else {
            return request.createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(item)
                    .build();
        }
    }
}


