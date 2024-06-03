FROM openjdk:17

LABEL author=MariaEscriabnoVerde

ENV DATABASE_URL jdbc:mysql://localhost:3306/booknbook?useSSL=false
ENV DATABASE_USERNAME root
ENV DATABASE_PASSWORD 2820RMer*

#Previo --> mvn clean package
COPY target/booknbook-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

#docker build -t booknbook-backend-image:1 .
#docker run -p8081:8081 --name booknbook-backend-container booknbook-backend-image:1