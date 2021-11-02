#####################################################
#Script Name: FA002_7_RetireAnAsset
#Description/Action: Asset Retirement Full By Units. (Manual and massive) with or without revenue.
#Manual Test Case used: FA002.7 Asset Retirement Full By Units. (Manual and massive) with or without revenue.
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 08-Mar-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:
#Unique Data Required or Not: Yes
#Pre-Requisites (if Any): An Asset number is required
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Fixed Assets

  Background: 
    Given Setup Reporting for Finance

  @FA002_7
  Scenario Outline: FA002_7_RetireAnAsset
    Given User logs into Fusion application
    When User navigates to Retire Assets page
    And Search for an Asset "<AssetNum>"
    And Retire Cost of an Asset "<RetireCostComments>","<CostRetired>"
    Then Click on Submit in Retire Assets page
    And Retire Units of an Asset "<RetireUnitsComments>","<RetiredUnits>"
    Then Click on Submit in Retire Assets page
    And Verify the Retire Assests

    Examples: 
      | AssetNum          | RetireCostComments         | CostRetired | RetireUnitsComments         | RetiredUnits |
      | ASSETNUM363161844 | Retirement by cost testing |         500 | Retirement by Units testing |            1 |
