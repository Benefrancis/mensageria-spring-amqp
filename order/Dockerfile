FROM openjdk:17 as rabbitmq
LABEL maintainer="Benefrancis do Nascimento"
EXPOSE 8080
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

ADD target/*.jar order.jar

RUN chmod 755 ./

ENTRYPOINT ["java","-jar","./order.jar"]