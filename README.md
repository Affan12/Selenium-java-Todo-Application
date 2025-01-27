TODO APPLICATION

1.	Acceptance Criteria
________________________________________
This To-Do application is a straightforward task management tool that enables users to create and organize their daily tasks in a list. Core features include adding new items, marking tasks as completed, filtering tasks by status (All, Active, or Completed), and tracking the number of remaining tasks. Users can also clear completed tasks and delete specific items, making the app a simple yet effective way to stay on top of personal or work-related to-dos.

 
________________________________________
ADD A SINGLE TO-DO ITEM
________________________________________
• A user can enter one new item (e.g., "Buy Milk") on the To-Do MVC homepage.
• The newly added item should appear in the to-do list.
• The list must accurately show this single entry.
________________________________________
ADD MULTIPLE TO-DO ITEMS
________________________________________
• A user can add several items in sequence (e.g., "Buy Milk," "Exercise," "Meditate").
• Each new item appears in the list in the order it was entered.
• The list must contain all items exactly as typed.
________________________________________
CLEAR COMPLETED TO-DO ITEMS
________________________________________
• A user can mark a to-do item as completed.
• When the user selects "clear completed," all marked (completed) items are removed.
• Only items still active remain in the list.
________________________________________
FILTER ACTIVE TO-DO ITEMS
________________________________________
• Some items are completed, and some remain active.
• The user selects the "Active" filter to view only items that are not completed.
• Completed items are not visible in this filtered view.
________________________________________
FILTER COMPLETED TO-DO ITEMS
________________________________________
• Some items are completed, and some remain active.
• The user selects the "Completed" filter to view only items that have been marked as done.
• Only completed items are displayed; active items are hidden in this view.
________________________________________
VERIFY COUNTER AFTER ADDING AN ITEM
________________________________________
• A user adds a single new to-do item.
• The item counter should increment by one.
• If only one item is added, it should display "1 item left."
________________________________________
VERIFY COUNTER AFTER ADDING MULTIPLE ITEMS
________________________________________
• A user adds multiple new to-do items (e.g., three).
• The counter should reflect the total number of active items (e.g., "3 items left").
• No completed tasks affect this number; only uncompleted items are counted.
________________________________________
VERIFY COUNTER AFTER COMPLETING A TO-DO ITEM
________________________________________
• A user adds some to-do items and completes one of them.
• The counter must decrease by one for each item marked as completed.
• Only active items contribute to the total displayed.
________________________________________
VERIFY COUNTER AFTER DELETING A TO-DO ITEM
________________________________________
• A user deletes one of the existing items from the list.
• The item should no longer appear, and the counter should update accordingly.
• Only remaining active items should be reflected in the counter.
________________________________________
VERIFY COUNTER AND FILTERING FUNCTIONALITY
________________________________________
• A user adds multiple items, completes some, and navigates across filters (All, Active, Completed).
• The counter consistently shows the count of active items as filters change.
• Under “Active,” only uncompleted items appear; under “Completed,” only completed ones.
• Switching filters does not remove or alter items; it only changes the view.
________________________________________
DELETE A TO-DO ITEM
________________________________________
• A user can remove an existing item from the list (e.g., "Buy Milk").
• Once removed, the item no longer appears in any filter or in the counter.
• The list accurately reflects all remaining tasks after deletion.



2.	Develop automation tests for the acceptance criteria using any preferred open-source automation tool and programming language.

________________________________________

I have created automated tests for each acceptance criterion using open-source frameworks designed to handle browser interactions and verification steps. These tests simulate a user’s actions—such as adding items, toggling checkboxes, and applying filters—to confirm the To-Do application behaves as expected under various conditions.
• I have utilized Selenium WebDriver in conjunction with Java to interact with and verify elements of the To-Do application.
• I have incorporated Cucumber for Behavior-Driven Development (BDD), structuring our test scenarios in an easily understandable, scenario-based format.

This approach allows for both clear communication of test requirements and maintainable test code. It also supports seamless integration into CI/CD pipelines to verify the acceptance criteria across diverse environments



3.	Upload the source code to a public repository on GitHub.

https://github.com/Affan12/Selenium-java-Todo-Application/


Provide clear instructions on how to run the tests.	

Clone the Repository from IntelliJ
• Open IntelliJ and select “Get from VCS” (or go to File > New > Project from Version Control).
• Paste the repository URL mentioned above into the “URL” field.
• Click “Clone” to create a local copy of the project.
Install/Enable the Cucumber Plugin
• Go to File > Settings (or IntelliJ Preferences on macOS).
• Select Plugins in the left panel.
• Search for “Cucumber for Java.”
• Install or enable it, then restart IntelliJ if prompted.
Run the Cucumber Tests
• On the right hand maven panel perform under lifecycle Clean then Compile


• Alternatively, locate the Cucumber Test Runner class or a “.feature” file, right-click, and select “Run.”
Install Project Dependencies
• In the IntelliJ terminal view or your system’s command prompt:
• If you use Maven, run: mvn clean install
• If you use Gradle, run: gradle clean build


