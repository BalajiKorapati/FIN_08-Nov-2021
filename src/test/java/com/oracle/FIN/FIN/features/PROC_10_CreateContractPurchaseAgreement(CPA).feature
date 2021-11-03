#####################################################
#Script Name: PROC_10_CreateContractPurchaseAgreement(CPA)
#Description/Action: Create a Contract Purchase Agreement (CPA)
#Manual Test Case used: PROC_10_Create a Contract Purchase Agreement (CPA)
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 22-03-2021
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

  @PROC_10
  Scenario Outline: PROC_10_CreateContractPurchaseAgreement(CPA)
    Given User logs into Fusion application
    When User navigates to "Procurement" > "Purchase Agreements"
    Then Click "Create Agreement" under Tasks
    Then Enter Create details "<Style>", "<ProcurementBU>", "", "<Supplier>"
    And Click Create button in Create window
    Then Verify "Agreement" number is created
    Then Enter General Details "", "<StartDate>", "<EndDate>", "<AgreementAmount>"
    Then Click Submit button
    And Verify "<Style>" submitted confirmation message and Click OK
    Then Click "Manage Agreements" under Tasks
    Then Perform Search for "Agreement" and verify results displayed ""
    Then User logs out of Fusion application

    Examples: 
      | Style                       | ProcurementBU | Supplier       | StartDate | EndDate | AgreementAmount |
      | Contract Purchase Agreement |               | ABC Consulting | 3/23/21   | 6/23/21 |        1,000.00 |
