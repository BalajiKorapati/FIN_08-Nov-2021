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
	
@FA002.8 
Scenario Outline: FA002.8_ReinstatementOfAsset 
	Given User logs into Fusion application 
	When User Navigates to Reinstate Assets page 
	Then Perform Search for an Asset with "<AssetNumber>" 
	And Select Row in Asset Results 
	And Click on Reinstate button 
	Then Click on Warning Yes button 
	Then Click on Done button 
	And User navigates to Asset Inquiry page 
	Then Perform Search for an Asset Inquiry with "<BookName>","<AssetNumber>" 
	And User navigates to Transactions "<Transaction_Type>" 
	Then User logs out of Fusion application
	
	Examples: 
		|AssetNumber| Transaction_Type|BookName|
		|TESTASSET0001|  Reinstatement| US CORP|