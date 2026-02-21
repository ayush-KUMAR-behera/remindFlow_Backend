# RemindFlow 🚀

RemindFlow is a Spring Boot backend application that helps users manage reminders and receive email notifications at scheduled times.

---

## 📌 Problem It Solves

People often forget important tasks like calls, meetings, or deadlines.
RemindFlow solves this by:

* Allowing users to create reminders
* Automatically sending email notifications at the scheduled time
* Managing reminders securely per user

---

## ⚙️ Features

* 🔐 User Authentication (JWT based)
* 📝 Create, Update, Delete Reminders
* 📧 Email Notification System (JavaMailSender)
* ⏰ Scheduler for automated reminder processing
* 🗑️ Soft Delete (data is not permanently removed)
* 📄 Pagination for large data handling
* ❗ Global Exception Handling

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot
* Spring Security
* JWT (Authentication)
* Spring Data JPA (Hibernate)
* MySQL
* JavaMailSender
* Swagger (API Testing)

---

## 🔄 How It Works

1. User registers and logs in
2. JWT token is generated
3. User creates reminders using secured APIs
4. Scheduler runs every minute
5. If reminder time matches → Email is sent automatically

---

## 🔐 Security

* Passwords are encrypted using BCrypt
* JWT is used for authentication
* Protected APIs require token

---

## ▶️ Run Locally

1. Clone the repository
2. Configure database in `application.properties`
3. Add email credentials in `application-local.properties`
4. Run the application

---

## 📌 Future Improvements

* Forgot Password (Email Token Reset)
* Change Password / Update Email
* Reminder Repeat Feature
* Notification via SMS / Push

---

## 👨‍💻 Author

Ayush Kumar Behera
