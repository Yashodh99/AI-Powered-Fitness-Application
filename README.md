#  AI-Powered Fitness Application

An **AI-enhanced fitness tracking platform** built with **Spring Boot**, **React**, **PostgreSQL**, **Eureka**, **RabbitMQ**, **Docker** and **Keycloak**.  
This project helps users track workouts, monitor calories, and receive **AI-powered recommendations** to improve their performance and health goals.

The system follows a **microservices architecture**, ensuring scalability, flexibility, and independent deployment of each service.

---

##  What This Project Does

-  **Tracks user fitness activities** such as workout type, duration, and calories burned.  
-  **Uses AI (Gemini API)** to analyze patterns and provide personalized recommendations.  
-  **Secures user access** with Keycloak (OAuth2).  
-  **Microservices-based architecture** with service discoveryand centralized configuration.  
-  **Integrates RabbitMQ** for real-time, event-driven communication between services.  
-  **Provides a modern and responsive web interface** built using React and Material UI.  

---

##  Tech Stack and Why It’s Used

###  Backend Technologies

| Technology | Purpose | Reason for Use |
|-------------|----------|----------------|
| **Spring Boot** | Core microservice framework | Simplifies backend development and REST API creation. |
| **Spring Cloud** | Microservice ecosystem | Provides service discovery (Eureka), routing (Gateway), and centralized config management. |
| **PostgreSQL** | Relational database | Reliable, scalable, and supports complex data queries. |
| **MongoDB** | NoSQL database | Stores activity logs and flexible AI insights. |
| **RabbitMQ** | Message broker | Handles asynchronous communication between microservices. |
| **Keycloak** | Authentication & authorization | Provides secure login with OAuth2 . |
| **Docker** | Containerization | Makes deployment easier across different environments. |

---

###  Frontend Technologies

| Technology | Purpose | Reason for Use |
|-------------|----------|----------------|
| **React.js** | Frontend framework | Enables fast and modular UI development. |
| **Material UI (MUI)** | UI library | Provides pre-built, responsive, modern components. |
| **Axios** | HTTP client | For communicating securely with backend services. |
| **Redux Toolkit** | State management | Efficient global state handling for user data and sessions. |
| **React Router** | Navigation | Handles routing between different views. |
| **react-oauth2-code-pkce** | OAuth2 client | For secure integration with Keycloak authentication. |

---

