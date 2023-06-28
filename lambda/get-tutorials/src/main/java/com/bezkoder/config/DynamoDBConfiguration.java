package com.bezkoder.config;


import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import lombok.Getter;

@Getter
public class DynamoDBConfiguration {

	private AwsConfig awsConfig;

	public DynamoDBConfiguration() {
		this.awsConfig = new AwsConfig();
	}

	public AmazonDynamoDB buildAmazonDynamoDB() {
		String endpoint = awsConfig.getDynamoDB().getEndpoint();
		String region = awsConfig.getRegion();

		AwsClientBuilder.EndpointConfiguration config = new AwsClientBuilder.EndpointConfiguration(endpoint, region);

		AmazonDynamoDBClientBuilder clientBuilder = AmazonDynamoDBClientBuilder.standard();
		clientBuilder.setEndpointConfiguration(config);
		AmazonDynamoDB client = clientBuilder.build();
		return client;
	}

}
