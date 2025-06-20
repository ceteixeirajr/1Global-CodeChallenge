package com.global.challenge.dao;

import java.sql.Timestamp;
import org.springframework.data.annotation.Id;

/**
 * Class Device
 * <b>Description:</b> A device entity.<br>
 * This class defines a Device object.
 *   
 * @author Carlos Teixeira
 * @version 1.0
 */
public class Device {
	
	@Id
	int id;
	String name;
	String brand;
	String state;
	Timestamp timestp;
	
	/**
	 * public Device(int id, String name, String brand, String state, Timestamp timestp)<br>
	 * Constructs a new device 
	 *
	 * @param id - device id number
	 * @param name - device name
	 * @param brand - device brand
	 * @param state - device state, can be: A or U or I
	 * @param timestp - device time of creation 
	 * 
	 */
	public Device(int id, String name, String brand, String state, Timestamp timestp) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.state = state;
		this.timestp = timestp;
	}
	
	/**
	 * public Device(String name, String brand, String state, Timestamp timestp)<br>
	 * Constructs a new device 
	 *
	 * @param name - device name
	 * @param brand - device brand
	 * @param state - device state, can be: A or U or I
	 * @param timestp - device time of creation 
	 * 
	 */
	public Device(String name, String brand, String state, Timestamp timestp) {
		super();
		this.name = name;
		this.brand = brand;
		this.state = state;
		this.timestp = timestp;
	}
	
	/**
	 * public Device(int id)<br>
	 * Constructs a new device 
	 *
	 * @param id - device id number
	 * 
	 */
	public Device(int id) {
		super();
		this.id = id;
	}
	
	/**
	 * public Device()<br>
	 * Constructs a new device 
	 *
	 */
	public Device() {
		super();
	}
	
	/**
	 * public int getId()<br>
	 * Returns the id of a device. 
	 *
	 * @return returns the id of a device.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * public void setId(int id)<br>
	 * Sets the id of a device. 
	 *
	 * @param id - device id number
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * public String getName()<br>
	 * Returns the name of a device. 
	 *
	 * @return returns the name of a device.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * public void setName(String name)<br>
	 * Sets the name of a device. 
	 *
	 * @param name - device name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * public String getBrand()<br>
	 * Returns the brand of a device. 
	 *
	 * @return returns the brand of a device.
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * public void setBrand(String brand)<br>
	 * Sets the brand of a device. 
	 *
	 * @param brand - device brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	/**
	 * public String getState()<br>
	 * Returns the state of a device. 
	 *
	 * @return returns the state of a device, can be: A or U or I.
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * public void setState(String state)<br>
	 * Sets the state of a device. 
	 *
	 * @param state - device state, can be: A or U or I
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * public Timestamp getTimestp()<br>
	 * Returns the creation date and time of a device. 
	 *
	 * @return returns the creation date and time of a device (java.sql.Timestamp).
	 */
	public Timestamp getTimestp() {
		return timestp;
	}
	
	/**
	 * public void setTimestp(Timestamp timestp)<br>
	 * Sets the creation date and time of a device.  
	 *
	 * @param timestp - device date and time of creation  (java.sql.Timestamp(milliseconds))
	 */
	public void setTimestp(Timestamp timestp) {
		this.timestp = timestp;
	}

}
