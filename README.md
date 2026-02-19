# 📘 Online Exam Question Bank & Test Paper Assembly System (Hibernate)

---

## 📌 Overview

The **Online Exam Question Bank & Test Paper Assembly System** is a console-based Java application developed using **Core Java, Hibernate ORM, and Oracle Database**.

This system allows an examination cell to:

- Maintain a reusable **question bank**
- Assemble test papers using **difficulty-based blueprints**
- Publish and archive **test papers**
- Protect the integrity of **published exams**
- Manage questions using **Hibernate transactions**

The project follows a clean **Layered MVC Architecture**:

**Bean → DAO → Service → Controller**

---

## 🚀 Features

### 🔹 Question Management

- **Add** new question  
- **View** question details  
- **View all** questions  
- **Remove** question (with validation)

### 🔹 Test Paper Management

- **Create Test Paper** (Transactional Operation)  
- **Publish Test Paper** (Transactional Operation)  
- **Archive Test Paper**

### 🔹 Validation & Data Integrity

- Prevent deletion of questions used in **PUBLISHED** papers  
- Prevent publishing invalid test papers  
- Validate **blueprint difficulty distribution**  
- Ensure sufficient **ACTIVE** questions before paper creation  

---

## 🛠 Technologies Used

- **Java (Core Java)**
- **Hibernate ORM**
- **Oracle Database**
- **HQL**
- **Console-Based UI**
- **MVC Architecture**

---

## 📂 Project Structure

src/  
└── com.exam  
├── app  
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

---

## 🧠 System Architecture

The application follows a **Layered Architecture Pattern**:

| **Layer**             | **Responsibility**                              |
|-----------------------|-------------------------------------------------|
| **Bean Layer**        | Represents database entities (Hibernate mapped) |
| **DAO Layer**         | Handles Hibernate CRUD operations               |
| **Service Layer**     | Contains business logic and validations          |
| **Controller Layer**  | Manages console interaction                     |

---

## 🔄 Transactional Operations

The following operations are executed inside **Hibernate transactions**:

- **Create Test Paper**
- **Publish Test Paper**

Transactions ensure:

- **Atomicity**
- **Consistency**
- **Automatic Rollback on Failure**

Hibernate manages sessions using `Session` and `Transaction`.

---

## 📋 Business Rules Enforced

- Question ID must be **unique**
- Difficulty must be **EASY / MEDIUM / HARD**
- Marks must be **positive**
- Cannot publish a **non-DRAFT** paper
- Cannot delete a question used in a **PUBLISHED** paper
- Must have enough **ACTIVE** questions to create paper
- Blueprint total must match **required total marks**

---

## 📊 Status Lifecycle

### Question Status
- **ACTIVE**
- **INACTIVE**

### Test Paper Status
**DRAFT → PUBLISHED → ARCHIVED**

---

## 🧪 Sample Use Case

**Example Blueprint**

- **Paper Title:** Hibernate Practice Test  
- **Subject:** JAVA  
- **Total Marks:** 10  
- **Difficulty Mix:** EASY = 4, MEDIUM = 6, HARD = 0  

**System Workflow**

1. Validate inputs  
2. Check question availability  
3. Fetch questions using HQL  
4. Store paper as **DRAFT**  
5. Allow publishing after validation  

---

## ▶️ How to Run

1. Configure database details in **hibernate.cfg.xml**  
2. Ensure entity mappings are correct  
3. Build the project  
4. Run **ExamMain**  
5. Perform operations via console  

---

## 🖥 Sample Output

**Adding New Question**

<img width="1553" height="458" alt="image" src="https://github.com/user-attachments/assets/901d1a5d-e461-4b78-9f46-ac6ef17ab6f7" />

**Viewing All the Question Paper**

<img width="1544" height="790" alt="image" src="https://github.com/user-attachments/assets/7cd941d8-1f74-4b8d-a63b-a7bd2226966a" />

**Exit**

<img width="1543" height="280" alt="image" src="https://github.com/user-attachments/assets/72af44e7-efde-4a9f-a2dc-cc14da32c569" 

---

## 🎯 Key Concepts Demonstrated

- **Hibernate ORM Mapping**
- **Session & Transaction Management**
- **DAO Pattern**
- **Custom Exception Handling**
- **HQL Queries**
- **Blueprint-Based Paper Assembly**
- **Data Integrity Enforcement**
- **Layered System Design**
