# Spring Boot Olingo 2 JPA Sample Project

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.15-brightgreen.svg)](https://spring.io/projects/spring-boot)

A comprehensive sample project demonstrating how to build an OData 2.0 service using Apache Olingo 2, Spring Boot, and JPA with EclipseLink.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Database Configuration](#database-configuration)
- [OData Service Endpoints](#odata-service-endpoints)
- [Entity Model](#entity-model)
- [Usage Examples](#usage-examples)
- [Development Guide](#development-guide)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

---

## 🎯 Overview

This project serves as a **starter template** for building **OData 2.0 services** with Spring Boot. It demonstrates the seamless integration of Apache Olingo 2 with Spring Boot and JPA, using EclipseLink as the JPA implementation. The service supports both H2 (in-memory) and MySQL databases, making it suitable for both development and production environments.

### Purpose

- Provide a working example of Apache Olingo 2 integration with Spring Boot
- Demonstrate JPA entity exposure as OData resources
- Show best practices for configuring EclipseLink with Spring Boot
- Serve as a foundation for building enterprise OData services

---

## ✨ Features

- ✅ **OData 2.0 Protocol Support** - Full implementation of OData 2.0 specification
- ✅ **JPA Integration** - Seamless integration with JPA entities using Olingo's JPA processor
- ✅ **EclipseLink Provider** - Uses EclipseLink with static weaving for enhanced performance
- ✅ **Dual Database Support** - Pre-configured for both H2 (dev) and MySQL (prod)
- ✅ **Custom Type Converters** - Support for Java 8+ types like `LocalDateTime`
- ✅ **LOB Support** - Handle large objects with lazy loading capabilities
- ✅ **RESTful API** - Expose database entities as OData resources
- ✅ **Spring Boot Auto-configuration** - Leverage Spring Boot for rapid development

---

## 🛠 Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 1.8 | Programming Language |
| Spring Boot | 2.7.15 | Application Framework |
| Apache Olingo | 2.0.13 | OData 2.0 Implementation |
| EclipseLink | 2.7.9 | JPA Provider |
| Apache CXF | 3.2.14 | JAX-RS Runtime |
| Maven | 3.6+ | Build Tool |
| H2 Database | Latest | In-Memory Database (Default) |
| MySQL | 8.0.33 | Production Database (Optional) |
| Joda-Time | 2.10.6 | Date/Time Utilities |
| Gson | 2.9.1 | JSON Processing |

---

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- ☑️ **Java Development Kit (JDK)** 8 or higher
- ☑️ **Apache Maven** 3.6 or higher
- ☑️ **MySQL** 8.0+ (optional, for production database)
- ☑️ **Git** (for cloning the repository)
- ☑️ **IDE** (IntelliJ IDEA, Eclipse, or VS Code recommended)

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/shivasaxena/spring-boot-olingo2-jpa-sample-project.git
cd spring-boot-olingo2-jpa-sample-project
```

### 2. Build the Project

```bash
mvn clean install
```

This command will:
- Download all required dependencies
- Compile the source code
- Run EclipseLink static weaving on entity classes
- Execute unit tests
- Package the application as a JAR file

### 3. Run the Application

**Option A: Using Maven**
```bash
mvn spring-boot:run
```

**Option B: Using the JAR file**
```bash
java -jar target/spring-boot-olingo2-sample-project-0.0.1-SNAPSHOT.jar
```

**Option C: From Your IDE**
- Locate and run the main class: `SpringBootOlingo2SampleProjectApplication.java`

### 4. Verify the Service is Running

Open your browser and navigate to:
```
http://localhost:8080/odata.svc/
```

You should see the OData service document listing available entity sets.

---

## 📁 Project Structure

```
spring-boot-olingo2-jpa-sample-project/
│
├── src/
│   ├── main/
│   │   ├── java/com/metalop/code/samples/olingo/springbootolingo2sampleproject/
│   │   │   ├── SpringBootOlingo2SampleProjectApplication.java  # Main application entry point
│   │   │   │
│   │   │   ├── configuration/
│   │   │   │   └── OlingoConfig.java                           # OData and JPA configuration
│   │   │   │
│   │   │   ├── entity/
│   │   │   │   ├── Student.java                                # Student JPA entity
│   │   │   │   └── Location.java                               # Location entity (placeholder)
│   │   │   │
│   │   │   └── utils/
│   │   │       ├── CustomODataJPAProcessor.java                # Custom OData processor
│   │   │       ├── CustomOnJPAWriteContent.java                # Custom write handler
│   │   │       ├── JPAServiceFactory.java                      # OData service factory
│   │   │       ├── LocalDateTimeAttributeConverter.java        # JPA type converter
│   │   │       ├── MyDebugCallback.java                        # Debug utilities
│   │   │       ├── SpringContextUtil.java                      # Spring context helper
│   │   │       └── custom_adapters/
│   │   │           └── LocalDateTimeXmlAdapter.java            # XML adapter for LocalDateTime
│   │   │
│   │   └── resources/
│   │       ├── application.properties                          # Spring Boot configuration
│   │       └── META-INF/
│   │           └── persistence.xml                             # JPA persistence configuration
│   │
│   └── test/
│       └── java/                                               # Unit and integration tests
│
├── pom.xml                                                     # Maven build configuration
├── README.md                                                   # Project documentation (this file)
└── LICENSE                                                     # Apache 2.0 License
```

---

## 🗄 Database Configuration

### H2 Database (Default Configuration)

The project uses **H2 in-memory database** by default for quick development and testing. Configuration in `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

**Accessing H2 Console:**
- URL: `http://localhost:8080/h2-console` (if enabled)
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

### MySQL Database (Production Configuration)

To switch to MySQL for production:

1. **Install and start MySQL server**

2. **Create a database:**
   ```sql
   CREATE DATABASE testdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. **Update `application.properties`:**
   ```properties
   # Comment out H2 configuration
   # spring.datasource.url=jdbc:h2:mem:testdb
   # spring.datasource.driverClassName=org.h2.Driver
   
   # Uncomment and configure MySQL
   spring.datasource.url=jdbc:mysql://localhost:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

4. **Restart the application**

---

## 🌐 OData Service Endpoints

The OData service is exposed at `/odata.svc/` with comprehensive query capabilities.

### Service Root

```
GET http://localhost:8080/odata.svc/
```
Returns the service document with available entity sets.

### Metadata Document

```
GET http://localhost:8080/odata.svc/$metadata
```
Returns the OData metadata (EDM) describing the complete data model.

### Entity Operations

#### Get All Students
```
GET http://localhost:8080/odata.svc/Students
```

#### Get a Specific Student
```
GET http://localhost:8080/odata.svc/Students('S001')
```

#### Filter Results
```
GET http://localhost:8080/odata.svc/Students?$filter=marks gt 80
GET http://localhost:8080/odata.svc/Students?$filter=name eq 'John Doe'
```

#### Sort Results
```
GET http://localhost:8080/odata.svc/Students?$orderby=name asc
GET http://localhost:8080/odata.svc/Students?$orderby=marks desc
```

#### Select Specific Properties
```
GET http://localhost:8080/odata.svc/Students?$select=studentID,name,marks
```

#### Pagination
```
GET http://localhost:8080/odata.svc/Students?$top=10&$skip=0
```

#### Count Entities
```
GET http://localhost:8080/odata.svc/Students/$count
```

#### Combined Query
```
GET http://localhost:8080/odata.svc/Students?$filter=marks gt 80&$orderby=name asc&$top=5
```

---

## 📊 Entity Model

### Student Entity

The `Student` entity represents student records with comprehensive data support.

| Property | Type | Nullable | Description |
|----------|------|----------|-------------|
| `studentID` | String | No | Primary key, unique student identifier |
| `name` | String | Yes | Student's full name |
| `dateOfJoining` | LocalDateTime | Yes | Date when the student enrolled |
| `marks` | int | Yes | Student's academic score/marks |
| `picture` | byte[] | Yes | Student's photograph (LOB) |
| `longTextDescription` | String | Yes | Detailed description (LOB, lazy-loaded) |

**Sample JSON Response:**
```json
{
  "d": {
    "results": [
      {
        "__metadata": {
          "type": "Default.Student",
          "uri": "http://localhost:8080/odata.svc/Students('S001')"
        },
        "studentID": "S001",
        "name": "John Doe",
        "dateOfJoining": "/Date(1609459200000)/",
        "marks": 85,
        "picture": null,
        "longTextDescription": "Excellent student with strong analytical skills."
      }
    ]
  }
}
```

### Location Entity

Placeholder entity for future expansion. Can be extended to include geographical location data, addresses, or other location-based information.

---

## 💡 Usage Examples

### Using cURL

**Retrieve all students:**
```bash
curl -X GET "http://localhost:8080/odata.svc/Students" -H "Accept: application/json"
```

**Get student by ID:**
```bash
curl -X GET "http://localhost:8080/odata.svc/Students('S001')" -H "Accept: application/json"
```

**Filter students with marks greater than 80:**
```bash
curl -X GET "http://localhost:8080/odata.svc/Students?\$filter=marks%20gt%2080" -H "Accept: application/json"
```

**Create a new student (if POST is implemented):**
```bash
curl -X POST "http://localhost:8080/odata.svc/Students" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "studentID": "S002",
    "name": "Jane Smith",
    "marks": 92,
    "dateOfJoining": "/Date(1609459200000)/"
  }'
```

### Using Postman

1. **Import Service**: Use `http://localhost:8080/odata.svc/$metadata` to understand the schema
2. **Set Headers**: 
   - `Accept: application/json` for JSON responses
   - `Content-Type: application/json` for POST/PUT requests
3. **Use OData Query Options**: Add query parameters for filtering, sorting, pagination

### Using JavaScript/Fetch API

```javascript
// Fetch all students
fetch('http://localhost:8080/odata.svc/Students', {
  method: 'GET',
  headers: {
    'Accept': 'application/json'
  }
})
.then(response => response.json())
.then(data => console.log(data))
.catch(error => console.error('Error:', error));
```

---

## 🔧 Development Guide

### Key Components Explained

#### 1. OlingoConfig.java
Configures the OData servlet and entity manager factory:
- **OData Servlet Registration**: Maps the service to `/odata.svc/*`
- **EclipseLink Configuration**: Sets up EclipseLink as the JPA provider
- **Service Factory**: Registers `JPAServiceFactory` for OData request handling
- **Logging**: Configures EclipseLink logging level

#### 2. JPAServiceFactory.java
Creates and configures the OData JPA service:
- Provides entity manager access to the OData processor
- Implements custom OData processor for business logic
- Manages persistence unit configuration
- Handles OData service lifecycle

#### 3. CustomODataJPAProcessor.java
Extends the default JPA processor to:
- Implement custom query logic
- Add business validation rules
- Override default CRUD behavior
- Handle custom error responses

#### 4. LocalDateTimeAttributeConverter.java
JPA attribute converter for Java 8 date/time:
- Converts `LocalDateTime` to database TIMESTAMP
- Provides bidirectional conversion
- Ensures database compatibility

### Adding New Entities

**Step 1:** Create a new JPA entity class
```java
@Entity
public class Course implements Serializable {
    @Id
    private String courseID;
    
    @Column
    private String courseName;
    
    // Getters and setters
}
```

**Step 2:** Add entity to `persistence.xml` (if needed)
```xml
<class>com.metalop.code.samples.olingo.springbootolingo2sampleproject.entity.Course</class>
```

**Step 3:** Restart the application

**Step 4:** Access via OData
```
http://localhost:8080/odata.svc/Courses
```

### EclipseLink Static Weaving

The project uses **EclipseLink static weaving** for performance optimization:

```xml
<plugin>
    <groupId>de.empulse.eclipselink</groupId>
    <artifactId>staticweave-maven-plugin</artifactId>
    <version>1.0.0</version>
</plugin>
```

**Benefits:**
- ✅ Enhanced lazy loading performance
- ✅ Optimized change tracking
- ✅ Better fetch group support
- ✅ Reduced runtime overhead

### Custom Query Implementation

To implement custom queries, extend `CustomODataJPAProcessor`:

```java
@Override
public ODataResponse readEntitySet(GetEntitySetUriInfo uriInfo, String contentType) 
        throws ODataException {
    // Custom logic here
    return super.readEntitySet(uriInfo, contentType);
}
```

---

## 🐛 Troubleshooting

### Common Issues and Solutions

#### Issue 1: Port 8080 Already in Use
**Solution:** Change the port in `application.properties`
```properties
server.port=8081
```

#### Issue 2: Database Connection Error
**Symptoms:** Application fails to start with connection errors

**Solutions:**
- Verify database credentials in `application.properties`
- Ensure MySQL server is running (if using MySQL)
- Check firewall settings
- Verify database exists: `CREATE DATABASE testdb;`

#### Issue 3: Static Weaving Errors
**Symptoms:** Build fails during the weave phase

**Solutions:**
```bash
mvn clean install -U
```
- Ensure `persistence.xml` is properly configured
- Check entity class annotations

#### Issue 4: ClassNotFoundException
**Symptoms:** Runtime errors about missing classes

**Solutions:**
```bash
mvn clean install
mvn dependency:tree  # Check dependency conflicts
```

#### Issue 5: OData Metadata Not Generated
**Symptoms:** `/odata.svc/$metadata` returns 500 error

**Solutions:**
- Verify all entities have `@Entity` annotation
- Check JPA configuration in `persistence.xml`
- Review application logs for detailed error messages
- Ensure entity IDs are properly annotated with `@Id`

### Enable Debug Logging

Add to `application.properties` for detailed logs:
```properties
logging.level.org.apache.olingo=DEBUG
logging.level.com.metalop.code.samples.olingo=DEBUG
logging.level.org.springframework=DEBUG
logging.level.org.eclipse.persistence=FINE
```

### Getting Help

If you encounter issues:
1. Check the [Issues](https://github.com/shivasaxena/spring-boot-olingo2-jpa-sample-project/issues) page
2. Review application logs carefully
3. Enable debug logging
4. Open a new issue with detailed information

---

## 🤝 Contributing

We welcome contributions from the open-source community! Here's how you can help:

### How to Contribute

1. **Fork the repository**
   ```bash
   git clone https://github.com/yourusername/spring-boot-olingo2-jpa-sample-project.git
   ```

2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```

3. **Make your changes**
   - Write clean, documented code
   - Follow existing code style
   - Add unit tests

4. **Commit your changes**
   ```bash
   git commit -m 'Add some amazing feature'
   ```

5. **Push to the branch**
   ```bash
   git push origin feature/amazing-feature
   ```

6. **Open a Pull Request**

### Contribution Guidelines

- ✅ Follow Java coding conventions
- ✅ Write unit tests for new features
- ✅ Update documentation as needed
- ✅ Ensure all tests pass before submitting PR
- ✅ Keep commits atomic and well-described
- ✅ Add comments for complex logic

### Areas for Contribution

- 🔹 Additional entity examples
- 🔹 Advanced OData query examples
- 🔹 Integration tests
- 🔹 Performance optimizations
- 🔹 Documentation improvements
- 🔹 Bug fixes

---

## 📄 License

This project is licensed under the **Apache License 2.0** - see the [LICENSE](LICENSE) file for details.

```
Copyright 2025 Shiva Saxena

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

---

## 📚 Additional Resources

### Official Documentation
- [Apache Olingo 2 Documentation](https://olingo.apache.org/doc/odata2/index.html)
- [OData Protocol v2.0 Specification](https://www.odata.org/documentation/odata-version-2-0/)
- [Spring Boot 2.7.x Reference](https://docs.spring.io/spring-boot/docs/2.7.x/reference/html/)
- [EclipseLink User Guide](https://www.eclipse.org/eclipselink/documentation/)

### Tutorials and Guides
- [OData Query Options](https://www.odata.org/getting-started/basic-tutorial/#queryData)
- [Spring Boot with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Apache CXF Documentation](https://cxf.apache.org/docs/)

### Related Projects
- [Olingo Samples](https://github.com/apache/olingo-odata2)
- [Spring Boot Examples](https://github.com/spring-projects/spring-boot/tree/main/spring-boot-samples)

---

## 👥 Authors and Maintainers

- **Shiva Saxena** - *Initial work* - [shivasaxena](https://github.com/shivasaxena)

---

## 🙏 Acknowledgments

- Apache Olingo team for the excellent OData library
- Spring Boot community for the amazing framework
- EclipseLink team for the robust JPA implementation
- All contributors who help improve this project

---

## 📞 Support and Contact

- **GitHub Issues**: [Report a bug or request a feature](https://github.com/shivasaxena/spring-boot-olingo2-jpa-sample-project/issues)
- **Discussions**: Share ideas and ask questions
- **Pull Requests**: Contribute code improvements

---

<div align="center">

**⭐ If you find this project helpful, please give it a star! ⭐**

Made with ❤️ by the open-source community

</div>
