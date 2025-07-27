# StayEase 🏨

**StayEase** is a Java-based hotel management system designed to handle room bookings, status tracking, and checkout operations. Built with a Swing GUI and MySQL backend, it demonstrates clean object-oriented design principles and practical database integration using JDBC.

---

## ✨ Features

- 🛏️ Book and checkout rooms (10 rooms supported)
- 📋 View current room status
- 🎛️ Interactive GUI built with Java Swing
- 💾 Persistent data storage using MySQL (JDBC)
- 🔐 Clean OOP structure using encapsulation and abstraction

---

## 🛠️ Tech Stack

| Technology | Usage                     |
|------------|---------------------------|
| Java       | Core development language |
| Swing      | GUI interface              |
| MySQL      | Database engine            |
| JDBC       | Java–DB connectivity       |
| OOP        | Project architecture       |

---

## 🧠 OOP Implementation

StayEase leverages key Object-Oriented Programming principles:

### 🔒 Encapsulation
- All room data is encapsulated in a `Room` class.
- Room status is updated via controlled methods (`book()`, `checkout()`), not direct access.

### 🎯 Abstraction
- The UI and main logic use high-level functions like `bookRoom()` or `showStatus()` without knowing internal logic.
- Complex logic is hidden inside classes.

### 🧱 Class Structure
- `Room` – defines properties and methods for a hotel room.
- `HotelManager` – manages an array/list of `Room` objects and central logic.
- `StayEaseUI` – handles the GUI using Swing components.

---

## ⚙️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/Sahana0610/StayEase.git
cd StayEase
