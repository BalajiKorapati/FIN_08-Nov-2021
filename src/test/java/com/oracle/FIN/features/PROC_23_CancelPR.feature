#####################################################
#Script Name: PROC_23_CancelPR
#Description/Action: Cancel PR
#Manual Test Case used: PROC_23_Cancel PR
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 26-03-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:
#Unique Data Required or Not:
#Pre-Requisites (if Any): A PR must be available to Cancel
#Automated Script to be executed as pre-req(if any): PROC_24_CreatePRforRequestor
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any): In Demo Instance, since PR is auto approved, PR cannot be Cancelled. 
#So in this script, we are verifying whether Error message appears when try to Cancel Approved PR.
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC_23
  Scenario Outline: PROC_23_CancelPR
    Given User logs into Fusion application
    When User navigates to Purchase Requisitions page
    Then Click Manage Requisitions link
    And Perform Search for "Requisition" and verify results displayed "<PRNumber>"
    Then Click "PR number" link in Results and verify "Requisition:" page displayed
    Then Select Cancel Requisition under Actions dropdown
    Then Enter "<Reason>" and Click OK in Cancel Requisition window
    Then Verify error message and Click OK
    Then User logs out of Fusion application

    Examples: 
      | PRNumber | Reason    |
      |   204088 | Auto Test |
