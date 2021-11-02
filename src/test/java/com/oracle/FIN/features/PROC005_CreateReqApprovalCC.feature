#####################################################
#Script Name: PROC_05 Create req for Approval CC - check correct approvers
#Description/Action: Create req for Approval CC - check correct approvers
#Manual Test Case used: PROC_05 Create req for Approval CC - check correct approvers
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 29-Mar-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:PROC005_CreateReqApprovalCC.feature
#Unique Data Required or Not: No
#Pre-Requisites (if Any):
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC05
  Scenario Outline: PROC005_CreateReqApprovalCC
    Given User logs into Fusion application
    When User navigates to Requisitions page
    And Click on  More Tasks and select Enter Requisition Line
    And Enter Noncatalog requests details "<LineType>","<ItemDesc>","<CategoryName>","<Qty>","<UOM>","<Price>","<Currency>"
    And Add to cart and Submit the request
    Then Capture the requistion number
    Then Search for Requisition created
    And Review the Approval list

    Examples: 
      | ItemDesc                                      | CategoryName | Qty | UOM | Price | Currency | ReturnComment           | LineType |
      | Test for Non catalog PR from requistion lines | 425.06 Chair |  20 | Ea  |   200 | USD      | Test for No negotiation | Goods    |
