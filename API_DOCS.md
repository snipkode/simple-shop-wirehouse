# Simple Shop Warehouse Management API

Ini adalah RESTful API buat ngaturin sistem inventory gudang toko sederhana.

## Base URL
`http://localhost:8080/api/warehouse`

## Authentication
Gak perlu authentication buat implementasi basic ini.

## API Endpoints

### Items

#### Bikin Item
- **POST** `/api/warehouse/items`
- **Content-Type**: `application/json`
- **Body**:
  ```json
  {
    "name": "Laptop",
    "description": "Laptop performa tinggi",
    "category": "Elektronik",
    "active": true,
    "variants": [
      {
        "name": "i7/16GB RAM",
        "price": 1200.00,
        "initialStock": 10,
        "active": true
      },
      {
        "name": "i5/8GB RAM",
        "price": 800.00,
        "initialStock": 15,
        "active": true
      }
    ]
  }
  ```
- **Response**: `201 Created` - ItemResponse object

#### Get Semua Item
- **GET** `/api/warehouse/items`
- **Response**: `200 OK` - Array of ItemResponse objects

#### Get Item berdasarkan ID
- **GET** `/api/warehouse/items/{id}`
- **Response**: `200 OK` - ItemResponse object

#### Update Item
- **PUT** `/api/warehouse/items/{id}`
- **Content-Type**: `application/json`
- **Body**: Sama kaya create
- **Response**: `200 OK` - Updated ItemResponse object

#### Hapus Item
- **DELETE** `/api/warehouse/items/{id}`
- **Response**: `204 No Content`

### Variants

#### Bikin Variant buat Item
- **POST** `/api/warehouse/items/{itemId}/variants`
- **Content-Type**: `application/json`
- **Body**:
  ```json
  {
    "name": "Merah",
    "price": 29.99,
    "initialStock": 50,
    "active": true
  }
  ```
- **Response**: `201 Created` - VariantResponse object

#### Get Variants buat Item
- **GET** `/api/warehouse/items/{itemId}/variants`
- **Response**: `200 OK` - Array of VariantResponse objects

### Stock

#### Update Stock
- **PUT** `/api/warehouse/stocks/{variantId}`
- **Content-Type**: `application/json`
- **Body**:
  ```json
  {
    "variantId": 1,
    "quantity": 100
  }
  ```
- **Response**: `200 OK` - StockResponse object

#### Get Stock berdasarkan Variant
- **GET** `/api/warehouse/stocks/{variantId}`
- **Response**: `200 OK` - StockResponse object

#### Reserve Stock (Cegah jual barang kalo stock abis)
- **POST** `/api/warehouse/stocks/{variantId}/reserve?quantity=5`
- **Response**: `200 OK` - Boolean yang nunjukin success

#### Update Stock Quantity
- **PUT** `/api/warehouse/stocks/{variantId}/quantity?quantityChange=10`
- **Response**: `200 OK` - Boolean yang nunjukin success

## Contoh Penggunaan

### 1. Bikin item ama variants
```bash
curl -X POST http://localhost:8080/api/warehouse/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Kaos",
    "description": "Kaos Katun",
    "category": "Pakaian",
    "active": true,
    "variants": [
      {
        "name": "S/Biru",
        "price": 19.99,
        "initialStock": 20,
        "active": true
      },
      {
        "name": "M/Merah",
        "price": 19.99,
        "initialStock": 15,
        "active": true
      }
    ]
  }'
```

### 2. Get semua item
```bash
curl -X GET http://localhost:8080/api/warehouse/items
```

### 3. Reserve stock buat order
```bash
curl -X POST "http://localhost:8080/api/warehouse/stocks/1/reserve?quantity=2"
```

### 4. Update stock setelah nerima inventory baru
```bash
curl -X PUT "http://localhost:8080/api/warehouse/stocks/1/quantity?quantityChange=50"
```

## Error Handling

API ini return HTTP status code yang bener:
- `200 OK` - Request berhasil
- `201 Created` - Resource berhasil dibikin
- `204 No Content` - Resource berhasil dihapus
- `400 Bad Request` - Data request gak valid
- `404 Not Found` - Resource gak ketemu
- `409 Conflict` - Pelanggaran business rule (misalnya: stock kurang)
- `500 Internal Server Error` - Server error

Format response error:
```json
{
  "status": 404,
  "message": "Item gak ketemu id: 999"
}
```

## Database

Aplikasi make database H2 in-memory yang udah di-config otomatis. Schema dibikin pas startup.