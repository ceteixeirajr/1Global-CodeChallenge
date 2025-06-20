package com.global.challenge;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.challenge.dao.*;


@SpringBootTest
@AutoConfigureMockMvc
public class DeviceContollerTests {

	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	
		
	
	@Test
	public void testHealtCheck () throws Exception {
		
		Device device = null;
		
		String requestBody = "";
		mockMvc.perform(get("/alldevices").contentType("application/text").content(requestBody)).andExpect(status().isOk()).andDo(print());
		
		
		//TEST for: PUT - http://localhost:8080/createdevice
		device = new Device ("t001","xpto","A",new java.sql.Timestamp(System.currentTimeMillis()));
		requestBody = objectMapper.writeValueAsString(device);
		mockMvc.perform(put("/createdevice").contentType("application/json").content(requestBody)).andExpect(status().isOk()).andDo(print());
		
		
		//TEST for: PATCH - http://localhost:8080/updatedevice
		device = new Device (19,"t001","xpto","U",new java.sql.Timestamp(System.currentTimeMillis()));
		requestBody = objectMapper.writeValueAsString(device);
		mockMvc.perform(patch("/updatedevice").contentType("application/json").content(requestBody)).andExpect(status().isOk()).andDo(print());
		
		device = new Device (19,"t001","xpto2","U",new java.sql.Timestamp(System.currentTimeMillis()));
		requestBody = objectMapper.writeValueAsString(device);
		mockMvc.perform(patch("/updatedevice").contentType("application/json").content(requestBody)).andExpect(status().isOk()).andDo(print());
		
		device = new Device (190,"t002","xpto","U",new java.sql.Timestamp(System.currentTimeMillis()));
		requestBody = objectMapper.writeValueAsString(device);
		mockMvc.perform(patch("/updatedevice").contentType("application/json").content(requestBody)).andExpect(status().isOk()).andDo(print());
		
		
		//TEST for: GET - http://localhost:8080/devicebyid?id=id
		requestBody = objectMapper.writeValueAsString(device);
		mockMvc.perform(get("/devicebyid?id=19").contentType("application/text").content("")).andExpect(status().isOk()).andDo(print());

		
		//TEST for: GET - http://localhost:8080/alldevices
		requestBody = objectMapper.writeValueAsString(device);
		mockMvc.perform(get("/alldevices").contentType("application/text").content("")).andExpect(status().isOk()).andDo(print());
		
		
		//TEST for: GET - http://localhost:8080/devicebybrand?brand=brand
		requestBody = objectMapper.writeValueAsString(device);
		mockMvc.perform(get("/devicebybrand?brand=xpto").contentType("application/text").content("")).andExpect(status().isOk()).andDo(print());
				
				
		//TEST for: GET - http://localhost:8080/devicebystate?state=A
		requestBody = objectMapper.writeValueAsString(device);
		mockMvc.perform(get("/devicebystate?state=A").contentType("application/text").content("")).andExpect(status().isOk()).andDo(print());
		
		
		//TEST for: DELETE - http://localhost:8080/deletedevice?id=id
		requestBody = objectMapper.writeValueAsString(device);
		mockMvc.perform(delete("/deletedevice?id=19").contentType("application/text").content("")).andExpect(status().isOk()).andDo(print());
		
		requestBody = objectMapper.writeValueAsString(device);
		mockMvc.perform(delete("/deletedevice?id=31").contentType("application/text").content("")).andExpect(status().isOk()).andDo(print());
	}
}
