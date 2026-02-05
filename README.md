# UI Automation Sample (Java + Selenium + TestNG + Allure + Jenkins Windows)

## What’s inside
- Selenium 4 + TestNG
- Multi-browser (Chrome/Edge/Firefox) via WebDriverManager
- Explicit wait only (no implicit wait)
- PageUI (locators) separated from Page Objects
- BasePage + ElementActions (Allure Steps)
- Allure attachments on failure: screenshot, page source, browser console (when available)
- Retry only for tests annotated with `@Flaky(retries=N)` via TestNG transformer
- Jenkinsfile (Windows agent) runs Chrome/Edge/Firefox in parallel with separate workspaces

## Quick start (local)
1) Update `src/main/resources/env/staging.properties` with your app url and credentials.
2) Run:
```bash
mvn test -Denv=staging -Dbrowser=chrome -Dheadless=false -DsuiteXmlFile=src/test/resources/suites/smoke.xml
```

## Jenkins
- Use `Jenkinsfile`.
- Install JDK 17, Maven, and Allure plugin on Jenkins.
- Prefer Jenkins credentials injection and override via:
  - `-Dusername=... -Dpassword=...`

## Update locators
- `src/main/java/ui/locators/LoginPageUI.java`
- `src/main/java/ui/locators/HomePageUI.java`

## Notes
- The sample assumes:
  - login url is `${baseUrl}/login`
  - home/dashboard loaded when `[data-testid='app-header']` is visible
Adjust for your application.
