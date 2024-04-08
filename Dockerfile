FROM maven:3.9.6
WORKDIR /frete-back
EXPOSE 8080
COPY . .
RUN mvn clean install
ENTRYPOINT mvn spring-boot:run