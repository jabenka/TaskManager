# **Task Manager**  

## **Overview**  
The **Task Manager** is a **microservice-based** system designed for **managing user tasks** with full **CRUD operations**, **deadline notifications**, and **JWT-based authentication**.  

Built on a **Kubernetes (k3d) cluster**, it leverages:  
✔ **Spring Cloud Gateway** – API routing & load balancing  
✔ **Eureka Discovery Service** – Dynamic service registration & discovery  
✔ **RabbitMQ** – Event-driven deadline notifications  
✔ **Prometheus + Grafana** – Real-time metrics & monitoring  
✔ **PostgreSQL** – Persistent & reliable task storage  

---

## **📌 Key Features**  

### **1. Task Management**  
- ✅ **Create, Update, Delete, and Fetch** user tasks  
- ✅ **Deadline tracking** with **Email notifications** using **RabbitMQ** 
- ✅ **User-specific task retrieval**  

### **2. Authentication & Security**  
- 🔑 **Custom JWT-based Auth Service**  
- 🔄 **Token validation & refresh mechanisms**  

### **3. Service Discovery & API Gateway**  
- 🌐 **Eureka Server** – Auto-registration of microservices  
- 🚪 **Spring Cloud Gateway** – Single entry point for all APIs  
  - 🔄 Load balancing  
  - 🔐 JWT validation  
  - 🔗 Dynamic routing  

### **4. Monitoring & Observability**  
- 📊 **Prometheus** – Metrics collection (HTTP requests, DB queries, JVM stats)  
- 📈 **Grafana** – Custom dashboards for system health  
- 🚨 **Alerts** on service failures or performance degradation  

### **5. Event-Driven Notifications**  
- 📨 **RabbitMQ** – Sends alerts when task deadlines expire  
- 🔄 **Dead Letter Queue (DLQ)** – Handles failed messages  

---

## **🏗 Architecture**  

### **🔹 Technology Stack**  
| Component             | Technology                        |
| --------------------- | --------------------------------- |
| **API Gateway**       | Spring Cloud Gateway              |
| **Service Discovery** | Eureka Server                     |
| **Task Service**      | Spring Boot + PostgreSQL+RabbitMQ |
| **Auth Service**      | Spring Security + JWT             |
| **Notifications**     | RabbitMQ                          |
| **Monitoring**        | Prometheus + Grafana              |
| **Containerization**  | Docker + Kubernetes (k3d)         |

### **🔹 Microservices**  
1. **Eureka Service** – Handles service registration & discovery  
2. **Gateway Service** – Performs routing, JWT validation, and load balancing  
3. **Task Service** – Manages user tasks (CRUD, DB interaction)  
4. **Auth Service** – Issues & validates JWT tokens  
5. **Notification Service** – Sends deadline emails via Email using RabbitMQ  

---

## **🚀 Deployment (k3d Cluster)**  

### **Prerequisites**  
- **k3d** (Lightweight Kubernetes)  
- **Docker**  

### **Steps**  
1. **Start k3d cluster**  
   ```bash
   k3d cluster create mycluster -p "1080:80@loadbalancer" -p "9090:9090@loadbalancer"
   ```
2. Run the provided `script.sh` in the `deployment/k3d/` directory to build Docker images, import them into the cluster, and apply Kubernetes manifests
---

## **🔐 Authentication Flow**  
1. **User logs in** → Auth Service returns **JWT**  
2. **JWT is sent in headers** for subsequent requests  
3. **Spring Gateway validates token** before routing  
4. **Task Service processes requests** only if authorized  

---
