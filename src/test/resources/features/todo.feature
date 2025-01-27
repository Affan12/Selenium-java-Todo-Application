@Todo
Feature: Manage To-Do Items
  @Todo_1
  Scenario: Add a single To-Do item
    Given I am on the To-Do MVC homepage
    When I add a To-Do item "Buy Milk"
    Then the To-Do list should contain "Buy Milk"

  @Todo_2
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

  @Todo_3
  Scenario: Clear completed To-Do items
    Given I am on the To-Do MVC homepage
    When I add a To-Do item "Complete Homework"
    And I complete the first To-Do item
    And I clear completed items
#    Then the To-Do list should be empty


  #New Tests AC

  @Todo_4
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

  @Todo_5
  Scenario: Filter Completed To-Do Items
    Given I am on the To-Do MVC homepage
    And I add the following To-Do items:
      | item           |
      | Complete Homework |
      | Attend Meeting |
    And I click the checkbox for "Complete Homework"
    When I click the "Completed" filter
    Then I should see only the following completed items:
      | Complete Homework   |

#Check bottom left counter

  @Todo_6
  Scenario: Verify counter after adding an item
    Given I am on the To-Do MVC homepage
    When I add a To-Do item "Task 1"
    Then the counter should display 1 items left

  @Todo_7
  Scenario: Verify counter after adding multiple items
    Given I am on the To-Do MVC homepage
    When I add the following To-Do items and verify the counter:
      | Task 1 |
      | Task 2 |
      | Task 3 |
    Then the counter should display 3 items left

  @Todo_8
  Scenario: Verify counter after completing a To-Do item
    Given I am on the To-Do MVC homepage
    When I add the following To-Do items:
      | Task 1 |
      | Task 2 |
#    And I complete the first To-Do item and verify the counter
    And I click the checkbox for "Task 1"
    Then the counter should display 1 items left

  @Todo_9
  Scenario: Verify counter after deleting a To-Do item
    Given I am on the To-Do MVC homepage
    When I add the following To-Do items:
      | Task 1 |
      | Task 2 |
    And I click the checkbox for "Task 2"
    Then the counter should display 1 items left


#New E2E Scenario

  @Todo_10
  Scenario: Verify counter and filtering functionality
    Given I am on the To-Do MVC homepage
    When I add the following To-Do items:
      | Task 1 |
      | Task 2 |
    And I click the checkbox for "Task 1"
    Then the counter should display 1 items left
    And I click the "All" filter in page
    And I click the "Active" filter in page
    Then the counter should display 1 items left
    Then the To-Do list under "Active" should contain the following items:
      | Task 2 |
    And I click the "Completed" filter in page
    Then the counter should display 1 items left
    Then the To-Do list under "Completed" should contain the following items:
      | Task 1 |

    #Double Click edit

#  Scenario: Edit a To-Do item
#    Given I am on the To-Do MVC homepage
#    And I add a To-Do item "Buy Groceries"
#    When I edit the To-Do item "Buy Groceries" to "Buy Vegetables"
#    Then the To-Do list should contain "Buy Vegetables" item

  @Todo11
  Scenario: Delete a To-Do item
    Given I am on the To-Do MVC homepage
    When I add a To-Do item "Buy Milk"
    And I delete the To-Do item "Buy Milk"
    Then the To-Do list should not contain "Buy Milk"
