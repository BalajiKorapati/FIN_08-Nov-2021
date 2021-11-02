#####################################################
#Script Name: PROC_07 Create req for Approval Capital Project - check correct approvers
#Description/Action: Create req for Approval Capital Project - check correct approvers
#Manual Test Case used: PROC_07 Create req for Approval Capital Project - check correct approvers
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 29-Mar-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:PROC007_CreateReqApprovalCapitalProject.feature
#Unique Data Required or Not: No
#Pre-Requisites (if Any):
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC07
  Scenario Outline: PROC007_CreateReqApprovalCapitalProject
    Given User logs into Fusion application
    When User navigates to Requisitions page
    And Click on  More Tasks and select Request Non Catalog item
    And Enter Noncatalog requests details "<ItemDesc>","<CategoryName>","<Qty>","<UOM>","<Price>","<Currency>"
    And Add to cart and Submit the request
    Then Capture the requistion number
    Then Search for Requisition created
    And Review the Approval list

    Examples: 
      | ItemDesc                | CategoryName           | Qty | UOM | Price | Currency | ReturnComment           |
      | Test for No negotiation | 615.00 Office Supplies | 200 | Ea  |  1000 | USD      | Test for No negotiation |
