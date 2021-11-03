#####################################################
#Script Name: FA002.3_FreezeAndUnFreezeDepreciation
#Description/Action: Suspend and Resume Depreciation for Capitalized Assets
#Manual Test Case used: FA002.3 - Freeze and Unfrezze DPR checkbox in Capitalized assets
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 03-03-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location: 
#Data Bank parameters:
#Feature File Name/s used: 
#Unique Data Required or Not: Yes
#Pre-Requisites (if Any): Capitalized Asset must be available
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################

Feature: Fixed Assets

  Background: 
    Given Setup Reporting for Finance
    
    @FA002.3
  Scenario Outline: FA002.3_FreezeAndUnFreezeDepreciation
    Given User logs into Fusion application
    When User navigates to Adjust Assets page
    Then Perform Search for an Asset "<Book>", "<AssetNumber>"
    And Select Asset Line in results "<AssetNumber>"
    Then Select "Suspend Depreciation" under Actions dropdown
    Then Enter Comments "Automation Test - Suspend DPR" and Click OK
    And Verify "suspend depreciation transaction" confirmation message and Click OK
    Then Click "<AssetNumber>" from Results to view Asset Details
    And Verify Depreciate is "unchecked" in Financials tab
    And Click Done button to navigate to "Adjust Assets" page
    Then Select "Resume Depreciation" under Actions dropdown
    Then Enter Comments "Automation Test - Resume DPR" and Click OK
    And Verify "resume depreciation transaction" confirmation message and Click OK
    Then Click "<AssetNumber>" from Results to view Asset Details
    And Verify Depreciate is "checked" in Financials tab
    Then User logs out of Fusion application
    
    Examples:
    |Book|AssetNumber |
		|		 |TESTASSET002|