# DemoBlaze Selenium Test Automation

## Project Overview
Automated functional testing framework for the DemoBlaze e-commerce website. Uses Selenium WebDriver, TestNG, Maven, and the Page Object Model (POM) to validate core user journeys and business workflows.

## Key Features
- Comprehensive e-commerce workflow coverage
- Modular, maintainable Page Object Model (POM) design
- Automated browser management with WebDriverManager
- TestNG-powered suite and assertions
- Real-time reporting via Jenkins CI
- Test management & traceability with Jira + Zephyr

## Technologies Used
- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Maven**
- **WebDriverManager**
- **Jenkins**
- **Jira / Zephyr**
- **Git/GitHub**

## Project Structure

```
demoblaze-selenium/
├── pom.xml
├── testng.xml
├── README.md
├── src/
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   ├── pages/
│       │   ├── tests/
│       │   └── utils/
└── target/
    └── surefire-reports/
```

## Test Coverage

### Automated Test Cases (16)

**Registration:**  
- Register user with new username (timestamp driven)  
- Attempt registration with existing username

**Login:**  
- Blank credentials  
- Wrong password  
- Valid login

**Cart:**  
- Add product to cart  
- Add same product twice 
- Remove product from cart

**Purchase:**  
- End-to-end product purchase workflow

**Logout:**  
- User logout and session validation

**Product Category:**  
- View phones  
- View laptops  
- View monitors

**Contact Form:**  
- Valid submission  
- Submission with empty fields  
- Submission with long message

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/demoblaze-selenium.git
   cd demoblaze-selenium
   ```
2. Install Java JDK (11+), Maven, and Chrome browser.
3. Update Maven libraries:
   ```bash
   mvn clean install
   ```

## Running Tests

- To run all tests:
  ```bash
  mvn clean test
  ```
- To run specific test class:
  ```bash
  mvn test -Dtest=LoginTest
  ```
- Configure and run with TestNG suite:
  ```bash
  mvn test -DsuiteXmlFile=testng.xml
  ```

## CI/CD

- Jenkins is configured to trigger builds on each Git push and publishes results.
- All test results are viewable in Jenkins and exported as JUnit XML and HTML reports.

## Test Management

- Test cases and execution results are tracked via Jira & Zephyr for full traceability and reporting.

## Contribution

- Open to pull requests and feature suggestions!
- Please follow POM structure and add unit tests with assertions for any new functionality.

## License

MIT License

***
