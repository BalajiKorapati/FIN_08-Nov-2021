#####################################################
#Script Name: AP013_InvoiceCancellation
#Description/Action: Cancelling an Invoice
#Manual Test Case used: AP013 - Invoice Cancellation
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
#Pre-Requisites (if Any):An Invoice must be available to cancel
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Accounts Payable

  Background: 
    Given Setup Reporting for Finance

  @AP00
  Scenario Outline: AP013_InvoiceCancellation
    Given User logs into Fusion application
    When User navigates to Manage Invoices page
    And Perform Search for an Invoice with "<InvoiceNumber>"
    And Click Invoice Number to open invoice
    When User clicks "Cancel Invoice" under Actions dropdown
    Then Warning message displayed
    And User clicks OK button
    Then Invoice validation status changes to "Canceled"
    And Invoice Amount changes to "0.00"
    Then User logs out of Fusion application
    
	Examples:
		|InvoiceNumber|
		|Inv			|