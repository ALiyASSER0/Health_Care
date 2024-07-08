# HealthCare 🏥
HealthCare is a Hospital Management System, which offers a comprehensive software solution designed to streamline the administrative, financial, and clinical operations of a hospital. It integrates various functionalities such as patient registration, appointment scheduling, billing, medical records management, and more, to enhance the efficiency and effectiveness of healthcare delivery.

## System Entities 🥼
- Patient
- Doctor
- Head Doctor
- Nurse
- Head Nurse
- Pharmacist
- Human resources

## Features 🚀
- **Verification Codes and Two-Step Authentication:**
    - Enhancing the security of user accounts by requiring an additional verification step during login. This module helps protect user accounts from unauthorized access by sending a verification code to the user's email address during the authentication process. 
    - Integrating with an email service to send verification codes to users.
    - Implementing the two-step authentication flow to enhance account security.
    - Customized email templates for verification codes.

- **Human Resources:**
    - Manages hospital staff, recruitment, and performance. This module ensures that the hospital operates smoothly by maintaining a well-organized and motivated workforce.
    - Responsible for managing `users` and `employees` entities.
    - The relationship between `users` and `employees` is One-to-One(1-1), so each employee has only one user account.

- **Patient Capabilities:**
    - Register and Login: Validate and register user accounts.
    - Clinic Reservations: Book appointments and view the reservation queue.
    - Manage Reservations: View, update, and delete current reservations; access reservation history.
    - Medical Records: Upload and view medical records.

- **Doctor Capabilities:**
    - Clinic Management: List all clinics, view working hours, and update clinic status.
    - Case and Patient Management: Access current cases, view patient details, and medical records.
    - Room Management: View, update, and add room details.
    - Call Management: List, create, and update calls within the system.

- **Nurse Capabilities:**
    - Patient and Clinic Management: Retrieve user IDs, view clinic patients, and access patient details.
    - Room Management: View, update, and add room details.
    - Call Management: List, view, create, and update calls.

- **Pharmacist:**
    - Responsible for managing the hospital's pharmaceutical inventory, prescriptions and orders. This module ensures that the hospital's pharmacy operates efficiently and that patients receive their medications on time.
    - Managing prescriptions issued by doctors, including verification, dispensing medications, and maintaining records.
    - Keeping track of the pharmaceutical inventory, including stock levels.
    - Handling orders for medications and supplies, ensuring timely procurement and delivery.
    - The relationship between `medication` and `medication_categories` is Many-to-Many(M-M), so each medication has many medication categories and vice versa.
    - The relationship between `prescription` and `prescription_items` is Many-to-Many(M-M), so each prescription has many items (medicines) and vice versa.

- **Billing and Payments:**
    - Responsible for managing patient payments, processing credit card transactions and handling invoices.
    - Handling patient payments for services rendered, including credit card transactions.
    - Storing and managing credit card information securely.
    - Verify card details and handle authorization errors.
    - The relationship between `users` and `credit_cards` is One-to-Many(1-M), so each user can have multiple credit cards between each credit card can only belongs to a single user.

- **Real-Time Communication Chat:**
    - Responsible for facilitating real-time interactions between hospital staff, patients, and other stakeholders. This module supports chat messaging to enhance communication and collaboration within the hospital.
    - Instant text communication between users.
    - Secure storage and retrieval of messages with a One-on-one messaging.

## Technologies Used ⚙️
- **UI/UX Design:**
    - Use Android XML for designing screens.
    - Utilize design libraries like Material Design Components for interactive and aesthetic elements.
- **Data Management:**
    - Use Retrofit for network requests and interacting with the backend.
    - Use Room Library for local data storage in SQLite database on the device.
- **Session Management and Authentication:**
    - Use authorization libraries like OAuth or JWT for managing login and authentication.
- **App Testing:**
    - Use appropriate testing tools like JUnit to ensure the app functions correctly according to requirements.
- **Real-Time Communication:**
    - WebSocket - Protocol for real-time communication
    - Daphne - ASGI server for handling HTTP, HTTP2, and WebSocket protocols
    - ASGI - Asynchronous server gateway interface for handling asynchronous requests




## A video describing the project🗃️
- **introduction:**


https://github.com/ALiyASSER0/Health_Care/assets/128180482/90cd1373-3ef7-4789-a2c3-f3bda7e9e761

- **Patient:**

https://github.com/ALiyASSER0/Health_Care/assets/128180482/7ec96c9a-3d58-4c3e-a29c-adc4d399a5f5

- **Doctor:**

https://github.com/ALiyASSER0/Health_Care/assets/128180482/20891f74-8005-4fba-b1a0-2ce448ae8c7f

- **Nurse:**

https://github.com/ALiyASSER0/Health_Care/assets/128180482/1698fcf6-386f-4d63-96e7-5ba25cd81929

- **HR:**

https://github.com/ALiyASSER0/Health_Care/assets/128180482/65e75893-9a2b-4316-aef6-afae79e528fc


## API Documentation 📜
- **Swagger Documentation:**
  - Access the Swagger documentation [*here*](https://gp-mvz0.onrender.com/swagger/).
- **Postman Collection:**
  - Access the Postman collection [*here*](https://documenter.getpostman.com/view/23656997/2sA35G3MtP).

## Back-End Repository 🖌️
The back-end code for this project is developed using Python Django and can be found in the following repository:
[*Back-End Repository*](https://github.com/ShehabM0/HealthCare).

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

