package com.global.challenge.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Class DeviceDAO
 * <b>Description:</b>Device Database access.<br>
 * This class provides access to the device table, with the following features:
 *  
 *  public void create(Device device);
 *	public List<Device> retrieveAll();
 *	public Device retrieveById(int id);
 *	public List<Device> retrieveByBrand(String brand);
 *	public List<Device> retrieveByState(String state);
 *	public void update(Device device);
 *	public void delete(Device device);
 *   
 * @author Carlos Teixeira
 * @version 1.0
 */


@Component
public class DeviceDAO implements AccessDAO {

	@Autowired
	DeviceRepository deviceRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	/**
	 * public void create(Device device) <br>
	 * Creates a new device in the database.
	 *
	 * @param device - a device object
	 */
	public void create(Device device) {

		deviceRepository.save(device);

	}

	/**
	 * public List<Device> retrieveAll() <br>
	 * Retrieves all devices in the database.
	 *
	 * @return a list of devices objects.
	 */
	public List<Device> retrieveAll() {
		
		Iterable<Device> devices = deviceRepository.findAll();
		List<Device> deviceList = new ArrayList<>();
		devices.forEach(deviceList::add);
		return deviceList;
	}
	
	/**
	 * public Device retrieveById(int id) <br>
	 * Retrieves a device by its id from the database.
	 *
	 * @param id - device id number
	 * @return if exists, returns a device object or null.
	 */
	public Device retrieveById(int id) {
		
		Optional<Device> device = deviceRepository.findById(id);
		
		if(device.isPresent()) {
			return device.get();
		}
		
		return null;
		
	}
	
	/**
	 * public List<Device> retrieveByBrand(String brand) <br>
	 * Retrieves a device by its brand from the database.
	 *
	 * @param brand - device brand
	 * @return a list of devices objects.
	 */
	public List<Device> retrieveByBrand(String brand) {
		
		var sql = "select * from device where brand = ?";
		var bprm = new BeanPropertyRowMapper <Device>(Device.class);
		List<Device> deviceList = jdbcTemplate.query(sql,  bprm, brand);
				
		return deviceList;
	}
	
	/**
	 * public List<Device> retrieveByState(String state) <br>
	 * Retrieves a device by its state from the database.
	 *
	 * @param state - device state, can be: A or U or I
	 * @return a list of devices objects.
	 */
	public List<Device> retrieveByState(String state) {
		
		var sql = "select * from device where state = ?";
		var bprm = new BeanPropertyRowMapper <Device>(Device.class);
		List<Device> deviceList = jdbcTemplate.query(sql,  bprm, state);
				
		return deviceList;
	}

	/**
	 * public void update(Device device) <br>
	 * Updates a device in the database.
	 *
	 * @param device - a device object
	 */
	public void update(Device device) {

		deviceRepository.save(device);

	}

	/**
	 * public void delete(Device device) <br>
	 * Deletes a device in the database.
	 *
	 * @param device - a device object
	 */
	public void delete(Device device) {

		deviceRepository.delete(device);
	}

}
