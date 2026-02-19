#📘 Online Exam Question Bank & Test Paper Assembly System (Hibernate)

---

# 📌 Overview

The Online Exam Question Bank & Test Paper Assembly System is a console-based Java application developed using:

Core Java

Hibernate ORM

Oracle Database

This system helps an examination cell to:

Maintain a reusable question bank

Assemble test papers using difficulty-based blueprints

Publish and archive test papers

Ensure integrity of published exams

Handle data using ORM-based transactions

Architecture Followed

Bean (Entity) → DAO → Service → Controller

# 🚀 Features
* 🔹 Question Management *

Add new question

View question details

View all questions

Remove question (with validation)

* 🔹 Test Paper Management *

Create Test Paper (Transactional Operation)

Publish Test Paper (Transactional Operation)

Archive Test Paper

* 🔹 Validation & Data Integrity *

Prevent deletion of questions used in PUBLISHED papers

Prevent publishing invalid test papers

Validate difficulty blueprint distribution

Ensure sufficient ACTIVE questions before paper creation

# 🛠 Technologies Used

Java (Core Java)

Hibernate ORM

JPA Annotations

Oracle Database

SQL

Console-Based UI

MVC Architecture

# 📂 Project Structure
src/
└── com.kce
    ├── main
    │   └── ExamMain.java
    │
    ├── service
    │   └── ExamService.java
    │
    ├── bean
    │   ├── Question.java
    │   └── TestPaper.java
    │
    ├── dao
    │   ├── QuestionDAO.java
    │   └── TestPaperDAO.java
    │
    └── util
        ├── HibernateUtil.java
        ├── ValidationException.java
        ├── QuestionPoolInsufficientException.java
        └── QuestionInPublishedPaperException.java

# 🧠 System Architecture
Layer	Responsibility
Bean (Entity)	Maps Java classes to DB tables using annotations
DAO Layer	Performs ORM-based CRUD using Hibernate
Service Layer	Business logic & validations
Controller Layer	Console interaction
# 🔄 Transactional Operations

The following operations are executed inside Hibernate-managed transactions:

Create Test Paper

Publish Test Paper

Why Transactions?

Atomicity

Consistency

Rollback on failure

Data integrity

Hibernate explicitly manages:

beginTransaction()

commit()

rollback()

# 📋 Business Rules Enforced

Question ID must be unique

Difficulty must be EASY / MEDIUM / HARD

Marks must be positive

Cannot publish a non-DRAFT paper

Cannot delete a question used in a PUBLISHED paper

Must have sufficient ACTIVE questions

Blueprint total must equal Total Marks

# 📊 Status Lifecycle
* Question Status *

ACTIVE

INACTIVE

* Test Paper Status *

DRAFT → PUBLISHED → ARCHIVED

# 🧪 Sample Use Case
Example Blueprint

Paper Title : DBMS Practice Test

Subject : DBMS

Total Marks : 10

Difficulty Distribution

EASY = 4

MEDIUM = 6

HARD = 0

System Flow

Validate input data

Check ACTIVE question availability

Select questions based on blueprint

Store paper as DRAFT

Allow publishing after validation

# ▶️ How to Run

Configure DB details in hibernate.cfg.xml

Ensure required tables / sequences exist

Compile the project

Run ExamMain

Perform operations via console

# 🖥 Output

**Adding New Question**

<img width="1553" height="458" alt="image" src="https://github.com/user-attachments/assets/901d1a5d-e461-4b78-9f46-ac6ef17ab6f7" />

**Viewing All the Question Paper**

<img width="1544" height="790" alt="image" src="https://github.com/user-attachments/assets/7cd941d8-1f74-4b8d-a63b-a7bd2226966a" />

**Exit**

<img width="1543" height="280" alt="image" src="https://github.com/user-attachments/assets/72af44e7-efde-4a9f-a2dc-cc14da32c569" />


# 🎯 Key Concepts Demonstrated

Hibernate ORM Mapping

JPA Annotations

Session & Transaction Management

DAO Design Pattern

Custom Exception Handling

Blueprint-Based Paper Assembly

Data Integrity Enforcement

Layered Architecture Design

Oracle Sequence-Based ID Generation
