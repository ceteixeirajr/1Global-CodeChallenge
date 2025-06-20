package com.global.challenge.dao;

import java.util.List;

/**
 * Interface AccessDAO
 * <b>Description:</b> AccessDAO base interface.<br>
 * This interface defines the database access methods that must be implemented.
 *   
 * @author Carlos Teixeira
 * @version 1.0
 */
public interface AccessDAO {

	public void create(Device device);
	public List<Device> retrieveAll();
	public Device retrieveById(int id);
	public List<Device> retrieveByBrand(String brand);
	public List<Device> retrieveByState(String state);
	public void update(Device device);
	public void delete(Device device);
}
