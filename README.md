# Change logs
<p>
#v1.0.2<br>
	<p>- adds on to #v1.0.1<br></p>
	<p>- Uses CustomFlywayMigrationStrategy to collate all the flyway.locations from active profiles and hands it to flyway for migrations.<br></p>
</p>
<p>
#v1.0.1<br>
    - multiple locations, multiple profiles.<br>
    - Prerequisites<br>
        * create MySQL database<br>
            > spring_boot_flyway for 'default' profile<br>
            > spring_boot_moi for 'moi' profile<br>
        * running profile specific migrations<br> 
            > gradle build -x test && java -jar deploy/application/dist/spring-boot-flyway-1.0.1.SNAPSHOT.jar for 'default' profile<br>
            > gradle build -x test && java -jar deploy/application/dist/spring-boot-flyway-1.0.1.SNAPSHOT.jar --spring.profiles.active=moi for 'moi' profile<br>
</p>
<p>
#v1.0.0
    - single location
</p>
