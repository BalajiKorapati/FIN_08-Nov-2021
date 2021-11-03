#####################################################
#Script Name: Test
#Description/Action:
#Manual Test Case used: Test
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 23-02-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location: 
#Data Bank parameters:
#Feature File Name/s used: Test
#Unique Data Required or Not:
#Pre-Requisites (if Any):An Employee with Assignment Status :
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Login and Logout

  Background: 
    Given Setup Reporting for Finance

  @Test
  Scenario: Successful Login and Logout
    Given User logs into Fusion application
    Then User logs out of Fusion application
