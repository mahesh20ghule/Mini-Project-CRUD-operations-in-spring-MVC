# 🧾 Employee Management System (Spring Boot MVC)

A complete CRUD-based web application built using Spring Boot MVC with Thymeleaf. This project manages employee records with features like pagination, search filters, and custom error handling.

---
## Demo Video on LinkedIn
[Watch here](https://www.linkedin.com/posts/mahesh-ghule20_springboot-javadeveloper-fullstackdevelopment-activity-7350474361604034561-z1P-?utm_source=share&utm_medium=member_desktop&rcm=ACoAAEWOcswBa1SddpfUdjs0ZPNQ7ag_jtmjieM)

## 🚀 Features
- Add, Edit, Delete and Search Employees
- Dynamic Search with multiple filters (Job, Salary, Department No.)
- Pagination with Thymeleaf
- Custom 4xx and 5xx error pages
- Form validations
- Embedded Tomcat server

---

## 💻 Tech Stack

### 🧠 Backend
- **Java 17+**
- **Spring Boot (MVC Architecture)**
- **Spring Data JPA** – for ORM and database interaction
- **Spring Web** – for handling HTTP requests and responses
- **Lombok** – to reduce boilerplate code using annotations

### 🎨 Frontend
- **Thymeleaf** – server-side Java template engine
- **HTML5 & CSS3** – for structure and styling

### 🗃️ Database
- **Oracle** – primary database

### 🚀 Tools & Utilities
- **Maven** – for dependency management and builds
- **Spring Tool Suite (STS)** – development environment
- **Git & GitHub** – version control and code hosting
- **Embedded Tomcat** – via Spring Boot starter

### 🌐 Web Features
- **Pagination** – implemented using Spring Data `Pageable`
- **Custom Error Handling** – custom `4xx.html` and `5xx.html` error pages using Thymeleaf
  
## 🛠️ Challenges Faced & Solutions

- **Duplicate POST Requests on Page Refresh:**
  - Faced the common issue of form re-submission when users refreshed the page after submitting a POST request.
  - ✅ Resolved using the Post/Redirect/Get (PRG) pattern by redirecting after form submission and storing messages in the session or using RedirectAttributes.

- **Form Checkboxes with Dynamic Search:**
  - Thymeleaf checkboxes bound to a list (`deptnos`) caused exceptions since the entity class didn't have that field.
  - ✅ Fixed by capturing the checkbox values using `@RequestParam` and plain HTML `name="deptnos"` without binding to the model.

- **Maintaining Search State During Pagination:**
  - Dynamic search filters were getting lost when navigating between pages of results.
  - ✅ Handled by storing the search input in the session and reusing it across paginated requests to maintain filter context.

---

## 📦 How to Run Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/ems-springboot-mvc.git
2. import in eclipse IDE or intellij idea or any IDE you are comfortable with and run it
