#####################################################
#Script Name: AR003.2_CreateAdjustmentReceipts
#Description/Action: Create receipt write off for small differences 
#Manual Test Case used: AR003.2 - Create Adjustment Receipts
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 12-03-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location: 
#Data Bank parameters:
#Feature File Name/s used: 
#Unique Data Required or Not: Yes
#Pre-Requisites (if Any): A Receipt must be available to write off
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################


Feature: Accounts Receivable

  Background: 
    Given Setup Reporting for Finance
    
  @AR003.2
  Scenario Outline: AR003.2_CreateAdjustmentReceipts
    Given User logs into Fusion application
    When User navigates to Manage Receipts page
    Then Perform Search for a Receipt "<ReceiptNumber>", "<CustomerName>"
    And Click Receipt "<ReceiptNumber>" in Search Results
    Then Select "More:Create Write-Off" under Actions dropdown
    Then Enter Write-Off details "<WriteOffAmount>", "<ReceivablesActivity>"
    And Click OK to Create Write-Off and Click Save
    Then Verify "<ReceivablesActivity>" and "Applied Amount" in Receipt Details
    Then User logs out of Fusion application
    
    Examples:
    |ReceiptNumber|CustomerName	 						|WriteOffAmount|ReceivablesActivity|
    |100					|ABC Corporation Worldwide|	10.00				 |Receipt Write-off	 |
    