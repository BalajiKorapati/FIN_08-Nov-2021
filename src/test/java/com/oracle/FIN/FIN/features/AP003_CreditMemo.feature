#####################################################
#Script Name: AP003_CreditMemo
#Description/Action: Create a Credit Memo and Validate
#Manual Test Case used: AP003 - Credit Memo
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 02-03-2021
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

Feature: Accounts Payable

  Background: 
    Given Setup Reporting for Finance
    
    @AP00
  Scenario Outline: AP003_CreditMemo
    Given User logs into Fusion application
    When User navigates to Create Invoice page
    And Enter Invoice Header details "<BusinessUnit>", "<Supplier>", "<SupplierSite>", "<LegalEntity>", "<InvoiceNumber>", "<Amount>", "<Type>", "<Date>", "<PaymentTerms>", "<TermsDate>"
    And Enter Line Details
    And Save the Invoice
    And Click "Calculate Tax" under Invoice Actions dropdown
    Then Verify Total Amount and Update Invoice Header Amount
    Then "Validate" the Invoice and verify Invoice status changes to "Validated"
    Then Click "Account in Draft" under Invoice Actions dropdown
    And Verify Accounting complete message and Click OK
    And Click Invoice Validation Status link to view Invoice Summary
    And Verify Invoice "Accounting" status displayed as "Draft"
    And Close Invoice Summary pop up window
    Then Click "Post to Ledger" under Invoice Actions dropdown
    And Verify Accounting complete message and Click OK
    And Click Invoice Validation Status link to view Invoice Summary
    And Verify Invoice "Accounting" status displayed as "Accounted"
    And Close Invoice Summary pop up window
    Then User logs out of Fusion application
    
    Examples:
    |BusinessUnit			|Supplier			 |SupplierSite|LegalEntity|InvoiceNumber|Amount|Type			 |Date|PaymentTerms|TermsDate|
    |US1 Business Unit|ABC Consulting|						|					  |Inv				  |-100	 |Credit memo|		|Immediate	 |				 |
    