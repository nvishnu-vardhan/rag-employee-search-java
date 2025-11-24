# RAG Employee Search - Java Full Stack

🔍 **AI-powered employee search using RAG (Retrieval Augmented Generation)**

A full-stack application built with Spring Boot backend and React frontend that demonstrates RAG-style employee search capabilities.

## 🎯 Features

- **Intelligent Search**: Search employees by name, skills, department, role, or years of experience
- **RAG-Style Filtering**: Advanced filtering with support for:
  - Skill-based queries (e.g., "Python developers")
  - Experience filtering (e.g., "5+ years")
  - Department filtering (e.g., "Backend in Engineering")
  - Keyword matching across multiple fields
- **Real-time Results**: Fast employee matching with comprehensive summaries
- **Modern UI**: Clean, responsive React interface with purple gradient design
- **REST API**: Well-structured Spring Boot backend with CORS support

## 📚 Technology Stack

### Backend
- **Java 17+**
- **Spring Boot 3.2.0**
- **Spring Web** - RESTful API
- **Lombok** - Reduce boilerplate code
- **Jackson** - JSON processing
- **Maven** - Dependency management

### Frontend
- **React 18**
- **Axios** - HTTP client
- **Modern CSS** - Styled with gradients and cards

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Node.js 14+ and npm
- Git

### Backend Setup

1. **Clone the repository**
```bash
git clone https://github.com/nvishnu-vardhan/rag-employee-search-java.git
cd rag-employee-search-java
```

2. **Navigate to backend**
```bash
cd backend
```

3. **Build the project**
```bash
mvn clean install
```

4. **Run the application**
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup

1. **Navigate to frontend**
```bash
cd frontend
```

2. **Install dependencies**
```bash
npm install
```

3. **Start the development server**
```bash
npm start
```

The frontend will start on `http://localhost:3000`

## 📊 API Documentation

### Search Employees

**Endpoint**: `GET /api/employees/search`

**Query Parameters**:
- `q` (required): Search query string

**Example Requests**:
```bash
# Search for Python developers
curl "http://localhost:8080/api/employees/search?q=Python%20developers"

# Search for employees with 5+ years experience
curl "http://localhost:8080/api/employees/search?q=5%2B%20years"

# Search by department
curl "http://localhost:8080/api/employees/search?q=Backend%20in%20Engineering"
```

**Response Format**:
```json
{
  "employees": [
    {
      "name": "Sarah Johnson",
      "email": "sarah.j@orants.ai",
      "department": "Engineering",
      "years": 5,
      "joinDate": "2024-03-15",
      "skills": ["Python", "React", "PostgreSQL"],
      "role": "Full-stack developer specializing in web applications"
    }
  ],
  "summary": "3 employees were found..."
}
```

## 📁 Project Structure

```
rag-employee-search-java/
├── backend/
│   ├── src/main/java/com/vishnu/employee/
│   │   ├── EmployeeSearchApplication.java
│   │   ├── controller/
│   │   │   └── EmployeeController.java
│   │   ├── model/
│   │   │   └── Employee.java
│   │   └── service/
│   │       └── EmployeeService.java
│   ├── src/main/resources/
│   │   ├── application.properties
│   │   └── employees.json
│   └── pom.xml
├── frontend/
│   ├── public/
│   ├── src/
│   │   └── App.js
│   └── package.json
└── README.md
```

## 🔧 Development

### Adding More Employees

Edit `backend/src/main/resources/employees.json` to add more employee records:

```json
{
  "name": "John Doe",
  "email": "john.doe@company.com",
  "department": "Engineering",
  "years": 3,
  "joinDate": "2022-01-15",
  "skills": ["Java", "Spring Boot", "AWS"],
  "role": "Backend developer"
}
```

### Running Tests

```bash
# Backend tests
cd backend
mvn test

# Frontend tests
cd frontend
npm test
```

## 🚀 Deployment

### Backend Deployment (Railway/Docker)

**Dockerfile** (create in backend directory):
```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
```

### Frontend Deployment (Vercel)

1. Push your code to GitHub
2. Connect your repository to Vercel
3. Set build command: `npm run build`
4. Set output directory: `build`
5. Add environment variable: `REACT_APP_API_URL=your-backend-url`

## ✨ Example Queries

- "Python developers" - Find all Python developers
- "React 5+ years" - Find React developers with 5+ years experience
- "Backend in Engineering" - Find backend engineers
- "Data scientists" - Find data science professionals
- "Full-stack" - Find full-stack developers

## 📝 License

This project is open source and available for educational purposes.

## 👤 Author

**N Vishnu Vardhan**
- GitHub: [@nvishnu-vardhan](https://github.com/nvishnu-vardhan)
- LinkedIn: [N Vishnu Vardhan](https://linkedin.com/in/nvishnu-vardhan)

## 🚀 Future Enhancements

- [ ] Add pagination for large result sets
- [ ] Implement caching for frequent queries
- [ ] Add authentication and authorization
- [ ] Integrate with vector databases (Pinecone, Weaviate)
- [ ] Add AI-powered semantic search
- [ ] Implement real-time updates with WebSockets
- [ ] Add employee profiles and detailed views
- [ ] Export search results to CSV/PDF

---

**Built with ❤️ for learning RAG systems and full-stack development**
