# ── Build stage ──────────────────────────────
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src

RUN chmod +x gradlew
RUN ./gradlew bootJar --no-daemon -x test

# ── Run stage ────────────────────────────────
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Sécurité: non-root user
RUN addgroup -S appgroup && \
    adduser -S appuser -G appgroup

COPY --from=builder /app/build/libs/*.jar app.jar

USER appuser

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
  CMD wget -qO- http://localhost:8080/actuator/health || exit 1

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]