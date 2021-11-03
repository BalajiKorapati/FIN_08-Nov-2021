#####################################################
#Script Name: AR002.3_CreditInvoice
#Description/Action: Create and Apply a credit memo from an Invoice 
#Manual Test Case used: AR002.3 - Credit Invoice
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 10-03-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location: 
#Data Bank parameters:
#Feature File Name/s used: 
#Unique Data Required or Not: Yes
#Pre-Requisites (if Any): An Invoice must be avilable to apply Credit Memo
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################


Feature: Accounts Receivable

  Background: 
    Given Setup Reporting for Finance
    
   @AR002.3
  Scenario Outline: AR002.3_CreditInvoice
    Given User logs into Fusion application
    When User navigates to Credit Transaction page
    Then Search for a Transaction Number "<Number>"
    And Enter Credit Amount "<Amount>" and verify tax amount is calculated
    And Click Edit Distributions button
    Then Reveiw Distributions and Click Save and Close
    Then Click Complete and Close button and Verify the transaction created message
    Then Click "Manage Transactions" under Tasks
    Then Perform Search for Transaction with "","<Number>","",""
    And Click Transaction Number in Search Results "<Number>"
    Then Select "View Transaction Activities" under Actions
    And Verify Transaction Activity status is "Applied"
    Then User logs out of Fusion application
    
    Examples:
    |Number|Amount|
    |48778 |-20		|