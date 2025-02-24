# 1. Gradle 빌드를 위한 이미지 (빌드 전용)
FROM gradle:8-jdk17 AS build

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 프로젝트 파일들을 복사 (Gradle 관련 파일들)
COPY . .

# 4. Gradle 빌드 실행 (JAR 생성)
RUN gradle clean build -x test

# 5. 실제 실행을 위한 이미지 (Java 17 기반)
FROM openjdk:17-jdk-slim AS runtime

# 6. 작업 디렉토리 설정
WORKDIR /app

# 7. 빌드된 JAR 파일을 복사 (빌드 단계에서 생성된 JAR)
COPY --from=build /app/build/libs/*.jar app.jar

# 8. 컨테이너에서 실행할 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]

