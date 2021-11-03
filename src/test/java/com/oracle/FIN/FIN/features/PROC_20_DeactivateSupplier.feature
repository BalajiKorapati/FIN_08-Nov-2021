#####################################################
#Script Name: PROC_20_DeactivateSupplier
#Description/Action: Deactivate Supplier
#Manual Test Case used: PROC_20_Deactivate Supplier
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 01-04-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:
#Unique Data Required or Not:
#Pre-Requisites (if Any): A Supplier must be available to make it Inactive
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC_19
  Scenario Outline: PROC_20_DeactivateSupplier
    Given User logs into Fusion application
    When User navigates to "Procurement" > "Suppliers"
    Then Click "Manage Suppliers" under Tasks
    Then Perform Search for "Keywords" and verify results displayed "<Supplier>"
    And Click "Supplier" link in Results and verify "Supplier:" page displayed
    Then Click Edit button to Edit Supplier details
    Then Enter Inactive Date "<InactiveDate>"
    And Click Submit button to save Supplier changes
    And Verify "Internal profile change request" submitted confirmation message and Click OK
    Then User logs out of Fusion application

    Examples: 
      | Supplier         | InactiveDate |
      | TEST_SUPPLIER_01 | 4/1/21       |
