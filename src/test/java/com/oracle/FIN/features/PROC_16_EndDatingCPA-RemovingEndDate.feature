#####################################################
#Script Name: PROC_16_EndDatingCPA-RemovingEndDate
#Description/Action: End dating a CPA - removing an end date
#Manual Test Case used: PROC_16_End dating a CPA - removing an end date
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 15-04-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:
#Unique Data Required or Not: Yes
#Pre-Requisites (if Any): A CPA must be available
#Automated Script to be executed as pre-req(if any): PROC_10_CreateContractPurchaseAgreement(CPA).feature
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC_16
  Scenario Outline: PROC_16_EndDatingCPA-RemovingEndDate
    Given User logs into Fusion application
    When User navigates to "Procurement" > "Purchase Agreements"
    Then Click "Manage Agreements" under Tasks
    Then Perform Search for "Agreement" and verify results displayed "<Agreement>"
    Then Click "Agreement Number" link in Results and verify "Contract Purchase Agreement" page displayed
    Then Click Edit under Actions dropdown
    Then Enter General Details "", "", "<EndDate>", ""
    Then Click Submit button
    And Verify "Contract Purchase Agreement" submitted confirmation message and Click OK
    Then User logs out of Fusion application

    Examples: 
      | Agreement | EndDate |
      |     52271 | 4/14/21 |
