#####################################################
#Script Name: FA002_1_ManuallyAddAsset
#Description/Action: Manually add a single asset by entering all required information and any optional information directly into Assets
#Manual Test Case used: FA002.1 Manual Additions Capitalized Asset including Minor Assets Categories
#Track/Module: FINANCE
#Regions to be executed:
#Developed By: OFS
#Date Created: 25-02-2021
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

  @FA002_1
  Scenario Outline: FA002_1_ManuallyAddAsset
    Given User logs into Fusion application
    When User navigates to Add Asset page
    And Enter Basic information "<Book>","<AssetType>","<Category>","<Description>","<Cost>","<Units>","<ExpenseAccount>","<Location>"
    And Enter Asset details "<AssetNum>","<InServiceDate>"
    Then Enter Descriptive details "<TagNum>","<SerialNum>","<Manufacturer>","<Model>","<PropertyType>","<Ownership>","<Bought>"
    Then Enter Assignments details "<EmployeeName>"
    And Enter Transaction details "<Comments>","<Amortize>"
    Then Clicks on Submit button in Add Asset page
    And Verify the Asset creation

    Examples: 
      | Book    | AssetType   | Category          | Description | Cost | Units | ExpenseAccount           | Location                      | AssetNum | InServiceDate | TagNum | SerialNum | Manufacturer | Model | PropertyType | Ownership | Bought | EmployeeName  | Comments | Amortize |
      | US CORP | Capitalized | COMPUTER-HARDWARE | FA_Testing  | 1000 |     1 | 101.10.68160.000.000.000 | USA-CALIFORNIA-AUSTIN-OFFICES | AN_Test_ |               |        |           |              |       |              |           |        | Abrams, Paula | Testing  | No       |
