#####################################################
#Script Name: PROC_21_Edit PR_No_PO
#Description/Action: Edit PR (no PO)
#Manual Test Case used: PROC_21_Edit PR (no PO)
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 22-Mar-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:PROC021_Edit_PR_No_PO.feature
#Unique Data Required or Not: No
#Pre-Requisites (if Any): Require an Requisition with Pending approval status
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC021
  Scenario Outline: PROC021_Edit_PR_No_PO
    Given User logs into Fusion application
    When User navigates to Requisitions page
    And Search for the requistion "<RequistionNum>"
    And Click on Actions and Edit requistion "<Description>","<ReqQty>"
    And Save the page

    Examples: 
      | RequistionNum | Description | ReqQty |
      |        204100 |        1000 |     20 |
