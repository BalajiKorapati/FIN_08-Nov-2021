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
	
@AR001.1 
Scenario Outline: AR001.1_CreateExternalCustomer 
	Given User logs into Fusion application 
	When User Navigates to Billing page 
	Then Click on Create Customer link 
	Then Enter at Organization Information"<OrganizationName>","<AccountDescription>","<AccountType>","<AccountAddressSet>","<SiteName>" 
	Then Click on Actions and Add row "<Purpose1>","<Purpose2>" 
	And Click Save and Close button 
	And Click On Warning OK button
	Then Perform Search for an AccountNumber with "<OrganizationName>" 
	Then Click on Account Number 
	And Click Communication 
	And Click Edit Contacts 
	Then Click on Actions and select Create Contacts "<FirstName>" 
	Then Click On Contact points Actions and select create "<PhoneNumber>" 
	Then Click On Account Contact Responsibilities Actions and select Add row "<ResponsibilityType1>","<ResponsibilityType2>"
	And Click Save and Close button 
	Then Click on Account Information Profile History 
	Then Click on Actions and select Correct Record "<BillLevel>","<BillType>","<PaymentTerms>" 
	And Click Save and Close button 
	Then User logs out of Fusion application 
	
	Examples: 
		|OrganizationName|AccountDescription|AccountType|AccountAddressSet|SiteName|Purpose1|Purpose2|FirstName|PhoneNumber|ResponsibilityType1|ResponsibilityType2|BillLevel|BillType|PaymentTerms| 
		|TEST_CUST_20B_AR001|TEST_CUST_20B_AR001|External|Enterprise  |TEST_SITE_20B_AR001|Bill to|Ship to|Test|9999999999|Bill to|Legal|Account|Detail|ORA_HES|