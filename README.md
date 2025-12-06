# Simple Shop Warehouse Management System

RESTful API buat ngaturin gudang toko sederhana make Spring Boot.

## Overview

Aplikasi ini nyediain solusi manajemen gudang lengkap buat tracking item, variants-nya (misalnya ukuran ato warna beda), harga, ama jumlah stok. Sistem ini ngepastiin item gak bisa dijual kalo stock-nya abis.

## Fitur-fitur

- **Item Management**: Bikin, liat, update, ama hapus item
- **Variant Management**: Item bisa punya banyak variant (misalnya: ukuran S, M, L)
- **Pricing**: Tiap item/variant punya harga beda
- **Stock Tracking**: Track jumlah stock buat item ama variants-nya
- **Stock Validation**: Gak boleh jual kalo stock abis
- **RESTful API**: Make prinsip REST buat semua endpoint
- **Data Persistence**: Make H2 database in-memory buat setup cepet

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- Maven

## Cara Jalanin Aplikasi

### Prerequisites

- Java 17 ke atas
- Maven 3.6.0 ke atas

### Running the Application

1. Clone repo ini (ato download source code)
2. Masuk ke folder project root
3. Jalanin aplikasi make Maven:

```bash
mvn spring-boot:run
```

Atau build ama jalanin file JAR:

```bash
mvn clean package
java -jar target/simpleshop-0.0.1-SNAPSHOT.jar
```

### Alternative: Make IDE

Kalo make IDE:
- Import project sebagai Maven project
- Jalanin class `SimpleshopApplication` sebagai Java application

### Akses Aplikasi

- Aplikasi jalan di: `http://localhost:8080`
- H2 Console: `http://localhost:8080/h2-console` (udah di-config buat development)

## API Documentation

Documentation API ada di: [API Documentation](API_DOCUMENTATION.md)

## Design Decisions

### 1. Struktur Domain Model
- **Item**: Produk utama (misalnya: "Kaos")
- **Variant**: Versi spesifik item (misalnya: "Kaos XL/Merah")
- **Stock**: Track jumlah stok buat tiap variant

### 2. Relasi Entity
- Satu Item bisa punya banyak Variants (One-to-Many)
- Satu Variant punya satu Stock record (One-to-One)

### 3. Desain Database
- Make H2 in-memory database buat setup ama testing gampang
- JPA make auto DDL generation buat otomatis bikin schema
- Make soft deletion buat item (active flag, gak hard delete)

### 4. Manajemen Stock
- Method stock reservation buat gak ada race condition pas checkout
- Validation biar jumlah stock gak minus
- Endpoint sendiri buat adjust stock

### 5. Error Handling
- Custom exception class buat error business logic
- Global exception handler buat response error konsisten
- HTTP status code yang bener buat berbagai kasus

### 6. Validasi
- Input validation make Spring validation
- Business logic validation di layer service
- Stock validation buat cegah overselling

### 7. Arsitektur
- Layered architecture (Controller -> Service -> Repository -> Entity)
- DTOs buat API request/response biar gak nge-ekspos internal model
- Dependency injection buat loose coupling

## Asumsi-asumsi

1. Item bisa ada tanpa variants (walaupun umumnya punya variants)
2. Kalo udah dibikin variants, gak bisa dipindahin ke item lain
3. Stock reservation langsung ngurangin stok yang tersedia
4. Cuma item ama variant aktif yang ditampilin di list
5. Make soft deletion buat maintain data integrity ama history

## Fitur-fitur Tambahan

- H2 console buat liat database pas development
- Comprehensive exception handling
- Input validation
- Stock reservation buat cegah overselling
- Proper transaction management
- Lombok buat kurangi boilerplate code
- RESTful endpoint design

## Swagger Documentation

Aplikasi ini include Swagger UI buat dokumentasi API yang interaktif:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html` - Interface web buat explore ama test API endpoints
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs` - OpenAPI 3.0 specification dalam format JSON
- **OpenAPI YAML**: `openapi.yaml` - OpenAPI 3.0 specification dalam format YAML (file static)

Swagger UI provide dokumentasi lengkap buat semua endpoints, termasuk request/response schemas, HTTP methods, ama parameter details.

## Testing

Test unit ama integration udah dimasukin buat verifikasi core functionality. Jalanin test make:

```bash
mvn test
```

## POSTMAN COLLECTION
- Included Postman JSON Collection **SimpleShopWarehouse.postman_collection** ready to import to quick Test


## NEXT DEVELOPMENT
- Integration microservices with Containerization Docker 
- Swagger Implementation 
- AI Integration to create Fraud Detection.
- CI/CD Integration Hooks with Github Runner or Jenkins