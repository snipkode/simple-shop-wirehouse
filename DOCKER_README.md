## Running with Docker

### Prerequisites
- Docker Desktop or Docker Engine
- Docker Compose

### Running the Application

1. **Build and start the services:**
   ```bash
   docker-compose up --build
   ```

2. **Access the application:**
   - Application: [http://localhost:8080](http://localhost:8080)
   - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - PgAdmin: [http://localhost:5050](http://localhost:5050) (username: admin@simpleshop.com, password: password)

3. **Stop the services:**
   ```bash
   docker-compose down
   ```

### Alternative: Running only the application with H2 database

If you want to run the application with the in-memory H2 database instead of PostgreSQL:

1. Run the application directly:
   ```bash
   ./mvnw spring-boot:run
   ```

2. Or build and run the jar:
   ```bash
   ./mvnw clean package
   java -jar target/simpleshop-0.0.1-SNAPSHOT.jar
   ```