
---

## 📡 API Endpoints

### 🧾 Order Service

| Method | Endpoint         | Description                |
|--------|------------------|----------------------------|
| POST   | `/api/orders`    | Create a new order         |
| GET    | `/api/orders/{id}` | **Not available yet**      |

Currently, **no API is implemented for retrieving order details** (i.e., the `GET /api/orders/{id}` endpoint is not available).

---

## 📬 Email Notification

- Uses **Spring Mail** to send emails through Gmail.
- **App password** is used for Gmail authentication.

---

## 🚀 Features

- 📦 **Decoupled architecture** using Kafka for event streaming
- 📡 **Real-time updates** for inventory and payment handling
- ✉️ **Email notification** system for order status
- 🧪 Easy to scale and test each microservice independently

---

## 🛠️ Running Locally

1. **Start Kafka locally** (ensure Kafka and Zookeeper are running):
   - Follow instructions to set up **Kafka** and **Zookeeper** locally.
2. **Run each microservice individually**:
   - Each service can be run via Spring Boot. Ensure the correct port configurations for each service.
3. **Use Postman or any HTTP client**:
   - Hit `POST /api/orders` to simulate order creation. This will trigger Kafka events and subsequent processing.

---

## 🧑‍💻 Author

Built with ❤️ by Aryan
