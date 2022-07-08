FROM openjdk:17 as rabbitmq
LABEL maintainer="Benefrancis do Nascimento"
EXPOSE 8082
WORKDIR /

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Copy the project source
COPY ./src ./src
COPY ./pom.xml ./pom.xml

RUN chmod 755 /app/mvnw

#RUN ./mvnw package -DskipTests

CMD mvnw package -DskipTests

RUN chmod 755 ./

ADD target/*.jar notification.jar
ENTRYPOINT ["java","-jar","./notification.jar"]

