#####################################################
#Script Name: PROC004 Create a PR by using a Smart Form
#Description/Action: Create a PR by using a Smart Form
#Manual Test Case used: PROC004 Create a PR by using a Smart Form
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 29-Mar-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:PROC004_CreatePRBySmartForm.feature
#Unique Data Required or Not: No
#Pre-Requisites (if Any):
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC04
  Scenario Outline: PROC004_CreatePRBySmartForm
    Given User logs into Fusion application
    When User navigates to Requisitions page
    And Click on  Business cards from Request forms
    And Enter Requests details "<RequestType>","<Qty>","<Name>","<Title>","<Address>","<PhoneNum>","<EmailID>"
    And Add to cart and Submit the request
    Then Capture the requistion number

    Examples: 
      | RequestType    | Qty | Name           | Title            | Address           | PhoneNum        | EmailID                 |
      | Business Cards |   1 | Morrois Morgan | Business Analyst | Redwood, CA , USA | +1 650-506-7000 | morrois.morgan@test.com |
