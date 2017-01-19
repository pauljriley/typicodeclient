Feature: Main Activity

  @main
  Scenario: Verify that the application displays a list of post
    Given I see the main page
    Then I should see a list of posts

  @main
  Scenario: Verify that I can open a post
    Given I see the main page
    When I select a post
    Then I should see the posts details

  @main
  Scenario: Verify that the application displays the details of a post
    Given I see the main page
    When I select a post
    Then I should see the posts details
    Then I should see the detail of the post


