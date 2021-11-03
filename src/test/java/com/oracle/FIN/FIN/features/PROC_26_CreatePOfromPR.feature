#####################################################
#Script Name: PROC_26_CreatePOFromPR
#Description/Action: Create PO from PR
#Manual Test Case used: PROC_26_Create PO from PR
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 29-03-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:
#Unique Data Required or Not:
#Pre-Requisites (if Any): A PR must be available
#Automated Script to be executed as pre-req(if any): PROC_24_CreatePRforRequestor
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC_26
  Scenario Outline: PROC_26_CreatePOFromPR
    Given User logs into Fusion application
    When User navigates to "Procurement" > "Purchase Orders"
    Then Click "Process Requisitions" under Tasks
    Then Perform Search for "Requisition" and verify results displayed "<PRNumber>"
    Then Select PR row in Search results
    And Click Add to Document Builder button
    Then Click OK in Add to Document Builder window
    Then Click Create button in Document Builder window
    And Verify "Purchase Order" submitted confirmation message and Click OK
    Then Verify "Order" number is created
    Then Enter General Details "<Supplier>", "", "", ""
    Then Click Submit button
    And Verify "Purchase Order" submitted confirmation message and Click OK
    Then Click "Manage Orders" under Tasks
    Then Perform Search for "Order" and verify results displayed ""
    Then User logs out of Fusion application

    Examples: 
      | PRNumber | Supplier                    |
      |   204096 | Northwest Electronic Supply |
