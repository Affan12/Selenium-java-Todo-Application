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


  #New Tests AC

  Scenario: Filter Active To-Do Items
    Given I am on the To-Do MVC homepage
    And I add the following To-Do items:
      | item          |
      | Write Tests   |
      | Conduct Review|
    And I click the checkbox for "Write Tests"
    When I click the "Active" filter
    Then I should see only the following active items:
      | item            |
      | Conduct Review  |

  Scenario: Filter Completed To-Do Items
    Given I am on the To-Do MVC homepage
    And I add the following To-Do items:
      | item           |
      | Complete Homework |
      | Attend Meeting |
    And I click the checkbox for "Complete Homework"
    When I click the "Completed" filter
    Then I should see only the following completed items:
#      | item                |
      | Complete Homework   |



