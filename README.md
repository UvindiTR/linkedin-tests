# LinkedIn UI Test Automation (IT3153 Software Quality Assurance, Assignment 04)

Automated UI tests for LinkedIn written in Java with Selenium WebDriver and TestNG.
The suite covers logging in, searching, and opening a search result.

Name: RMUT Rathnayake Student No: D/BIT/24/0048

## Technologies
- Java 21
- Selenium WebDriver 4 (Selenium Manager downloads ChromeDriver automatically)
- TestNG
- Maven
- Google Chrome
- Design pattern: Page Object Model (POM) with explicit waits

## Project structure
```
src/test/java
  base/   BaseTest.java, Config.java, SetupCheckTest.java
  pages/  LoginPage.java, SearchPage.java
  tests/  LoginTest.java, SearchTest.java, ResultTest.java
```
- `BaseTest` starts Chrome before each test, closes it afterwards, and saves a screenshot in `screenshots/` when a test fails.
- `Config` reads the login details from `config.properties`.
- `LoginPage` and `SearchPage` hold the locators and page actions.
- `SetupCheckTest` is a small test that only checks that the browser setup works.

## Test cases
| ID | Test | What it checks |
|----|------|----------------|
| TC01 | `LoginTest.validLogin` | A valid login opens the feed (URL contains `/feed`) |
| TC02 | `LoginTest.invalidPassword` | A wrong password shows an error message |
| TC03 | `SearchTest.searchShowsResults` | Searching shows the results page |
| TC04 | `ResultTest.firstResultOpens` | The first result opens the page it points to |

## Requirements
1. JDK 17 or later (developed with JDK 21)
2. Google Chrome (up to date)
3. Maven (or IntelliJ IDEA, which includes it)
4. Internet access
5. **Your own LinkedIn account**, preferably a dedicated test account

## How to configure the credentials
Passwords are never stored in the code or uploaded to Git.

1. In the project's top folder, copy `config.properties.example` to a new file named `config.properties`.
2. Open `config.properties` and enter your own test account details:
   ```
   LINKEDIN_EMAIL=your-test-account-email
   LINKEDIN_PASSWORD=your-test-account-password
   ```
3. Do not put spaces around `=` or quotation marks around the values.

`config.properties` is listed in `.gitignore`. If the file is missing, the code falls back to environment variables with the same names (`LINKEDIN_EMAIL`, `LINKEDIN_PASSWORD`).

## How to run the tests
**In IntelliJ IDEA**
1. Open the project and let Maven load the dependencies.
2. Right-click the `tests` folder and choose **Run 'Tests in tests'**.
   To run one test, click the green play icon next to it.

**From the command line** (in the project's top folder)
```
mvn test
```

Run the tests from the project's top folder, because `config.properties` is read from there.

## Screenshots
When a test fails, a screenshot is saved automatically in the `screenshots/` folder, named after the test.

## Handling login security and limitations
- Only my own account is used. No one else's credentials.
- If LinkedIn shows a CAPTCHA or a verification step, the tests do not try to bypass it. Complete the step by hand and run the test again.
- Several logins in a row can lead LinkedIn to ask for extra checks, so run the tests sparingly.
- LinkedIn changes its pages often, so locators may need updating. Its terms restrict automated access, and results depend on live content, such as which search result appears first.
- Because LinkedIn's pages load dynamically, a test can occasionally fail because of timing or a slow connection. Running that test again once usually fixes it.