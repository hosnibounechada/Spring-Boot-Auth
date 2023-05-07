# Spring-Boot-Auth
Spring Boot Authentication Template.

# Requirements
- Postgres
- Redis
- Minio (Local S3 Implementation)

# Content:

# Security
Handle security with spring security and JWT with RSA Signature.

# Data Validation
Validate http path variables, query params and request body.

# Exception Handling
Ensure to handle exception and throw meaningful error response with its appropriate Status code

# OpenApi Swagger URL
- Implemented only for local environment
- http://localhost:8080/swagger-ui/index.html

# Redis Implementation
- Use Redis for caching
- Handle user email confirmation
  - key => example@example.com
  - value => Hash { code : 123456, counter : 0}
  - TTL => 5 min

# Netty Socket-io
Handle Real-Time communication between users and server.

# Email Service
- Send 6 digits code to clients for email confirmation.
 Notify users about new features.

# Twilio Service
- Send 6 digits OTP for phone number confirmation.
- Send notification SMS.