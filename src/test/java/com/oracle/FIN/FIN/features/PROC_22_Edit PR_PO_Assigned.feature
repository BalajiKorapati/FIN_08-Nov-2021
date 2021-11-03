#####################################################
#Script Name: PROC_22_Edit PR_PO_Assigned
#Description/Action: Edit PR with PO assigned
#Manual Test Case used: PROC_22_Edit PR with PO assigned
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 29-Mar-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:PROC_22_Edit PR_PO_Assigned.feature
#Unique Data Required or Not: No
#Pre-Requisites (if Any):  A requisition with assigned PO is required
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC022
  Scenario Outline: PROC_22_Edit PR_PO_Assigned
    Given User logs into Fusion application
    When User navigates to Requisitions page
    And Search for the requistion "<RequistionNum>"
    And Click on Actions and Edit Order "<Description>","<POQty>"
    And Save the page

    Examples: 
      | RequistionNum | Description | POQty |
      |        204099 |        1000 |    20 |
