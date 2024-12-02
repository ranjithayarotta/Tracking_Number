# Tracking Number

 # Tracking Number Generator API

 This is a Spring Boot application providing an API for creating unique tracking numbers for orders. Tracking numbers are alphanumeric and conform to a specific pattern in the following. The application will enforce generated tracking numbers against a provided regex pattern and supports many concurrent requests.

 ## API Specification

 ### **GET /next-tracking-number**

This API returns a unique tracking number based on the query parameters passed.

#### Query Parameters:
- `origin_country_id` (Required): The origin country code in ISO 3166-1 alpha-2 format (e.g., "MY").
- `destination_country_id` (Required): The destination country code in ISO 3166-1 alpha-2 format (e.g., "ID").
- `weight` (Required): The order's weight in kilograms (up to 3 decimal places) (e.g., "1.234").
- `created_at` (Required): The order's creation timestamp in RFC 3339 format (e.g., "2018-11-20T19:29:32+08:00").
- `customer_id`: The UUID of the customer, for example: "de619854-b59b-425e-9db4-943979e1bd49".
- `customer_name`: The name of the customer, for example: "RedBox Logistics".
- `customer_slug`: The name of the customer in slug-case/kebab-case, for example: "redbox-logistics".
# Tracking Number

 # Tracking Number Generator API

 This is a Spring Boot application providing an API for creating unique tracking numbers for orders. Tracking numbers are alphanumeric and conform to a specific pattern in the following. The application will enforce generated tracking numbers against a provided regex pattern and supports many concurrent requests.

 ## API Specification

 ### **GET /next-tracking-number**

This API returns a unique tracking number based on the query parameters passed.

#### Query Parameters:
- `origin_country_id` (Required): The origin country code in ISO 3166-1 alpha-2 format (e.g., "MY").
- `destination_country_id` (Required): The destination country code in ISO 3166-1 alpha-2 format (e.g., "ID").
- `weight` (Required): The order's weight in kilograms (up to 3 decimal places) (e.g., "1.234").
- `created_at` (Required): The order's creation timestamp in RFC 3339 format (e.g., "2018-11-20T19:29:32+08:00").
- `customer_id`: The UUID of the customer, for example: "de619854-b59b-425e-9db4-943979e1bd49".
- `customer_name`: The name of the customer, for example: "RedBox Logistics".
- `customer_slug`: The name of the customer in slug-case/kebab-case, for example: "redbox-logistics".

### **Response Example:**
```json
{
  "trackingNumber": "4E9BCB63519B4C3F"
}

### **Response Example:**
```json
{
  "trackingNumber": "4E9BCB63519B4C3F"
}
