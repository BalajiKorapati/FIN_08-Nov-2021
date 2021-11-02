#####################################################
#Script Name: AR002.1_CreateManualInvoice
#Description/Action: Create manually in AR page an invoice of service 
#Manual Test Case used: AR002.1 - Create Manual Invoice - Services
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 05-03-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location: 
#Data Bank parameters:
#Feature File Name/s used: 
#Unique Data Required or Not: Yes
#Pre-Requisites (if Any): 
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################

Feature: Accounts Receivable

  Background: 
    Given Setup Reporting for Finance
    
    @AR002.1
  Scenario Outline: AR002.1_CreateManualInvoice
    Given User logs into Fusion application
    When User navigates to Create Transaction page
    Then Enter Transaction Header details "<TransactionClass>", "<BusinessUnit>", "<Source>", "<Type>", "<TransactionDate>", "<AccountingDate>", "<BillToName>"
    And Enter Transaction Line Details "<MemoLine>", "<Description>", "<Quantity>", "<UnitPrice>", "<TRXBusinessCategory>"
    Then Click Save and verify status of the transaction is "Incomplete"
    Then Click Edit Distributions under Actions dropdown
    Then Review Distributions and Click Save and Close
    Then Click Complete and Review and verify status of the transaction is "Complete"
    Then User logs out of Fusion application
    
    Examples:
    |TransactionClass|BusinessUnit						|Source|Type|TransactionDate|AccountingDate|BillToName		|MemoLine				|Description|Quantity|UnitPrice|TRXBusinessCategory|
    |Invoice				 |Supremo US Business Unit|		   | 	  | 							|					 		 |Emerald Hotels|Facility Rental|					 	|	1			 |				 |Sales Transaction  |
    