#####################################################
#Script Name: PROC_12_CreateBlanketPurchaseAgreement(BPA)
#Description/Action: Create a Blanket Purchase Agreement (BPA)
#Manual Test Case used: PROC_12_Create a Blanket Purchase Agreement (BPA)
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

  @PROC_12
  Scenario Outline: PROC_12_CreateBlanketPurchaseAgreement(BPA)
    Given User logs into Fusion application
    When User navigates to "Procurement" > "Purchase Agreements"
    Then Click "Create Agreement" under Tasks
    Then Enter Create details "<Style>", "<ProcurementBU>", "", "<Supplier>"
    And Click Create button in Create window
    Then Verify "Agreement" number is created
    Then Enter General Details "", "<StartDate>", "<EndDate>", "<AgreementAmount>"
    Then Enter Line Details "<Type>", "<Item>", "<Description>", "<CategoryName>", "", "<UOM>", "<Price>"
    Then Click Submit button
    And Verify "<Style>" submitted confirmation message and Click OK
    Then Click "Manage Agreements" under Tasks
    Then Perform Search for "Agreement" and verify results displayed ""
    Then User logs out of Fusion application

    Examples: 
      | Style          | ProcurementBU | Supplier       | StartDate | EndDate | AgreementAmount | Type | Item | Description | CategoryName       | UOM | Price    |
      | Purchase Order |               | ABC Consulting | 3/23/21   | 6/23/21 |        1,000.00 |      |      | BPA Test    | Contractor Expense | Ca  | 1,000.00 |
