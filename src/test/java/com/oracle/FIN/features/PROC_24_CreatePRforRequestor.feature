#####################################################
#Script Name: PROC_24_CreatePRforRequestor
#Description/Action: Create PR for Requestor
#Manual Test Case used: PROC_24_Create PR for Requestor
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 25-03-2021
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
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC_24
  Scenario Outline: PROC_24_CreatePRforRequestor
    Given User logs into Fusion application
    When User navigates to Purchase Requisitions page
    Then Click "Enter Requisition Line" under More Tasks
    Then Enter Requisition Line details "<LineType>", "<Item>", "<Quantity>", "<Price>"
    Then Click Add to Cart and verify Item is added to Cart
    Then Click Cart icon and Click Submit button
    Then Verify Requisition submitted message and Click OK
    Then Click Manage Requisitions link
    And Perform Search for "Requisition" and verify results displayed ""
    Then User logs out of Fusion application

    Examples: 
      | LineType | Item    | Quantity | Price  |
      |          | CM65041 |          | 100.00 |
