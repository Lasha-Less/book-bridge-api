# 🌉 book-bridge-api

## 🚀 Overview  
**book-bridge-api** is a Spring Boot microservice that connects the Book Metadata Management System with the Google Books API. 
It allows importing external book metadata and storing it in a local MongoDB database. It includes duplicate detection to avoid 
re-importing existing records.

---

## 🧱 Tech Stack  
- **Language:** Java 17  
- **Framework:** Spring Boot  
- **Database:** MongoDB  
- **Persistence:** Spring Data MongoDB  
- **REST Client:** Spring WebClient / RestTemplate  
- **Containerization:** Docker (optional)

---

## 📦 Features  
✅ Search and fetch book metadata from the **Google Books API**  
✅ Store raw metadata in **book-harvest (MongoDB)**  
✅ Avoid duplicates using internal checks  
✅ RESTful endpoints to trigger search/import workflows  
✅ Prepares raw book metadata for later enrichment and transformation

---

## 🧩 Part of: Book Metadata Management System

This service is part of a larger microservices-based architecture:

| Service          | Responsibility                                     |
|------------------|-----------------------------------------------------|
| `book-worm-api`  | Manages finalized book metadata in MySQL            |
| `book-bridge-api`| Fetches metadata from Google Books → saves in Mongo |
| `book-scribe`    | Enriches, transforms, and imports metadata to SQL   |
| `control-panel` *(planned)* | Orchestrates workflows and provides UI    |

---

## 🔧 Getting Started

To run the project locally:

```bash
# Clone the repository
git clone https://github.com/Lasha-Less/book-bridge-api.git
cd book-bridge-api

# Run with Maven
mvn spring-boot:run
