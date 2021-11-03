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

Feature: Accounts Payable

  Background: 
    Given Setup Reporting for Finance
    
    @AP005
  Scenario Outline: AP005_CreatePayment
    Given User logs into Fusion application
    When User navigates to Manage Invoices page
    And Perform Search for an Invoice with "<InvoiceNumber>"
    And Select Row in Invoice Results
    And Click Pay in Full button
    Then Enter Payment Details "<BankAccount>", "<PaymentProcessProfile>", "<PaymentDocument>"
    And Click Submit button and verify Payment Confirmation message
    And Click OK button in Payment Confirmation window
    Then Navigate to Create Positive Pay File page
    Then Enter Positive Pay parameters "<FromPaymentDate>", "<ToPaymentDate>", "<AllowSendingReplacementCopy>", "<Status>"
    And Click Submit button and verify Process submitted confirmation message
    And Click OK button in Process Confirmation window
    Then Navigate to Scheduled Processes
    And Verify "Create Positive Pay File" report Status is "Succeeded"
    Then User logs out of Fusion application
    
    Examples:
    |InvoiceNumber|BankAccount|PaymentProcessProfile|PaymentDocument|FromPaymentDate|ToPaymentDate|AllowSendingReplacementCopy|Status|
    |1001			|BofA-2869	|Positive Pay CHECK		|Check Stock A	|2/25/21				|							|														|			 |
    