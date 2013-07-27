package com.example.util;

import java.util.List;

public class ApexUtil {

	public enum classTypes
	{
		BASE, TEST, BATCH, SCHED, EMAIL, EXCEPTION, EMPTY;
	};
	
	public static String triggerStub(String name, String sObject, List<String> operations, Boolean createHandler)
	{
		StringBuilder triggerBuilder = new StringBuilder();
		triggerBuilder.append("trigger ").append(name).append(" on ").append(sObject).append("(");
		for (String operation : operations)
		{
			triggerBuilder.append(operation).append(",");
		}
		triggerBuilder.deleteCharAt(triggerBuilder.length()-1);
		triggerBuilder.append(") {").append("\n");
		
	/*	if (createHandler)
			triggerBuilder.append(name).append("TriggerHandler handler = new ").append(name).append("TriggerHandler(Trigger.isExecuting, Trigger.size);").append("\n");
		
		for (String operation : operations)
		{
			String[] splitOperation = operation.split("\\s+");
			triggerBuilder.append("\t").append("if ((Trigger.is" + splitOperation[0].substring(0, 1).toUpperCase() + splitOperation[0].substring(1) + ")").append(" && ").append("(Trigger.is" + splitOperation[1].substring(0, 1).toUpperCase() + splitOperation[1].substring(1) + ")").append(") {").append("\n");
			
			if (operation.equalsIgnoreCase("before insert") && createHandler)
				triggerBuilder.append("handler.OnBeforeInsert(Trigger.new);").append("\n");
			else if (operation.equalsIgnoreCase("after insert") && createHandler)
				triggerBuilder.append("handler.OnAfterInsert(Trigger.new);").append("\n");
			else if (operation.equalsIgnoreCase("before update") && createHandler)
				triggerBuilder.append("handler.OnBeforeUpdate(Trigger.old, Trigger.new, Trigger.newMap);").append("\n");
			else if (operation.equalsIgnoreCase("after update") && createHandler)
				triggerBuilder.append("handler.OnAfterUpdate(Trigger.old, Trigger.new, Trigger.newMap);").append("\n");
			else if (operation.equalsIgnoreCase("before delete") && createHandler)
				triggerBuilder.append("handler.OnBeforeDelete(Trigger.old, Trigger.oldMap);").append("\n");
			else if (operation.equalsIgnoreCase("after delete") && createHandler)
				triggerBuilder.append("handler.OnAfterDelete(Trigger.old, Trigger.oldMap);").append("\n");
			else if (operation.equalsIgnoreCase("after undelete") && createHandler)
				triggerBuilder.append("handler.OnUndelete(Trigger.new);").append("\n");
			
			triggerBuilder.append("}").append("\n");
		}
		 */
		triggerBuilder.append("}");
		
		return triggerBuilder.toString();
	}
	
	public static String classStub(String name, String type)
	{
		StringBuilder classBuilder = new StringBuilder();
		switch (classTypes.valueOf(type)) {
		case BASE:
			classBuilder.append("public class with sharing ").append(name).append("{").append("\n");
			classBuilder.append("public ").append(name).append("()").append("{}").append("\n");
			classBuilder.append("}");
			break;

		case TEST:
			classBuilder.append("@isTest").append("\n");
			classBuilder.append("public class ").append(name).append("{");
			classBuilder.append("@isTest static void Test1 {").append("\n").append("Test.startTest()").append("\n").append("Test.stopTest()").append("\n");
			classBuilder.append("}");
			break;
		
		case BATCH:
			classBuilder.append("global class ").append(name).append(" implements Database.Batchable<sObject>").append("{").append("\n");
			classBuilder.append("String query;").append("\n");
			classBuilder.append("global BatchTemplate() {").append("\n");
			classBuilder.append("}").append("\n");
			classBuilder.append("global Database.QueryLocator start(Database.BatchableContext BC) {").append("\n");
			classBuilder.append("return Database.getQueryLocator(query);").append("\n");
			classBuilder.append("}").append("\n");
		   	classBuilder.append("global void execute(Database.BatchableContext BC, List<sObject> scope) {").append("\n");
			classBuilder.append("}").append("\n");
			classBuilder.append("global void finish(Database.BatchableContext BC) {").append("\n");
			classBuilder.append("}").append("\n");
			classBuilder.append("}");
			break;
			
		case SCHED:
			classBuilder.append("global class ").append(name).append(" implements Schedulable {").append("\n");
			classBuilder.append("global void execute(SchedulableContext SC) {").append("\n");
			classBuilder.append("}").append("\n");
			classBuilder.append("}");
			break;
		
		case EMAIL:
			classBuilder.append("global class ").append(name).append(" implements Messaging.InboundEmailHandler {").append("\n");
			classBuilder.append("global Messaging.InboundEmailResult handleInboundEmail(Messaging.inboundEmail email,Messaging.InboundEnvelope env) {").append("\n");
			classBuilder.append("Messaging.InboundEmailResult result = new Messaging.InboundEmailResult();").append("\n");
			classBuilder.append("String myPlainText= '';").append("\n");
			classBuilder.append("myPlainText = email.plainTextBody;").append("\n");
			classBuilder.append("result.success = true;").append("\n");
			classBuilder.append("return result;").append("\n");
			classBuilder.append("}").append("\n");
			classBuilder.append("}");
			break;
			
		case EXCEPTION:
			classBuilder.append("public class ").append(name).append(" extends Exception{").append("\n \n");
			classBuilder.append("}");
			break;
		case EMPTY:
			classBuilder.append("public class ").append(name).append(" {").append("\n \n").append("}");
			break;
			
		}
		return classBuilder.toString();
	}
	
	public String componentStub(String name)
	{
		StringBuilder componentBuilder = new StringBuilder();
		componentBuilder.append("<apex:component>").append("\n").append("<apex:component>");
		return componentBuilder.toString();
	}
	
	public static String triggerHandler(String name, String sObject, List<String> operations)
	{
		StringBuilder triggerHandlerBuilder = new StringBuilder();
		triggerHandlerBuilder.append("public with sharing class ").append(name).append("TriggerHandler").append(" {").append("\n");
		triggerHandlerBuilder.append("\t").append("private boolean m_isExecuting = false;").append("\n");
		triggerHandlerBuilder.append("\t").append("private Integer BatchSize = 0;").append("\n\n");
		triggerHandlerBuilder.append("\t").append("public ").append(name).append("TriggerHandler").append("(boolean isExecuting, Integer size){").append("\n");
		triggerHandlerBuilder.append("\t\t").append("m_isExecuting = isExecuting;").append("\n");
		triggerHandlerBuilder.append("\t\t").append("BatchSize = size;").append("\n");
		triggerHandlerBuilder.append("\t").append("}").append("\n");
		
		for (String operation : operations)
		{
				if (operation.equalsIgnoreCase("before insert"))
			{
				triggerHandlerBuilder.append("\t").append("public void OnBeforeInsert(List<").append(sObject).append(">").append(" new").append(sObject).append(") {").append("\n");
				triggerHandlerBuilder.append("\t").append("}").append("\n");
			}
			else if (operation.equalsIgnoreCase("after insert"))
			{
				triggerHandlerBuilder.append("\t").append("public void OnAfterInsert(").append(sObject).append("[]").append(" new").append(sObject).append(") {").append("\n\n");
				triggerHandlerBuilder.append("\t").append("}").append("\n");
			}
			else if (operation.equalsIgnoreCase("before update"))
			{
				triggerHandlerBuilder.append("\t").append("public void OnBeforeUpdate(").append(sObject).append("[]").append(" old").append(sObject).append(",").append(sObject).append("[]").append(" updated").append(sObject).append(",").append("Map<ID,").append(sObject).append("> ").append(sObject).append("Map").append(")").append(" {").append("\n\n");
				triggerHandlerBuilder.append("\t").append("}").append("\n");
			}
			else if(operation.equalsIgnoreCase("after update"))
			{
				triggerHandlerBuilder.append("\t").append("public void OnAfterUpdate(").append(sObject).append("[]").append(" old").append(sObject).append(",").append(sObject).append("[]").append(" updated").append(sObject).append(",").append("Map<ID,").append(sObject).append("> ").append(sObject).append("Map").append(")").append(" {").append("\n\n");
				triggerHandlerBuilder.append("\t").append("}").append("\n");
			}
			else if (operation.equalsIgnoreCase("before delete"))
			{
				triggerHandlerBuilder.append("\t").append("public void OnBeforeDelete(").append(sObject).append("[]").append(sObject).append("ToDelete").append(",").append("Map<ID,").append(sObject).append("> ").append(sObject).append("Map").append(")").append(" {").append("\n\n");
				triggerHandlerBuilder.append("\t").append("}").append("\n");
			}
			else if (operation.equalsIgnoreCase("after delete"))
			{
				triggerHandlerBuilder.append("\t").append("public void OnAfterDelete(").append(sObject).append("[]").append(" deleted").append(sObject).append(",").append("Map<ID,").append(sObject).append("> ").append(sObject).append("Map").append(")").append(" {").append("\n\n");
				triggerHandlerBuilder.append("\t").append("}").append("\n");
			}
			else if (operation.equalsIgnoreCase("after undelete"))
			{
				triggerHandlerBuilder.append("\t").append("public void OnUndelete(").append(sObject).append("[]").append("restored").append(sObject).append(") {").append("\n\n");
				triggerHandlerBuilder.append("\t").append("}").append("\n");
			}
		}
		
		triggerHandlerBuilder.append("\t").append("public boolean IsTriggerContext{").append("\n");
		triggerHandlerBuilder.append("\t\t").append("get{ return m_isExecuting;}").append("\n");
		triggerHandlerBuilder.append("\t").append("}").append("\n");
		triggerHandlerBuilder.append("\t").append("public boolean IsVisualforcePageContext{").append("\n");
		triggerHandlerBuilder.append("\t\t").append("get{ return !IsTriggerContext;}").append("\n");
		triggerHandlerBuilder.append("\t").append("}").append("\n");
		triggerHandlerBuilder.append("\t").append("public boolean IsWebServiceContext{").append("\n");
		triggerHandlerBuilder.append("\t\t").append("get{ return !IsTriggerContext;}").append("\n");
		triggerHandlerBuilder.append("\t").append("}").append("\n");
		triggerHandlerBuilder.append("\t").append("public boolean IsExecuteAnonymousContext{").append("\n");
		triggerHandlerBuilder.append("\t\t").append("get{ return !IsTriggerContext;}").append("\n");
		triggerHandlerBuilder.append("\t").append("}").append("\n");
		
		triggerHandlerBuilder.append("}");
		
		return triggerHandlerBuilder.toString();
	}

}
