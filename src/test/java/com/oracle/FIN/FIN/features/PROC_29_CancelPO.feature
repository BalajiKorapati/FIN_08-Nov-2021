#####################################################
#Script Name: PROC_29_CancelPO
#Description/Action: Cancel PO
#Manual Test Case used: PROC_29_Cancel PO
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
#Pre-Requisites (if Any): A PO must be available to Cancel
#Automated Script to be executed as pre-req(if any): PROC_27_CreatePO(manually)
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any): In Demo Instance, while cancelling approved PO, a change order will be created and it is submitted for approval.
#So in this script, we are verifying change order confirmation message for cancelling Approved PO.
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC_29
  Scenario Outline: PROC_29_CancelPO
    Given User logs into Fusion application
    When User navigates to "Procurement" > "Purchase Orders"
    Then Click "Manage Orders" under Tasks
    Then Perform Search for "Order" and verify results displayed "<OrderNumber>"
    And Click "PO number" link in Results and verify "Purchase Order" page displayed
    Then Click Cancel Document under Actions dropdown
    Then Enter "<Reason>" and Click OK in Cancel Document window
    And Verify "change order" submitted confirmation message and Click OK
    Then User logs out of Fusion application

    Examples: 
      | OrderNumber | Reason         |
      |      164066 | PO Cancel Test |
