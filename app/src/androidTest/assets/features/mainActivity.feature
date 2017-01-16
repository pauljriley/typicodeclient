Feature: Main Activity

  @ScenarioId("FUNCTIONAL.MAIN.SCN.001") @main-scenarios
  Scenario: Verify that the application displays a list of post
    Given I see the main page
    Then I should see a list of posts

  @ScenarioId("FUNCTIONAL.MAIN.SCN.002") @main-scenarios
  Scenario: Verify that I can open a post
    Given I see the main page
    When I select a post
    Then I should see the posts details

