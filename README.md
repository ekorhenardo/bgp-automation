## Tech Stack
| Tool               | Purpose                        |
|--------------------|--------------------------------|
| Playwright (Java)  | Browser automation             |
| JUnit 5            | Test framework                 |
| Allure             | Test reporting                 |
| Maven              | Build & dependency management  |
| Spring Boot (TBD)  | Frontend UI for execution/reporting (planned) |

## Documentation / Justification
This section explains the flow of how the project was built, along with the reasoning behind each decision.

### 1. Started with Backend (Test Logic)
The first step was building the core test logic using:
- **Playwright with Java** — this combination was recommended in the task, and I also wanted to explore it more deeply since it's gaining popularity in modern QA stacks. It’s a good chance to get hands-on experience with Playwright’s powerful browser automation while sticking to Java, which is widely used in enterprise environments.
- **JUnit 5** — used as the main test framework for writing and organizing the tests.
- **Maven** — chosen for managing dependencies, building the project, and eventually integrating with other tools like Allure and Spring Boot.

This backend-first strategy ensured that:
- Tests are runnable independently via command line.
- Core logic and utilities are thoroughly validated.
- Test classes and structure are modular and maintainable.

### 2. Reporting with Allure
After getting the tests running properly, I integrated Allure to generate test reports. This helps visualize test results with:
- Clear breakdowns of steps and outcomes
- Execution time
- Attachments (e.g. screenshots, if added later)

Allure was a good fit because it’s clean, well-documented, and works nicely with JUnit 5. It also sets the stage for future CI/CD integration.

### 3. Simple Frontend (Planned)
A basic **Spring Boot** UI will be added to trigger test execution from a browser — either running all tests or a specific test class. This makes it easier for non-technical users to run the tests without needing the terminal.

Note: At this stage, the UI will only be used to trigger test runs. Displaying the test results on the frontend might be considered later if time allows.

## Project Structure
The code is organized into:
- `src/main/java`: Contains main flow logic or utilities that simulate user behavior.
- `src/test/java`: Contains test cases, base setup, and section-level validations.
- Utility files (e.g., BaseTest) to centralize Playwright setup/teardown logic and avoid duplication.


## Setup Instructions

### 1. Install Java
```bash
brew install openjdk@17
```
Note: follow the remaining setup for Java that appear in your terminal after running the brew command (setup $JAVA_HOME, symlink, etc).

### 2. Install Maven
```bash
brew install maven
```

### 3. Install Allure
```bash
brew install allure
```

### 4. Verify Installation:
```bash
java -version
mvn -v
allure --version
```

## How to Run Tests

### Run All Test Cases
```bash
mvn clean test
```

### Run Specific Test Class
```bash
mvn clean test -Dtest=ContactDetailsSectionTest
```
Note: the command above will run all test cases under **ContactDetailsSectionTest.java** file.