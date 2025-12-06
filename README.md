# SimpleShop – Warehouse Management API
A simple warehouse management backend system built using **Java 17**, **Spring Boot 3.x**, and **H2 Database**.  

Project ini dibuat sebagai assessment Backend Developer untuk mengelola:

- Items: The shop sells items that need to be tracked
- Variants: Items can have variants (e.g., different sizes, colors)
- Pricing: Each item/variant has a price
- Stock: Track inventory levels for items and their variants

Project ini didesain agar mudah dijalankan menggunakan **Eclipse IDE**.


# 🛠 Technology Stack & Dependencies

| Component | Version |
|----------|---------|
| Java | **17** |
| Spring Boot | **3.x** |
| Build Tool | Maven |
| Database | H2 (In-Memory) |
| IDE Support | Eclipse / IntelliJ / VSCode |
| JPA | Hibernate |
| Plugin | Lombok, Spring DevTools |

---

# Project Structure

```shell 
src/main/java/com/example/simpleshop/
├── controller/
├── services/
├── repository/
├── entity/
├── dto/
├── exception/
├── config/
├── model/
└── SimpleshopApplication.java
```

# Cara Menjalankan Project

### Karena ingin minim konfigurasi manual, sy menggunakan IDE Eclips :
- Buka IDE Eclips
- Kemudian Import Projects
---
- Jalankan tombol run berwarna hijau pilih opsi **Run As** --> **maven install**
- Update terlebih dahulu artifact dependency untuk menghindary error warning dengan klik kanan mouse untuk memunculkan opsi pilihan **Updates Maven Projects**
---
- Buat Profile Goal Isikan value : **spring-boot:run** kemudian klik save dan jalankan.

### Lain Menggunakan Command Line di Visual Studio Code :
- Download IDE Visual Studio Code
- Download dulu Installer Java JDK versi 17 (Wajib)
- Download Maven Binary
- Setup Environment Variable
- Restart Device (Jika Menggunakan Windows)
- Buka Visual Studio Code
- Jalankan Command Line berikut untuk mendownload dependency:

```shell
mvn install -DskipTests
```

- Jalankan Projects dengan menggunakan Command berikut:
```shell
mvn spring-boot:run
```

