# Property-Based Testing Lab

**SE 3004 Software Testing | Mugla Sitki Kocman University**

---

## Student Information

|                | Student 1                | Student 2 |
| -------------- | ------------------------ | --------- |
| **Name**       | Muhammed Mustafa Özdemir |           |
| **Student ID** | 220717009                |           |

> Fill in your names and IDs before your first commit.

---

## Setup

### Requirements

| Tool          | Version            | Download                                                                                  |
| ------------- | ------------------ | ----------------------------------------------------------------------------------------- |
| Java JDK      | 17 or higher       | [adoptium.net](https://adoptium.net)                                                      |
| Maven         | 3.8 or higher      | [maven.apache.org](https://maven.apache.org)                                              |
| IntelliJ IDEA | Any recent version | [jetbrains.com/idea](https://www.jetbrains.com/idea/download) (Community Edition is free) |

### Steps

**1. Download the project ZIP from DYS and extract it.**

**2. Open in IntelliJ IDEA:**
`File → Open → select the pbt-lab folder`

Wait for Maven to sync — it downloads dependencies automatically (~30 MB, one time only).

**3. Verify setup:**

```bash
mvn test -Dtest=Task1_WarmupTest
```

All 4 Task 1 tests should pass. If they do not, ask your instructor.

---

## Project Structure

```
pbt-lab/
  src/
    main/java/lab/
      StringUtils.java        <- Task 1 & 2  — DO NOT MODIFY
      BoundedStack.java       <- Task 3       — DO NOT MODIFY
      BuggyCalculator.java    <- Task 4       — DO NOT MODIFY
      FreeTask.java           <- Task 5       — DO NOT MODIFY
    test/java/lab/
      Task1_WarmupTest.java   <- your work
      Task2_CountOccurrencesTest.java
      Task3_BoundedStackTest.java
      Task4_BuggyCalculatorTest.java
      Task5_FreeTaskTest.java
  pom.xml                     <- Maven config (do not modify)
```

> **Only edit files inside `src/test/java/lab/`.**

---

## Tasks

| Task      | File                              | Time        | Type                | Points    |
| --------- | --------------------------------- | ----------- | ------------------- | --------- |
| Task 1    | `Task1_WarmupTest.java`           | 20 min      | Read & annotate     | 2 pt      |
| Task 2    | `Task2_CountOccurrencesTest.java` | 35 min      | Guided — fill TODOs | 5 pt      |
| Task 3    | `Task3_BoundedStackTest.java`     | 40 min      | Guided + Open       | 5 pt      |
| Task 4    | `Task4_BuggyCalculatorTest.java`  | 35 min      | Find the bugs       | 5 pt      |
| Task 5    | `Task5_FreeTaskTest.java`         | 30 min      | Free design         | 3 pt      |
| **Total** |                                   | **3 hours** |                     | **20 pt** |

---

## Running Tests

**Run a single task:**

```bash
mvn test -Dtest=Task2_CountOccurrencesTest
```

**Run all tasks:**

```bash
mvn test
```

**Run from IntelliJ:**
Right-click any test class or method → Run

---

## Submission

1. Run `mvn clean` to remove compiled files:
   ```bash
   mvn clean
   ```
2. Create a ZIP of the entire `pbt-lab` folder.
3. Prepare a PDF report (see lab document for details).
4. Log in to **dys.mu.edu.tr**
5. Go to **SE 3004 Software Testing → Lab 03 Submission**
6. Upload your ZIP file and PDF report.

---

## Tips

- If jqwik finds a counterexample, read the `Sample` section — it shows the exact input that broke your property.
- Use `@Property(tries = 200)` to speed up slow tests during development.
- Use `Assume.that(condition)` to filter inputs that do not satisfy preconditions.
- Switch driver/navigator roles after each task.
