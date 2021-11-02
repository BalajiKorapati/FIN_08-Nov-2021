#####################################################
#Script Name: AP005_CreatePayment
#Description/Action: Create Payment and Create Positive Pay Files
#Manual Test Case used: AP005 - Create Payments
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 24-02-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location: 
#Data Bank parameters:
#Feature File Name/s used: 
#Unique Data Required or Not: Yes
#Pre-Requisites (if Any):An Invoice must be available to Create a Payment
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Reinstatement Of Asset 

Background: 
	Given Setup Reporting for Finance 
	
@AR002.2 
Scenario Outline: AR002.2_Copy Transaction 
	Given User logs into Fusion application 
	When User Navigates to Billing page 
	Then Click on Manage Transactions link 
	Then Perform Search for Transaction with "<Transaction_Source>","<Transaction_Number>","<Transaction_Date>","<Bill_to_Customer>" 
	And click action and select duplicate 
	And click on Complete and Review "<New_Transaction_Source>"
	Then User logs out of Fusion application
	
	Examples: 
		|Transaction_Source|Transaction_Number|Transaction_Date|Bill_to_Customer|New_Transaction_Source|
		|Receivables Import|120050            |5/7/20          |Business World  |Bills Receivable|