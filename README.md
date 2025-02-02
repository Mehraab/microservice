High-Level Architecture
API Gateway
Routes incoming requests to the appropriate microservice.
Handles authentication and authorization via Keycloak.
Integrates with Consul for service discovery.
Services
Order Service:
Handles order creation and management.
Reads/writes to the shared order table.
Publishes events like OrderPlaced, OrderProcessed, OrderCancelled.
Inventory Service:
Manages inventory items.
Checks stock availability during order placement.
Updates the order table for stock confirmation.
Publishes events like  OutOfStock, OrderNotFound,OrderConfirmed.
Payment Service:
Handles payment processing.
Reads the order table for total amounts.
Updates the payment table with payment details.
Publishes events like PaymentSuccess, MissingPaymentMethod,UnconfirmedOrder,InsufficientAmount,InvalidPaymentRequest.
Notification Service:
Listens to all events from other services.
Sends notifications to users (e.g., via email, SMS, etc.).
Shared Components
Keycloak: Manages user authentication and roles.
Kafka: Event bus for communication between services.
Consul: Service discovery for microservices.
Shared Database:
Tables: order, inventory, payment.






Project Plan
Step 1: Set Up Base Architecture
Create a Maven/Gradle project for each microservice.
Integrate Consul for service discovery.
Configure Keycloak for authentication/authorization.
Set up Kafka for event streaming.
Step 2: Build Individual Services
API Gateway:
Route requests to services based on endpoints.
Validate JWT tokens using Keycloak.
Order Service:
Create order table with fields like order_id, userId,itemId, status, totalAmount.
Handle order placement.
Publish OrderPlaced events.
Inventory Service:
Create inventory table with fields like id, itemName, quantity, price.
Handle stock checking during order placement.
Update order table for stock confirmation.
Payment Service:
Create payment table with fields like id, orderId, userId, amount, status.
Process payments and validate against the order table.
Notification Service:
Listen to all Kafka events.
Send notifications to users.





Endpoints
API Gateway
	Order APIs
POST /api/orders/place  
GET /api/orders/getAll
GET /api/orders/get-for-one-user 
GET /api/orders/get-one-order?orderId=
	Inventory APIs
POST /api/inventory/create 
PUT /api/inventory/update
GET /api/inventory/getAll
GET /api/inventory/getOne?itemId=
GET /api/inventory/delete-one?itemId=
Payment APIs
POST /api/payments/create 
Notification APIs
GET /api/notification/getAll
GET /api/notification/get-for-one-user

