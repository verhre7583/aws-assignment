package com.bezkoder.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.bezkoder.config.DynamoDBConfiguration;
import com.bezkoder.model.TutorialDynamoDB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TutorialDynamoDBRepository {

	private DynamoDBMapper dynamoDBMapper;

	private DynamoDB dynamoDB;

	// to provide dynamic tablename
	private DynamoDBMapperConfig dynamoDBMapperConfig;

	public TutorialDynamoDBRepository() {
		DynamoDBConfiguration dynamoDBConfiguration = new DynamoDBConfiguration();
		AmazonDynamoDB amazonDynamoDB = dynamoDBConfiguration.buildAmazonDynamoDB();
		dynamoDB = new DynamoDB(amazonDynamoDB);
		this.dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		this.dynamoDBMapperConfig = new DynamoDBMapperConfig(new DynamoDBMapperConfig.TableNameOverride(dynamoDBConfiguration.getAwsConfig().getDynamoDB().getTableName()));
	}


	public void delete(String tutorialId) {
		TutorialDynamoDB tutorial = dynamoDBMapper.load(TutorialDynamoDB.class, tutorialId, this.dynamoDBMapperConfig);
		dynamoDBMapper.delete(tutorial, this.dynamoDBMapperConfig);
	}
}
