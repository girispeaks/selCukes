@Leads
  Feature: Managing Leads
    Creation and Deletion of Leads

Background: Logged In
    Given I am logged in Zoho.com
    And I click on 'Leads' in the top menu

@CreateLead
  Scenario Outline: Creation of Leads
    When I go to create lead page
    And enter and submit the lead details
      | FirstName    | LastName     | Email           | Company   |
      | <FirstName>  | <LastName>   | <Email>         | <Company> |
    Then Lead Description Page should load
    And I verify lead details
    When I click on 'Leads' in the top menu
    Then lead '<FirstName>','<LastName>' should 'be present' inside the grid
    Examples:
      |FirstName|LastName|Email          |Company|
      |James    |Daniel  |james@gmail.com|RDA    |


  @DeleteLead
  Scenario Outline: Deletion of the Leads
    When I select the lead
    And I click on the delete button
    Then lead should be deleted
    Examples:
      |FirstName|LastName|Email|Company|
      |name1    |        |     |       |
      |name1    |        |     |       |