# 🚀 deodorant-reborn-api

> REST API backend for the [deodorant-reborn-platform](https://github.com/DatengTankoua/deodorant-reborn-platform) — the community platform for the [intellij-deodorant-reborn](https://github.com/DatengTankoua/intellij-deodorant-reborn) plugin.

![CI](https://github.com/DatengTankoua/deodorant-reborn-api/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-green)
![Docker](https://img.shields.io/badge/Docker-ready-blue)
![License](https://img.shields.io/badge/license-MIT-blue)

---

## 📋 Table of Contents

- [🚀 deodorant-reborn-api](#-deodorant-reborn-api)
  - [📋 Table of Contents](#-table-of-contents)
  - [What is this?](#what-is-this)
  - [Tech Stack](#tech-stack)
  - [Prerequisites](#prerequisites)
  - [Project Structure](#project-structure)
  - [Getting Started](#getting-started)
    - [1. Clone the repository](#1-clone-the-repository)
    - [2. Set up environment variables](#2-set-up-environment-variables)
    - [3. Start Supabase locally](#3-start-supabase-locally)
    - [4. Run the API](#4-run-the-api)
    - [5. Run with Docker](#5-run-with-docker)
  - [Database](#database)
    - [Sync production to local](#sync-production-to-local)
    - [Create a new migration](#create-a-new-migration)
    - [Push migrations to production](#push-migrations-to-production)
  - [API Endpoints](#api-endpoints)
  - [Daily Workflow](#daily-workflow)
  - [Deployment](#deployment)
    - [First deployment](#first-deployment)
    - [Automatic deployments](#automatic-deployments)
  - [Contributing](#contributing)
  - [License](#license)
  - [Author](#author)
  - [Related Repositories](#related-repositories)

---

## What is this?

This is the **Spring Boot REST API** that powers the deodorant-reborn-platform website.

It handles:
- 📝 User feedback submissions
- 💡 Feature requests from the community
- 📊 Plugin download tracking
- 🔒 Secure endpoints with Spring Security

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming language |
| Spring Boot 3.3 | REST API framework |
| Spring Data JPA | Database access |
| Spring Security | API security |
| PostgreSQL | Database (via Supabase) |
| Supabase | Hosted PostgreSQL + local dev |
| Docker | Containerization |
| Docker Compose | Local orchestration |
| GitHub Actions | CI/CD pipeline |
| Swagger/OpenAPI | API documentation |
| Lombok | Reduce boilerplate code |
| Gradle | Build tool |

---

## Prerequisites

Before you start, make sure you have the following installed:

| Tool | Version | Download |
|------|---------|----------|
| Java JDK | 21+ | [adoptium.net](https://adoptium.net) |
| Docker Desktop | Latest | [docker.com](https://docker.com) |
| Supabase CLI | Latest | [supabase.com/docs/guides/cli](https://supabase.com/docs/guides/cli) |
| Git | Latest | [git-scm.com](https://git-scm.com) |

**Install Supabase CLI:**
```bash
npm install -g supabase
```

**Verify everything is installed:**
```bash
java --version        # Should show 21+
docker --version      # Should show latest
supabase --version    # Should show latest
git --version         # Should show latest
```

---

## Project Structure

```
deodorant-reborn-api/
 instructions
│   └── workflows/
│       ├── ci.yml                  # CI pipeline (lint, test, build)
│       └── release.yml             # Auto-release on tag push
├── supabase/
│   ├── config.toml                 # Supabase local configuration
│   ├── migrations/                 # SQL migration files
│   │   └── TIMESTAMP_name.sql      # One file per migration
│   └── seed.sql                    # Test data for local development
├── src/
│   └── main/
│       ├── java/com/deodorantreborn/api/
|       |   ├── featurerequests/
|       |   |   ├── controller/         # REST endpoints
|       |   |   ├── service/            # Business logic
|       |   |   ├── repository/         # Database access
|       |   |   ├── entity/             # JPA database entities
|       |   |   └── dto/
|       |   |       ├── request/        # Incoming request DTOs
|       |   |       └── response/       # Outgoing response DTOs
│       │   ├── feedback/
│       │   |   ├── controller/         # REST endpoints
│       │   |   ├── service/            # Business logic
│       │   |   ├── repository/         # Database access
│       │   |   ├── entity/             # JPA database entities
│       │   |   └── dto/
│       │   |       ├── request/        # Incoming request DTOs
│       │   |       └── response/       # Outgoing response DTOs 
│       │   ├── plugindownload/
│       │   |   ├── controller/         # REST endpoints
│       │   |   ├── service/            # Business logic
│       │   |   ├── repository/         # Database access
│       │   |   ├── entity/             # JPA database entities
│       │   |   └── dto/
│       │   |       ├── request/        # Incoming request DTOs
│       │   |       └── response/       # Outgoing response DTOs
│       │   ├── exception/          # Error handling
│       │   ├── config/             # Spring configuration
│       │   └── ApiApplication.java # Main entry point
│       └── resources/
│           └── application.yml     # App configuration
├── src/test/                       # Unit and integration tests
├── Dockerfile                      # Docker image definition
├── docker-compose.yml              # Local Docker setup
├── .env.example                    # Environment variables template
├── .gitignore
└── README.md
```

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/DatengTankoua/deodorant-reborn-api.git
cd deodorant-reborn-api
```

---

### 2. Set up environment variables

Copy the example file and fill in your values:

```bash
cp .env.example .env
```

Open `.env` and fill in your values:

```env
# Local Supabase (used during development)
SUPABASE_DB_URL=jdbc:postgresql://localhost:54322/postgres
SUPABASE_DB_USER=postgres
SUPABASE_DB_PASSWORD=postgres

# Allowed origins for CORS
CORS_ALLOWED_ORIGINS=http://localhost:3000
```

> ⚠️ **Never commit your `.env` file.** It is already in `.gitignore`.

---

### 3. Start Supabase locally

Supabase runs a full PostgreSQL database on your machine using Docker.

**Step 1 — Log in to Supabase (first time only):**
```bash
supabase login
```
This opens your browser. Log in and paste the token in the terminal.

**Step 2 — Link to the production project (first time only):**
```bash
supabase link --project-ref YOUR_PROJECT_REF
```
Find your Project Reference ID at:
**supabase.com → Your Project → Settings → General → Reference ID**

**Step 3 — Pull the production schema:**
```bash
supabase db pull --password YOUR_DB_PASSWORD
```
This creates migration files in `supabase/migrations/` matching your production tables.

**Step 4 — Start Supabase local:**
```bash
supabase start
```
Wait about 2 minutes. You will see:

```
API URL:    http://localhost:54321
DB URL:     postgresql://postgres:postgres@localhost:54322/postgres
Studio URL: http://localhost:54323   ← visual database interface
anon key:   eyJhbGc...
```

**Step 5 — Apply migrations and seed data:**
```bash
supabase db reset
```
This resets the local database, applies all migrations, and inserts the seed data from `supabase/seed.sql`.

**Step 6 — Open Supabase Studio:**

Go to **http://localhost:54323** in your browser to see and manage your local database visually — just like the Supabase dashboard online.

---

### 4. Run the API

Make sure Supabase local is running, then:

```bash
# On Windows
.\gradlew.bat bootRun

# On Mac/Linux
./gradlew bootRun
```

You should see:
```
Started ApiApplication in X.XXX seconds
```

**Verify it works:**
- Health check: http://localhost:8080/actuator/health → `{"status":"UP"}`
- Swagger UI: http://localhost:8080/swagger-ui
- API Docs: http://localhost:8080/api-docs

---

### 5. Run with Docker

Make sure **Docker Desktop is running** before this step.

```bash
# Build and start the API container
docker-compose up --build
```

To run in the background:
```bash
docker-compose up --build -d
```

To stop:
```bash
docker-compose down
```

To see logs:
```bash
docker-compose logs -f api
```

> 💡 When using Docker, Supabase still needs to be started separately with `supabase start`.

---

## Database

### Sync production to local

If someone changed the production database schema, pull the changes:

```bash
supabase db pull --password YOUR_DB_PASSWORD
supabase db reset
```

### Create a new migration

When you need to add or modify a table:

```bash
# Create a new migration file
supabase migration new describe_what_you_are_changing
```

This creates a file like `supabase/migrations/20240101120000_describe_what_you_are_changing.sql`.

Open it and write your SQL:

```sql
-- Example: add a new column
ALTER TABLE feedback ADD COLUMN is_public BOOLEAN DEFAULT true;
```

Apply it locally:

```bash
supabase db reset
```

### Push migrations to production

When you are ready to apply your changes to production:

```bash
supabase db push
```

> ⚠️ Always test migrations locally before pushing to production.

---

## API Endpoints

Once the app is running, visit **http://localhost:8080/swagger-ui** for the full interactive API documentation.

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/actuator/health` | Health check |
| `POST` | `/api/feedback` | Submit feedback |
| `GET` | `/api/feedback` | Get all feedback |
| `POST` | `/api/feature-requests` | Submit a feature request |
| `GET` | `/api/feature-requests` | Get all feature requests |
| `POST` | `/api/plugin/download` | Track a plugin download |
| `GET` | `/api/plugin/stats` | Get download statistics |

**Example request:**
```bash
curl -X POST http://localhost:8080/api/feedback \
  -H "Content-Type: application/json" \
  -d '{
    "email": "dev@example.com",
    "message": "Great plugin!",
    "rating": 5
  }'
```

**Example response:**
```json
{
  "status": "success",
  "data": {
    "id": "uuid-here",
    "message": "Great plugin!",
    "rating": 5,
    "createdAt": "2024-01-01T12:00:00Z"
  },
  "message": "Feedback submitted successfully",
  "timestamp": "2024-01-01T12:00:00Z"
}
```

---

## Daily Workflow

Here is the recommended workflow for every development session:

```bash
# 1. Start your session
supabase start              # Start local database
.\gradlew.bat bootRun       # Start the API (Windows)
./gradlew bootRun           # Start the API (Mac/Linux)

# 2. During development
# The API restarts automatically if you use Spring DevTools

# 3. If you changed the database schema
supabase migration new my_change
# Edit the migration file
supabase db reset           # Apply locally

# 4. Before pushing to GitHub
.\gradlew.bat build         # Make sure everything builds
.\gradlew.bat test          # Make sure tests pass

# 5. Push your changes
git add .
git commit -m "feat: describe what you did"
git push origin main

# 6. End your session
supabase stop               # Free up resources
```

---

## Deployment

This API is deployed on **Render** (free tier).

### First deployment

1. Go to [render.com](https://render.com)
2. **New → Web Service → Connect GitHub repo**
3. Select `deodorant-reborn-api`
4. Configure:
   - **Environment:** Docker
   - **Branch:** main
5. Add environment variables:
   ```
   SUPABASE_DB_URL=jdbc:postgresql://db.xxxx.supabase.co:5432/postgres
   SUPABASE_DB_USER=postgres
   SUPABASE_DB_PASSWORD=your_production_password
   CORS_ALLOWED_ORIGINS=https://your-site.vercel.app
   ```
6. Click **Deploy**

### Automatic deployments

Every push to `main` triggers a new deployment automatically on Render.

---

## Contributing

Contributions are welcome! Here is how to get started:

1. **Fork** the repository
2. **Create a branch:** `git checkout -b feat/your-feature-name`
3. **Make your changes** and write tests
4. **Build and test:** `./gradlew build`
5. **Commit:** `git commit -m "feat: describe your change"`
6. **Push:** `git push origin feat/your-feature-name`
7. **Open a Pull Request** on GitHub

**Branch naming:**
| Type | Pattern | Example |
|------|---------|---------|
| Feature | `feat/name` | `feat/add-voting` |
| Bug fix | `fix/name` | `fix/feedback-validation` |
| Docs | `docs/name` | `docs/update-readme` |
| Refactor | `refactor/name` | `refactor/feedback-service` |

---

## License

This project is licensed under the **MIT License**.

---

## Author

**Dateng Tankoua**
- 🌐 Platform: [deodorant-reborn-platform](https://your-site.vercel.app)
- 💼 LinkedIn: [linkedin.com/in/emery-josian-dateng-tankoua-944522338](https://linkedin.com/in/dateng-tankoua)
- 🐙 GitHub: [github.com/DatengTankoua](https://github.com/DatengTankoua)

---

## Related Repositories

| Repository | Description |
|------------|-------------|
| [intellij-deodorant-reborn](https://github.com/DatengTankoua/intellij-deodorant-reborn) | The IntelliJ IDEA plugin |
| [deodorant-reborn-platform](https://github.com/DatengTankoua/deodorant-reborn-platform) | The Next.js frontend website |
| [deodorant-reborn-api](https://github.com/DatengTankoua/deodorant-reborn-api) | This repository |