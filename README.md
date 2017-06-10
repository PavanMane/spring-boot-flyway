# Change logs

#v1.0.1
	- multiple locations, multiple profiles.
	- Prerequisites
		* create MySQL database
			- spring_boot_flyway for default profile
			- spring_boot_moi for moi profile
		* running profile specific migrations
			- gradle build -x test && java -jar deploy/application/dist/spring-boot-flyway-1.0.1.SNAPSHOT.jar for default profile
			- gradle build -x test && java -jar deploy/application/dist/spring-boot-flyway-1.0.1.SNAPSHOT.jar --spring.profiles.active=moi for 'moi' profile
	
#v1.0.0
	- single location.