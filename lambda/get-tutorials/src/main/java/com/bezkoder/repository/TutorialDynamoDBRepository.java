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


	public List<TutorialDynamoDB> getTutorialByTitel(String title) {

		PaginatedScanList<TutorialDynamoDB> tutorialList;
		if (title == null || title == "") {
			tutorialList = dynamoDBMapper.scan(TutorialDynamoDB.class, new DynamoDBScanExpression(), this.dynamoDBMapperConfig);
		} else {
			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
			scanExpression.addFilterCondition("title",
					new Condition().withComparisonOperator(ComparisonOperator.CONTAINS)
							.withAttributeValueList(new AttributeValue().withS(title)));
			tutorialList = dynamoDBMapper.scan(TutorialDynamoDB.class, scanExpression, this.dynamoDBMapperConfig);
		}

		tutorialList.loadAllResults();
		List<TutorialDynamoDB> list = new ArrayList<TutorialDynamoDB>(tutorialList.size());

		Iterator<TutorialDynamoDB> iterator = tutorialList.iterator();
		while (iterator.hasNext()) {
			TutorialDynamoDB element = iterator.next();
			list.add(element);
		}

		return list;

	}

}
