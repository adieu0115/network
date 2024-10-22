# 1단계: 빌드 단계
FROM gradle:7.6.1-jdk17 AS build

WORKDIR /app

# Gradle 설정 파일 복사
COPY build.gradle settings.gradle ./
COPY gradlew ./
COPY gradle ./gradle

# Gradlew 실행 권한 부여 (필요한 경우)
RUN chmod +x gradlew

# 의존성 캐싱을 위한 Gradle 빌드
RUN ./gradlew build -x test --no-daemon || return 0

# 소스 코드 복사
COPY src ./src

# 애플리케이션 빌드
RUN ./gradlew build -x test --no-daemon

# 2단계: 실행 단계
FROM openjdk:17-jdk-slim

WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 애플리케이션이 사용하는 포트 열기
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
