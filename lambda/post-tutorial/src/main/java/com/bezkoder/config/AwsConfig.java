package com.bezkoder.config;

import lombok.Getter;

import static com.amazonaws.regions.Regions.EU_CENTRAL_1;

@Getter
public class AwsConfig {

    private String region = System.getenv().getOrDefault("REGION", EU_CENTRAL_1.getName());
    private DynamoDB dynamoDB = new DynamoDB();

    @Getter
    public static class DynamoDB {
        private String endpoint = System.getenv().getOrDefault("DYNAMODB_ENDPOINT", "dynamodb.eu-central-1.amazonaws.com");
        private String tableName = System.getenv("DYNAMODB_TABLE");
    }

}
