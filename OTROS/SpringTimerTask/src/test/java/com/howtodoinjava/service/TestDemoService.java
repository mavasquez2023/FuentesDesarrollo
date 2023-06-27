package com.howtodoinjava.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemoService {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("schedule-basicUsageCron-example.xml");
		new ClassPathXmlApplicationContext("schedule-basicUsageFixedDelay-example.xml");
		new ClassPathXmlApplicationContext("schedule-xmlConfig-example.xml");
		new ClassPathXmlApplicationContext("schedule-PropertiesConfig-example.xml");
	}	
}
