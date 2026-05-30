# PROGIGS

A **freelancing marketplace web application** that connects freelancers and clients. Freelancers can create profiles, apply for jobs, manage portfolios, receive contracts, and get paid. Clients can post jobs, review proposals, hire freelancers, manage contracts, and leave reviews.

Built with **Spring Boot 3.3.2** and **Java 17**.

---

## Tech Stack

| Category          | Technology |
|-------------------|------------|
| Language          | Java 17 |
| Framework         | Spring Boot 3.3.2 |
| Security          | Spring Security + JWT (jjwt 0.12.5) |
| Database          | MySQL (via Spring Data JPA / Hibernate) |
| Email             | Spring Mail (Gmail SMTP) |
| Real-time         | Spring WebSocket |
| File Upload       | Cloudinary |
| API Docs          | Swagger / OpenAPI (springdoc 2.6.0) |
| Mapping           | ModelMapper 3.2.1 |
| Build             | Maven |

---

## Features

### Authentication & User Management
- Register as **FREELANCER** or **CLIENT**
- Email verification via **6-digit OTP**
- JWT-based login (24h token with user ID + role)
- Stateless session, BCrypt password hashing, role-based endpoint authorization

### Profile Management
- Full profile (name, email, phone, skills, hourly rate, company, location)
- **Profile image upload** via Cloudinary
- Education history, certifications, portfolio items (with image upload)
- Bank account details for payouts
- Profile verification workflow (PENDING → VERIFIED / REJECTED)
- Success rate calculation based on completed jobs, ratings, and review count

### Job Management (Client)
- Post jobs with title, description, required skills, duration, budget, payout method
- Job modules with individual invoices and transactions
- Edit / delete jobs
- Categorization by skill domain (Web Dev, Design, Mobile, Data Entry, Marketing, Legal, Finance, etc.)

### Proposal Management (Freelancer)
- Apply to jobs with bid amount, description, timeline
- View all proposals for a job
- Hiring workflow: proposal → HIRED when client creates a contract

### Contract Management
- Create contracts from accepted proposals
- Active contracts listing (client and freelancer views)
- Close contracts with real-time notification to freelancer

### Reviews & Ratings
- Post-job reviews with numeric rating
- Top freelancer ranking by average rating
- Success rate computation

### Job Discovery & Search
- Browse all jobs or latest 5 jobs
- Search by keyword (title, username, location, skills)
- Filter by category, skill, or location
- Applied-jobs tracking for freelancers
- International freelancer discovery

### Real-time Notifications
- WebSocket endpoint at `/ws`
- Per-user sessions (identified by `userId` query param)
- Persistent notifications stored in DB with CRUD

---

## Architecture

```
src/main/java/com/Ntra/PROGIGS/
├── Configration/       # Security, WebSocket, Cloudinary, Swagger config
├── Controller/         # REST controllers (12 controllers)
├── Service/            # Service interfaces
│   └── ServiceImpl/    # Service implementations
├── Repository/         # JPA repositories (13 repos)
├── Entity/             # JPA entities (17 entities + enums)
├── DTO/                # Data transfer objects (23 DTOs)
├── Mapper/             # ModelMapper-based DTO↔Entity mappers
├── Filter/             # JWT authentication filter + auth helper
└── Exception/          # Global exception handler + custom exceptions
```

---

## API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/register` | Register new user |
| POST | `/login` | Authenticate and get JWT |
| POST | `/verify-otp` | Verify email via OTP |

### User
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/user` | List all freelancers |
| GET | `/user/bySkill/{skill}` | Filter freelancers by skill |
| GET | `/user/top-Freelancer` | Top 6 rated freelancers |

### Profile
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/profile/image` | Upload profile image |
| PUT | `/profile/edit` | Edit profile |
| GET | `/profile/getProfile` | Get current user's profile |
| GET | `/profile/by-id/{id}` | Get profile by ID |
| GET | `/profile/success-rate` | Get own success metrics |
| GET | `/profile/get-international-freelancer` | Get non-local freelancers |

### Jobs
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/jobs/addjobs` | Post a new job |
| GET | `/jobs/alljobs` | List all jobs |
| GET | `/jobs/five-jobs` | Latest 4 jobs |
| GET | `/jobs/{id}` | Get job by ID |
| GET | `/jobs/search-jobs/{keyword}` | Search jobs |
| GET | `/jobs/appliedJobs` | Jobs freelancer applied to |
| GET | `/jobs/job-by-catogory/{catogory}` | Filter by category |
| GET | `/jobs/jobs-by-location/{location}` | Filter by location |

### Proposals
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/proposals/{jobid}` | Submit proposal |
| GET | `/proposals/{jobid}` | Get proposals for a job |

### Contracts
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/contract/{proposalid}` | Create contract from proposal |
| GET | `/contract/activeJobs` | List active contracts |
| PUT | `/contract/close/{contractId}` | Close contract |

### Reviews
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/review` | Submit a review |

### Notifications
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/notifications/{userId}` | Get user notifications |
| DELETE | `/api/notifications/{id}` | Delete a notification |

### Profile Sub-resources
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/profile/portfolio` | Add portfolio item |
| POST | `/profile/education` | Add education |
| POST | `/profile/certification` | Add certificate |
| POST | `/profile/bank` | Add bank details |

---

## Setup

### Prerequisites
- Java 17+
- Maven 3.x
- MySQL server
- Cloudinary account (for image uploads)
- Gmail account with app password (for OTP emails)

### Configuration
Set the following in `application.properties` or via environment variables:

```properties
spring.datasource.url=database_url
spring.datasource.username=root
spring.datasource.password=your_password

cloudinary.cloud-name=your_cloud_name
cloudinary.api-key=your_api_key
cloudinary.api-secret=your_api_secret

spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
```

### Run
```bash
./mvnw spring-boot:run
```

The server starts on **port 3031**.

### Swagger UI
```
http://localhost:3031/swagger-ui/
```

---

## Database

- **MySQL** with Hibernate `ddl-auto=update` (auto schema management)
- Dev profile: `application-dev.properties` (localhost:3306/demo1)
- Prod profile: `application-prod.properties`
- Environment variable `ENV` controls active profile

---

## Enums

| Enum | Values |
|------|--------|
| `UserRole` | FREELANCER, CLIENT |
| `Profile_Status` | PENDING, VERIFIED, REJECTED |
| `PropsalStatus` | HIRED |
| `ContractStatus` | ACTIVE, CLOSED |
| `Status` (Job) | ACTIVE, CLOSED, COMPLETED |
| `Pay0ut_Methods` | hourly, sprint, project |

---

## License

Private project — all rights reserved.
