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
        Map<String, String> inputParams = input.getQueryStringParameters();
        String title = inputParams != null ? inputParams.get("title") : null;

        LambdaLogger logger = context.getLogger();
        logger.log("CONTEXT: " + gson.toJson(context));

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        List<TutorialDynamoDB> tutorials = tutorialDynamoDBRepository.getTutorialByTitel(title);
        String body = gson.toJson(tutorials);
        logger.log("Body: " + body);

        // TODO having 204, the response body is not propagated
//        return response.withStatusCode(tutorials.isEmpty() ? 204 : 200).withBody(body);
        return response.withStatusCode(200).withBody(body);
    }

}
