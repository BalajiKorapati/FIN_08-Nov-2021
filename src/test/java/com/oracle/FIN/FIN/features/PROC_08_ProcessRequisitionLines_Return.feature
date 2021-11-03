#####################################################
#Script Name: PROC_08 Process Requistion Lines Return
#Description/Action: Process Requisition Lines - Return ( No agreement/ Negotiated box not ticked)
#Manual Test Case used: PROC_08 Process Requistion Lines Return
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 29-Mar-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:PROC_08_ProcessRequistionLines_Return.feature
#Unique Data Required or Not: No
#Pre-Requisites (if Any):
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC08
  Scenario Outline: PROC_08_ProcessRequistionLines_Return
    Given User logs into Fusion application
    When User navigates to Requisitions page
    And Click on  More Tasks and select Request Non Catalog item
    And Enter Noncatalog requests details "<ItemDesc>","<CategoryName>","<Qty>","<UOM>","<Price>","<Currency>"
    And Add to cart and Submit the request
    Then Capture the requistion number
    Then Navigate to Purchase agreements page
    Then Search requisition in Process requisitions page
    And Click on Return action "<ReturnComment>"

    Examples: 
      | ItemDesc                | CategoryName           | Qty | UOM | Price | Currency | ReturnComment           |
      | Test for No negotiation | 615.00 Office Supplies | 100 | Ea  |  90 | USD      | Test for No negotiation |
