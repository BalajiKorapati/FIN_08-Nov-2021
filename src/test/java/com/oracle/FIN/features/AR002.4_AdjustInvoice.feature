#####################################################
#Script Name: AR002.4_AdjustInvoice
#Description/Action: Adjust an already created Invoice 
#Manual Test Case used: AR002.4 - Adjust Invoice
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 11-03-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location: 
#Data Bank parameters:
#Feature File Name/s used: 
#Unique Data Required or Not: Yes
#Pre-Requisites (if Any): An Invoice must be available to adjust
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################

Feature: Accounts Receivable

  Background: 
    Given Setup Reporting for Finance
    
  @AR002.4
  Scenario Outline: AR002.4_AdjustInvoice
    Given User logs into Fusion application
    When User navigates to Manage Transactions page
    Then Perform Search for Transaction with "","<Number>","",""
    And Click Transaction Number in Search Results "<Number>"
    Then Select "Manage Adjustments" under Actions
    Then Click Create Adjustment icon
    And Enter Adjustment Details "<Activity>", "<Type>", "<Reason>"
    Then Submit Adjustment Details and Verify transaction created message
    Then Click Done and Click Save
    When User navigates to Manage Transactions page
    Then Perform Search for Transaction with "","<Number>","",""
    And Click Transaction Number in Search Results "<Number>"
    Then Select "View Transaction Activities" under Actions
    And Verify Transaction Activity status is "Approved"
    Then User logs out of Fusion application
    
   Examples:
   |Number|Activity						 |Type							 |Reason	 |
   |48778	|Adjustments Reversal|Invoice Adjustments|Write off|	
   