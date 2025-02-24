# 1. 사용할 베이스 이미지 (Java 17 기반)
FROM openjdk:17-jdk-slim

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 빌드된 JAR 파일을 복사 (뒤에 이름은 실제 생성된 JAR로 변경 필요)
COPY build/libs/*.jar app.jar

# 4. 컨테이너에서 실행할 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]