Feature: Manage To-Do Items

  Scenario: Add a single To-Do item
    Given I am on the To-Do MVC homepage
    When I add a To-Do item "Buy Milk"
    Then the To-Do list should contain "Buy Milk"

  Scenario: Add multiple To-Do items
    Given I am on the To-Do MVC homepage
    When I add the following To-Do items:
      | item      |
      | Buy Milk  |
      | Exercise  |
      | Meditate  |
    Then the To-Do list should contain the following items:
      | item      |
      | Buy Milk  |
      | Exercise  |
      | Meditate  |

  Scenario: Clear completed To-Do items
    Given I am on the To-Do MVC homepage
    When I add a To-Do item "Complete Homework"
    And I complete the first To-Do item
    And I clear completed items
#    Then the To-Do list should be empty
