# spring-boot-flyway

# About
Runs db migration script using flyway on mysql server. Flyway piggy backs on spring datasource configuration.

Prerequisite: -
1. MySQL Server, version used during testing 5.6.35

P.S:
Set jpa ddl-auto to none currently set to the following in application.properties:
spring.jpa.hibernate.ddl-auto=create-drop
