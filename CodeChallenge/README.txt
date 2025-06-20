This project was created in MAVEN, uses Spring BOOT and was generated in Spring Tools for Eclipse.

This project exports a REST API with the following functionalities:
 -Create a new device
 -Fully and/or partially update an existing device
 -Fetch a single device
 -Fetch all devices
 -Fetch devices by brand
 -Fetch devices by state
 -Delete a single device
 
 ------------------------------------------------------------------------------------------------------------------------------
 Settings:
 
 Data persistence is done by Microsoft SQL Server. Below is the SQL clause for creating the table: 

	create table device (
		 id INT IDENTITY(1,1) primary key,
		 name varchar(255),
		 brand varchar(128),
		 state varchar(1) check(state='A' or state ='U' or state='I'),
		 timestp datetime NOT NULL DEFAULT(GETDATE())
	);

In src/main/resources, check the application.properties:
spring.application.name=CodeChallenge
logging.threshold.console=error
spring.datasource.url=jdbc:sqlserver://instance;databaseName=TODO;encrypt=false
spring.datasource.username=username
spring.datasource.password=password

------------------------------------------------------------------------------------------------------------------------------
Use cases:

GET - http://localhost:8080/health-check
input: none
output: Code Challenge REST API is UP


PUT - http://localhost:8080/createdevice
input: A json containing { "name":"Name", "brand":"brand", "state":"A or U or I" }
output: A json containing the new device created in the database


PATCH - http://localhost:8080/updatedevice
input: A json containing {"id":"id number", "name":"Name", "brand":"brand", "state":"A or U or I" }
output: A message indicating that: Device is in use and can't be updated or 
                                   Device 'id' updated" or 
                                   Invalid state. State can be A or U or I or 
                                   Device not found


GET - http://localhost:8080/devicebyid?id="id number"
input: id containing the number of the device to be recovered
output: A json containing the device recovered from the database, by its id or message error


GET - http://localhost:8080/alldevices
input: none
output: A json list containing all the devices recovered from the database


GET - http://localhost:8080/devicebybrand?brand="brand"
input: brand containing the name of the brand from the devices to be recovered
output: A json list containing all devices that have the same brand, recovered from the database.


GET - http://localhost:8080/devicebystate?state="A - available or U - in-use or I - inactive "
input: state containing the code of the state from the devices to be recovered
output: A json list containing all devices that have the same state, recovered from the database.


DELETE - http://localhost:8080//deletedevice?id="id number"
input: id containing the number of the device to be deleted
output: A message indicating that: Device is in use and can't be deleted or 
								   Device 'id' deleted or 
								   Device not found!


------------------------------------------------------------------------------------------------------------------------------
JUnit Tests:

The DeviceContollerTests class was created to perform tests on all functionalities.

It must be executed by JUnit.

Location: /src/test/java
class: com.global.challenge.DeviceContollerTests


