#####################################################
#Script Name: PROC_28_Edit_PO
#Description/Action: Edit PO
#Manual Test Case used: PROC_28_Edit PO
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 24-03-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:
#Unique Data Required or Not: Yes
#Pre-Requisites (if Any): A PO must be available to Edit
#Automated Script to be executed as pre-req(if any): PROC_27_CreatePO(manually)
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC_28
  Scenario Outline: PROC_28_EditPO
    Given User logs into Fusion application
    When User navigates to "Procurement" > "Purchase Orders"
    Then Click "Manage Orders" under Tasks
    Then Perform Search for "Order" and verify results displayed "<OrderNumber>"
    And Click "PO number" link in Results and verify "Purchase Order" page displayed
    Then Click Edit under Actions dropdown
    Then Verify Change Order warning message and Click Yes
    And Update Purchase Order details "<PaymentTerms>", "<Description>"
    Then Click Submit button
    And Verify "change order" submitted confirmation message and Click OK
    Then User logs out of Fusion application

    Examples: 
      | OrderNumber | PaymentTerms | Description           |
      |      164064 | End of Month | Updated Payment Terms |
