package com.global.challenge.dao;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface DeviceRepository
 * <b>Description:</b> DeviceRepository base interface.<br>
 * This interface defines access to CRUD repository.
 *   
 * @author Carlos Teixeira
 * @version 1.0
 */
public interface DeviceRepository extends CrudRepository<Device, Integer> {

}
