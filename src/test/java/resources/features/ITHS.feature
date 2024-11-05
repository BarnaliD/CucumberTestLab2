Feature: Test iths website

  Background:
    Given User visits iths.se

  Scenario: Check website title
    When User check the title
    Then the title should be "IT-Högskolan – Här startar din IT-karriär!"

  Scenario: Verify phone number
    When User scroll to the bottom of the page
    Then the phone number should be "031-790 42 55"

  Scenario: Click on menu and verify first item
    When User minimize the screen size
    And User click on the menu button
    Then the first menu item should display "UTBILDNINGAR"

  Scenario: Navigate to Utbildning Göteborg page
    When User click on "Utbildningar"
    And User click on "Göteborg"
    Then the page header should be "IT-utbildningar Göteborg"

  Scenario: User clicks on Utbildning Stockhol link
    When User navigates and clicks on "Utbildninger"
    And User clicks on "Stockholm" link
    Then Page header should be "IT-utbildningar Stockholm"


  Scenario: User enters invalid login credentials
    Given User is on the login page
    When User enter username "Test.Testson@iths.se"
    And User enter password "Testing123"
    And User click the login button
    Then User should see an error message "Invalid login, please try again"

  Scenario: Verify that the contact email and phone number are displayed correctly on the Kontakt page
    When User locates and clicks on the "Kontakt" link
    Then User should see the contact email "marcus@iths.se"
    And User should see the contact phone number "070-5169513"

  Scenario: Verify the presence of "Scrum Master Lön" title in the news section
    When User click on the "Nyheter" link
    Then User should be redirected to the news page
    And User scroll to the "Scrum Master Lön" news title
    Then User should see the news title "Scrum Master Lön"

  Scenario: Verify the availability of the "ITHS tygkasse lila" product
    When User navigates and clicks on the "IT Shop" link
    And User closes any popup
    And User scrolls to the "Hoodies" category
    And User clicks on "Handla Här" button
    Then the page should display the product "ITHS tygkasse lila"

  Scenario: Verify the header on the Viktiga Datum page
    When User hovers over the "Här Du Ansökar" menu
    And User clicks on the "Viktiga Datum" link
    Then the page header should display "Viktiga datum"