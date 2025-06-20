package com.global.challenge;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.global.challenge.dao.Device;
import com.global.challenge.dao.DeviceDAO;
import com.google.gson.Gson;


/**
 * Class DeviceController
 * <b>Description:</b> Spring Boot App Controller.<br>
 * This class performs REST API functions for the following features:
 *   - Create a new device
 *   - Fully and/or partially update an existing device
 *   - Fetch a single device
 *   - Fetch all devices
 *   - Fetch devices by brand
 *   - Fetch devices by state
 *   - Delete a single device
 *   
 * @author Carlos Teixeira
 * @version 1.0
 */

@RestController
@CrossOrigin
public class DeviceController {
	
	@Autowired
	DeviceDAO deviceDAO;

	
	/**
	 * public String getHealthCheck()<br>
	 * Allows you to check that the Spring Boot Application is up and running.
	 *
	 * @return a message indicating that it is working
	 */
	@GetMapping("/health-check")
	public String getHealthCheck() {
		return "Code Challenge REST API is UP";
	}

	
	/**
	 * public Device createDevice(@RequestBody Device newDevice) <br>
	 * Implement the functionality: "Create a new device"
	 *
	 * @param newDevice - a json containing { "name":"Name", "brand":"brand", "state":"A or U or I" }
	 * @return a json containing the new device created in the database.
	 */
	@PutMapping("/createdevice")
	public Device createDevice(@RequestBody Device newDevice) {
		
		long milliseconds = System.currentTimeMillis();
		
		Device device = newDevice;
		device.setTimestp(new java.sql.Timestamp(milliseconds));
		deviceDAO.create(device);
		return device; 
	}
	
	
	/**
	 * public String updateDevice(@RequestBody Device updatedDevice)<br>
	 * Implement the functionality: "Fully and/or partially update an existing device"
	 * Name and Brand properties can't be updated if the device is in use
	 *
	 * @param updatedDevice - a json containing {"id":"id number", "name":"Name", "brand":"brand", "state":"A or U or I" }
	 * @return returns a message indicating: Device is in use and can't be updated or Device "id" updated or Invalid state. State can be A or U or I or Device not found.
	 */
	@PatchMapping("/updatedevice")
	public String updateDevice(@RequestBody Device updatedDevice) {
		
		Device device = deviceDAO.retrieveById(updatedDevice.getId());
		
		if (device != null) {
			
			if ((device.getName().equalsIgnoreCase(updatedDevice.getName()) &&
				 device.getBrand().equalsIgnoreCase(updatedDevice.getBrand())) ||
				 !device.getState().equalsIgnoreCase("U") ) {
				
				if(updatedDevice.getState().equalsIgnoreCase("A") ||
				   updatedDevice.getState().equalsIgnoreCase("U") ||
				   updatedDevice.getState().equalsIgnoreCase("I") ) {
				
					updatedDevice.setTimestp(device.getTimestp());
					deviceDAO.update(updatedDevice);
					return "Device " + updatedDevice.getId() + " updated";
					
				} else
					return "Invalid state. State can be A or U or I";

			} else {
				return "Device is in use and can't be updated";				
			}
			
		} else
			return "Device not found!";
	}
	
	
	/**
	 * public String getDeviceById(@RequestParam(name="id") String strId)<br>
	 * Implement the functionality: "Fetch a single device by its id"
	 * Id must be a number.
	 *
	 * @param strId - the device id number that should be retrieved
	 * @return a json containing the device recovered from the database or a message error.
	 */
	@GetMapping("/devicebyid")
	public String getDeviceById(@RequestParam(name="id") String strId) {
		
		Gson gson = new Gson();
		String json = null;
		int id = 0;
		
		try {
			id = Integer.parseInt(strId);
		} catch (NumberFormatException ex)  {
			return "Id must be a number";
		}
		
		Device device = deviceDAO.retrieveById(id);
		
		if (device != null) {
			json = gson.toJson(device);
									
		} else
			json = "Device not found!";
		
		return json;
	}
	
	
	/**
	 * public String getAllDevices()<br>
	 * Implement the functionality: "Fetch all devices"
	 *
	 * @return a json list containing all the devices recovered from the database.
	 */
	@GetMapping("/alldevices")
	public String getAllDevices() {
		
		Gson gson = new Gson();
		
		List<Device> deviceList = deviceDAO.retrieveAll();
		
		
		String json = gson.toJson(deviceList);
		return json;
	}
	
	
	/**
	 * public String getDeviceByBrand(@RequestParam(name="brand") String brand)<br>
	 * Implement the functionality: "Fetch devices by brand"
	 * Brand must be the name of the brand to be recovered.
	 *
	 * @param brand - the device brand that should be retrieved
	 * @return a json list containing all devices that have the same brand, recovered from the database.
	 */
	@GetMapping("/devicebybrand")
	public String getDeviceByBrand(@RequestParam(name="brand") String brand) {
		
		Gson gson = new Gson();
		
		List<Device> deviceList = deviceDAO.retrieveByBrand(brand);
		
		
		String json = gson.toJson(deviceList);
		return json;
	}
	
	
	/**
	 * public String getDeviceByState(@RequestParam(name="state") String state)<br>
	 * Implement the functionality: "Fetch devices by state"
	 * State must be the code of the state to be recovered. State can be: A - available or U - in-use or I - inactive 
	 *
	 * @param state - the device state that should be retrieved
	 * @return a json list containing all devices that have the same state, recovered from the database.
	 */
	@GetMapping("/devicebystate")
	public String getDeviceByState(@RequestParam(name="state") String state) {
		
		Gson gson = new Gson();
		
		List<Device> deviceList = deviceDAO.retrieveByState(state);
		
		
		String json = gson.toJson(deviceList);
		return json;
	}
	
	
	/**
	 * public String deleteDevice(@RequestParam(name="id") String strId) String state)<br>
	 * Implement the functionality: "Delete a single device"
	 * In use devices can't be deleted 
	 *
	 * @param strId - the device id number that should be deleted
	 * @return returns a message indicating: Device is in use and can't be deleted or Device 'id' deleted or Device not found!.
	 */
	@DeleteMapping("/deletedevice") 
	public String deleteDevice(@RequestParam(name="id") String strId) {
		
		Device device = null;
		int id = 0;
		
		try {
			id = Integer.parseInt(strId);
		} catch (NumberFormatException ex)  {
			return "Id must be a number";
		}
		
		device = deviceDAO.retrieveById(id);
		
		if (device != null) {
			
			if (device.getState().equalsIgnoreCase("U"))
				return "Device is in use and can't be deleted";
			else {
				deviceDAO.delete(device);
				return "Device "+id+" deleted";
			}
			
		} else
			return "Device not found!";
		
	}
}
