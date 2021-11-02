#####################################################
#Script Name: PROC017 Edit Supplier and Site
#Description/Action: Edit Supplier and Site
#Manual Test Case used: PROC017 Edit Supplier and Site
#Track/Module: Procurement
#Regions to be executed:
#Developed By: OFS
#Date Created: 14-Apr-2021
#Date Last Updated:
#Script Location:
#Databank Used: Yes/No: No
#Data Bank location:
#Data Bank parameters:
#Feature File Name/s used:PROC017_EditSupplierAndSite.feature
#Unique Data Required or Not: No
#Pre-Requisites (if Any):
#Automated Script to be executed as pre-req(if any):
#Status Of script(Dev/Review/Complete): Dev
#Comments (if any):
#####################################################
Feature: Procurement

  Background: 
    Given Setup Reporting for Finance

  @PROC17
  Scenario Outline: PROC17_EditSupplierAndSite
    Given User logs into Fusion application
    When User navigates to Supplier page
    Then User navigates to Manage Supplier page
    And Search for the Supplier "<SupplierName>"
    And Click on Edit
    Then Edit Site details "<SiteName>", "<NewSiteName>" and save and close
    Then Handle the confirmation message
    Then Edit Address "<AddressName>","<NewAddressName>"
    And Handle the confirmation message
    Then Submit the changes

    Examples: 
      | SupplierName   | SiteName | NewSiteName | AddressName  | NewAddressName |
      | ABC Consulting | ABC US10 | ABC US11   | ABC US1 | ABC US10  |
