#####################################################
#Script Name: CE001_CreateMisBankTrans
#Description/Action: Create Miscellaneous Bank Tranasction.
#Manual Test Case used: CE_01 Record Miscellaneous Bank Transactions
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 22-Mar-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:CE001_CreateMisBankTrans.feature
#Unique Data Required or Not: No
#Pre-Requisites (if Any):
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Cash Management

  Background: 
    Given Setup Reporting for Finance

  @CE001
  Scenario Outline: CE001_CreateMiscellaneousBankTranasction
    Given User logs into Fusion application
    When User navigates to Create Transactions page
    And Enter Transcation details "<BankAccountNum>", "<Amount>","<TranscationDate>"
    And Enter Transcation Type "<TransactionType>","<Description>"
    Then Enter Accounting details "<CashAccount>","<OffsetAccount>"
    And Save External Transaction page
    Then Verify the External Transaction creation

    Examples: 
      | BankAccountNum | Amount | TranscationDate | TransactionType | Description | CashAccount                                                  | OffsetAccount                                                |
      | Cash Box - USD |   1000 |                 | Miscellaneous   | Test_01     | 20-00000-00000-1202010102-20-00000-000000-000000-00000-00000 | 20-00000-00000-2108010122-20-00000-000000-000000-00000-00000 |
