FROM openjdk:8

COPY target/fx-deals.war application.war

ENTRYPOINT ["java","-jar", "application.war"]