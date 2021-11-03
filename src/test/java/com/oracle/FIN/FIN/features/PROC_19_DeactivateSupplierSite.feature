#####################################################
#Script Name: PROC_19_DeactivateSupplierSite
#Description/Action: Deactivate Supplier Site
#Manual Test Case used: PROC_19_Deactivate Supplier Site
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 31-03-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:
#Unique Data Required or Not:
#Pre-Requisites (if Any): A Supplier Site must be available to make it Inactive
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC_19
  Scenario Outline: PROC_19_DeactivateSupplierSite
    Given User logs into Fusion application
    When User navigates to "Procurement" > "Suppliers"
    Then Click "Manage Suppliers" under Tasks
    Then Perform Search for "Keywords" and verify results displayed "<Supplier>"
    And Click "Supplier" link in Results and verify "Supplier:" page displayed
    Then Click Edit button to Edit Supplier details
    Then Click "Sites" tab
    Then Select Site "<Site>" and Click Edit icon
    Then Enter Inactive Date "<InactiveDate>"
    Then Click Save and Close button to save Site changes
    Then Verfiy changes saved Confirmation message displayed and Click OK
    And Click Submit button to save Supplier changes
    Then User logs out of Fusion application

    Examples: 
      | Supplier         | Site   | InactiveDate |
      | TEST_SUPPLIER_01 | Office | 4/1/21       |
