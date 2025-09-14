# 🚍 Driver Dashboard Backend – Project Flow

This document describes the **project architecture, modules, and API flow** for the Driver Dashboard application.

---

## 📂 Project Directory Structure

(*tentative / subject to change / for reference*)

```
driver-dashboard/
├── src/
│   ├── main/
│   │   ├── java/com/nimblix/driverdashboard/
│   │   │   ├── overview/
│   │   │   │   ├── controller/
│   │   │   │   │   └── OverviewController.java
│   │   │   │   ├── service/
│   │   │   │   │   └── OverviewService.java
│   │   │   │   ├── repository/
│   │   │   │   │   └── VehicleStatusRepository.java
│   │   │   │   ├── dto/
│   │   │   │   │   └── OverviewResponse.java
│   │   │   │   └── model/
│   │   │   │       └── VehicleStatus.java
│   │   │   │
│   │   │   ├── fuelandcost/
│   │   │   │   ├── controller/
│   │   │   │   │   └── FuelCostController.java
│   │   │   │   ├── service/
│   │   │   │   │   └── FuelCostService.java
│   │   │   │   ├── repository/
│   │   │   │   │   └── FuelLogRepository.java
│   │   │   │   ├── dto/
│   │   │   │   │   ├── FuelLogDto.java
│   │   │   │   │   └── FuelSummaryDto.java
│   │   │   │   └── model/
│   │   │   │       └── FuelLog.java
│   │   │   │
│   │   │   ├── maintenance/
│   │   │   │   ├── controller/
│   │   │   │   │   └── MaintenanceController.java
│   │   │   │   ├── service/
│   │   │   │   │   └── MaintenanceService.java
│   │   │   │   ├── repository/
│   │   │   │   │   └── MaintenanceRepository.java
│   │   │   │   ├── dto/
│   │   │   │   │   ├── MaintenanceItemDto.java
│   │   │   │   │   └── MaintenanceSummaryDto.java
│   │   │   │   └── model/
│   │   │   │       └── MaintenanceItem.java
│   │   │   │
│   │   │   ├── announcements/   ✅ NEW MODULE
│   │   │   │   ├── controller/
│   │   │   │   │   └── AnnouncementController.java
│   │   │   │   ├── service/
│   │   │   │   │   └── AnnouncementService.java
│   │   │   │   ├── repository/
│   │   │   │   │   └── AnnouncementRepository.java
│   │   │   │   ├── dto/
│   │   │   │   │   └── AnnouncementInfoDto.java
│   │   │   │   └── model/
│   │   │   │       └── Announcement.java
│   │   │   │
│   │   │   └── common/
│   │   │       └── (shared utils, exceptions, constants)
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/ (unit tests here)
│
├── pom.xml
└── README.md
```

---

## 🔹 1. Modules Overview

### 1. Overview Module

* Provides driver’s overview: driver info, performance, vehicle status.
* **APIs**:

  * `GET /api/non-teaching-staff/drivers/{driverId}/overview`
  * `GET /api/non-teaching-staff/drivers/employee/{employeeId}/overview`
  * `GET /api/non-teaching-staff/drivers/vehicle/{vehicleNumber}/overview`

---

### 2. Maintenance Module

* Tracks maintenance items and summaries.
* **APIs**:

  * `GET /api/maintenance/{vehicleNumber}` → List of maintenance items
  * `GET /api/maintenance/{vehicleNumber}/summary` → Urgent / Scheduled / Completed counts
  * `POST /api/maintenance` → Add maintenance record

---

### 3. Fuel & Cost Module

* Manages fuel logs and consumption analysis.
* **APIs**:

  * `GET /api/fuel/logs/{vehicleNumber}` → Fetch recent logs
  * `GET /api/fuel/summary/{vehicleNumber}` → Fuel consumption & cost analysis
  * `POST /api/fuel/logs` → Add new fuel log

---

### 4. Announcements Module (**Common Across All Pages**)

* Centralized announcements for all drivers/pages.
* **APIs**:

  * `GET /api/announcements/recent` → Latest announcements
  * `POST /api/announcements` → Create new announcement

👉 Announcements are **NOT inside Overview anymore**, they are **separate** and reused across all modules.

---

## 🔹 2. Data Flow

1. **Frontend → Backend**

   * Frontend triggers REST calls (`fetch`/`axios`).
   * Example:

     ```http
     GET /api/non-teaching-staff/drivers/driver-001/overview
     ```

2. **Backend → Database**

   * Controller → Service → Repository (Spring Data JPA → DB).

3. **Backend → Frontend**

   * Response mapped to **DTOs** (e.g., `OverviewResponse`, `MaintenanceItemDto`, `FuelSummaryDto`, `AnnouncementInfoDto`).
   * Frontend renders the response.

---

## 🔹 3. Database Tables

* **Driver** → driver\_id, name, phone, shift, department
* **Vehicle** → vehicle\_number, engine\_health, fuel\_level, mileage\_today
* **Vehicle\_Status / Tracking** → driver\_id, vehicle\_number, engine\_status, fuel\_level, odometer, timestamp
* **Maintenance** → id, vehicle\_number, title, category, priority, action, status, estimated\_cost
* **Fuel\_Log** → id, vehicle\_number, station, liters, cost, odometer\_km, date, driver\_id
* **Announcement** → id, title, content, type, priority, target\_class, created\_at

---

## 🔹 4. Frontend Usage

* **Overview Page**

  * Calls: `/overview/{driverId}`
  * Also fetches: `/announcements/recent`
* **Maintenance Page**

  * Calls: `/maintenance/{vehicleNumber}`, `/maintenance/{vehicleNumber}/summary`
  * Also fetches: `/announcements/recent`
* **Fuel Page**

  * Calls: `/fuel/logs/{vehicleNumber}`, `/fuel/summary/{vehicleNumber}`
  * Also fetches: `/announcements/recent`

👉 Announcements API is **shared**.

---

## 🔹 5. Project Flow Diagram

```
[Frontend UI] 
    ↓ REST API Calls
[Spring Boot Controller] 
    ↓
[Service Layer] 
    ↓
[Repository (JPA)]
    ↓
[Database]
```

---

## 🔹 6. Testing Flow

* Use **Postman** to verify APIs.
* Ensure responses match **frontend contract**.
* If Postman ✅ but frontend ❌ → issue is frontend integration mismatch.

---

## ✅ Summary

* Announcements are **separate module** (not tied to Overview).
* Each module (Overview, Maintenance, Fuel, Announcements) has **independent endpoints**.
* Backend tested in **Postman** ensures correctness.
* Frontend must align with backend response structure.

---
