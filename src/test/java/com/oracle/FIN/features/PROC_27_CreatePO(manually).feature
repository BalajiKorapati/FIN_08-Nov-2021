#####################################################
#Script Name: PROC_27_CreatePO(manually)
#Description/Action: Create PO (manually)
#Manual Test Case used: PROC_27_Create PO (manually)
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 23-03-2021
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

  @PROC_27
  Scenario Outline: PROC_27_CreatePO(manually)
    Given User logs into Fusion application
    When User navigates to "Procurement" > "Purchase Orders"
    Then Click "Create Order" under Tasks
    Then Enter Create details "<Style>", "<ProcurementBU>", "<RequisitioningBU>", "<Supplier>"
    And Click Create button in Create window
    Then Verify "Order" number is created
    Then Enter Line Details "<Type>", "<Item>", "<Description>", "<CategoryName>", "<Quantity>", "<UOM>", "<Price>"
    Then Click Submit button
    And Verify "<Style>" submitted confirmation message and Click OK
    Then Click "Manage Orders" under Tasks
    Then Perform Search for "Order" and verify results displayed ""
    Then User logs out of Fusion application

    Examples: 
      | Style          | ProcurementBU | RequisitioningBU | Supplier                    | Type | Item    | Description | CategoryName | Quantity | UOM | Price |
      | Purchase Order |               |                  | Northwest Electronic Supply |      | CM65041 |             |              |        1 |     | 10.00 |
