#####################################################
#Script Name: FA002.12_RunDepreciation
#Description/Action:
#Manual Test Case used: FA002.12_RunDepreciation
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 10-03-2021
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
Feature: Fixed Assets

  Background: 
    Given Setup Reporting for Finance

  @FA002.11
  Scenario Outline: FA002.12_RunDepreciation
    Given User logs into Fusion application
    When User navigates to Assets page
    Then Click on the Depreciation Infotile
    And Click on the Calculate Depreciation
    Then Navigate to Scheduled Processes
    Then Verify "Calculate Depreciation" program Status is "Succeeded"

    Examples: 
      | Status    |
      | Succeeded |
