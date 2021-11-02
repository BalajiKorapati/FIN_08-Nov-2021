#####################################################
#Script Name: AP008_Void/StopPaymentRequest
#Description/Action: Void / Stop a Payment Request
#Manual Test Case used: AP008 - Void / Stop a Payment Request
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 23-02-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:
#Unique Data Required or Not: Yes
#Pre-Requisites (if Any):An Payment number is required to void the request
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Accounts Payable

  Background: 
    Given Setup Reporting for Finance

  @AP008
  Scenario Outline: AP008_Void_StopPaymentRequest
    Given User logs into Fusion application
    When User navigates to Manage Payments page
    And Perform Search for an Payment number with "<PaymentNumber_Stop>"
    And Clicks on "Initiate Stop" under Actions dropdown
    Then Enter Stop reason "<StopReason>" and Submit
    Then Verify Status change "Negotiable" to "Stop initiated"
    And Perform Search for an Payment number with "<PaymentNumber_Void>"
    Then Clicks on "Void" under Actions dropdown
    And Select Invoice Action "<InvoiceActions>" and Submit
    And Verify Status change "Negotiable" to "Voided"
    Then User logs out of Fusion application

    Examples: 
      | PaymentNumber_Stop | StopReason | PaymentNumber_Void | InvoiceActions |
      |               1001 | Testing    |               1010 | None           |
