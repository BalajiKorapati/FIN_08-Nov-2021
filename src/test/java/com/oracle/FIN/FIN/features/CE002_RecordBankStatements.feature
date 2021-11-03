#####################################################
#Script Name: CE002_RecordBankStatements
#Description/Action: Record Bank Statements
#Manual Test Case used: CE_02 Record Bank Statements
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 22-Mar-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:CE002_RecordBankStatements.feature
#Unique Data Required or Not: No
#Pre-Requisites (if Any):
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):If Statement ID is left blank, random number will be generated
#####################################################
Feature: Cash Management

  Background: 
    Given Setup Reporting for Finance

  @CE002
  Scenario Outline: CE002_RecordBankStatements
    Given User logs into Fusion application
    When User navigates to Bank Statements Reconciliation  page
    Then Navigates to Create Bank Statment page
    And Enter Bank Statement details "<BankAccount>", "<PeriodStartDate>","<PeriodEndDate>","<StatementID>"
    And Enter Statement Lines "<BookingDate>","<TransactionCode>","<FlowIndicator>","<Amount>"
    Then Enter Balance details "<ClosingAmount>"
    And Save Bank Statement page
    Then Verify the Bank Statement creation

    Examples: 
      | BankAccount                | PeriodStartDate | PeriodEndDate | StatementID | BookingDate | TransactionCode | FlowIndicator | Amount | ClosingAmount |
      | HSBC - Current - EUR - 111 | 3/1/21          | 3/17/21       |             | 3/15/21     |             101 | Credit        |   1000 |          1000 |
