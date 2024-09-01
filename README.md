# Hospitality-Management-System

## Overview

The **Hospitality Management System** is a comprehensive solution designed to streamline operations in the hospitality industry. This application provides tools for managing hotels, rooms, guests, and reservations, making it easier to handle day-to-day tasks efficiently.

## Features

- **Hotel Management**: Add and manage hotels, including hotel details, available services, and amenities.
- **Room Management**: Handle room assignments, room types, availability, and pricing.
- **Guest Management**: Register guests, manage their details, and track their stay history.
- **Reservation Management**: Create, modify, and cancel reservations with real-time availability checks.
- **User-Friendly Interface**: Java Swing-based GUI for easy navigation and interaction.

## Technology Stack

- **Frontend**: Java Swing
- **Backend**: Java
- **Database**: MySQL
- **IDE**: IntelliJ IDEA / Eclipse
- **Version Control**: Git

## Installation

### Prerequisites

- **Java Development Kit (JDK) 8 or higher**
- **MySQL Server**
- **IDE**: IntelliJ IDEA, Eclipse, or any other Java-supported IDE

### Steps

1. **Clone the repository**:
    ```bash
    git clone https://github.com/AtharvBorle/Hospitality-Management-System.git
    cd Hospitality-Management-System
    ```

2. **Set up the database**:
   - Create a MySQL database named `hospitality_db`.
   - Import the provided SQL script (`/sql/hospitality_db.sql`) to set up the database schema and initial data.

3. **Configure the database connection**:
   - In the `src/main/resources/db.properties` file, update the database connection details:
     ```properties
     db.url=jdbc:mysql://localhost:3306/hospitality_db
     db.username=your_username
     db.password=your_password
     ```

4. **Build and run the application**:
   - Open the project in your preferred IDE.
   - Build the project to resolve dependencies.
   - Run the `Main.java` file located in `src/main/java/com/hospitality` to start the application.

## Usage

- **Hotel Management**: Navigate to the Hotel Management section to add or edit hotel details.
- **Room Management**: Access the Room Management section to manage room details, types, and availability.
- **Guest Management**: Register new guests or update existing guest information.
- **Reservation Management**: Make new reservations, update existing ones, or cancel bookings.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Contact

For any inquiries, you can reach me at:
- **Email**: atharvbborle@gmail.com

---

**Note**: Replace placeholders like `yourusername` and `password` with your actual username and password.
