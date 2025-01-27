
I have created automated tests for each acceptance criterion using open-source frameworks designed to handle browser interactions and verification steps. These tests simulate a user’s actions—such as adding items, toggling checkboxes, and applying filters—to confirm the To-Do application behaves as expected under various conditions.
• I have utilized Selenium WebDriver in conjunction with Java to interact with and verify elements of the To-Do application.
• I have incorporated Cucumber for Behavior-Driven Development (BDD), structuring our test scenarios in an easily understandable, scenario-based format.

This approach allows for both clear communication of test requirements and maintainable test code. It also supports seamless integration into CI/CD pipelines to verify the acceptance criteria across diverse environments


Instructions on how to run the tests.	

Clone the Repository from IntelliJ
Open IntelliJ and select “Get from VCS” (or go to File > New > Project from Version Control).
Paste the repository URL mentioned above into the “URL” field.
Click “Clone” to create a local copy of the project.
Install/Enable the Cucumber Plugin
Go to File > Settings (or IntelliJ Preferences on macOS).
Select Plugins in the left panel.
Search for “Cucumber for Java.”
Install or enable it, then restart IntelliJ if prompted.
Run the Cucumber Tests
On the right hand maven panel perform under lifecycle Clean then Compile

 


• Alternatively, locate your Cucumber Test Runner class or a “.feature” file, right-click, and select “Run.”
Install Project Dependencies
• In the IntelliJ terminal view or your system’s command prompt:
• If you use Maven, run: mvn clean install
• If you use Gradle, run: gradle clean build


