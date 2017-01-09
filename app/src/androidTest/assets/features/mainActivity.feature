Feature: Main Activity
  Display a list of posts

  Scenario: Verify that the application displays a list of post
    Given I am launching the application
    When The Main Activity has loaded
    Then I should see a list of posts

  Scenario: Verify that I can open a post
    Given I am launching the application
    When I select a post
    Then I should see the posts details

