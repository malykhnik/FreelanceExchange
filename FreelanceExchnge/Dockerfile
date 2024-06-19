# Используем официальный базовый образ OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем собранный JAR файл в рабочую директорию контейнера
COPY target/FreelanceExchange-0.0.1-SNAPSHOT.jar /app/FreelanceExchange.jar

# Устанавливаем точку входа
ENTRYPOINT ["java", "-jar", "FreelanceExchange.jar"]
