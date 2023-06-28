package com.bezkoder;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.bezkoder.model.TutorialDynamoDB;
import com.bezkoder.repository.TutorialDynamoDBRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    static TutorialDynamoDBRepository tutorialDynamoDBRepository = new TutorialDynamoDBRepository();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        String requestBody = input.getBody();
        TutorialDynamoDB tutorial = gson.fromJson(requestBody, TutorialDynamoDB.class);

        Map<String, String> inputParams = input.getPathParameters();
        String id = inputParams != null ? inputParams.get("id") : null;

        LambdaLogger logger = context.getLogger();
        logger.log("CONTEXT: " + gson.toJson(context));

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        tutorial = tutorialDynamoDBRepository
                .save(new TutorialDynamoDB(tutorial.getTitle(), tutorial.getDescription(), false));

        String body = gson.toJson(tutorial);
        logger.log("Body: " + body);
        return response.withStatusCode(201).withBody(body);
    }

}
