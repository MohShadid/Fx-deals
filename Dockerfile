FROM openjdk:8

COPY fx-deals.war application.war

ENTRYPOINT ["java","-jar", "application.war"]