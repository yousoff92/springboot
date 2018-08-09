package com.yousoff.rest.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yousoff.rest.application.MainApplication;
import com.yousoff.rest.controller.ItemController;
import com.yousoff.rest.dao.ItemDao;
import com.yousoff.rest.model.Item;

/*
 * Reference :
 * 1. https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
 * 2. http://www.natpryce.com/articles/000714.html
 */

/**
 * 
 * Run and generate report using these command
 * 
 * surefire-report:report site -DgenerateReports=false
 * 
 * @author Yousoff Effendy
 * 
 * 
 *
 */
@SpringApplicationConfiguration(classes = MainApplication.class)
@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
@IntegrationTest("server.port:0")
public class ControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ItemDao repositoryMock;

	@Mock
	private ItemController controllerMock;

	@Before
	public void setUp() {
		controllerMock = new ItemController(repositoryMock);
		mockMvc = MockMvcBuilders.standaloneSetup(controllerMock).build();

	}

	/**
	 * To test whether all endpoints can be accessed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEndpointsIfEmpty() throws Exception {

		mockMvc.perform(post("/rest/v1/createItem")
				.content(asJsonString(new Item()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();

		mockMvc.perform(get("/rest/v1/getItem/{itemId}", "1")).andExpect(status().is2xxSuccessful()).andReturn();

		mockMvc.perform(get("/rest/v1//getAllItems")).andExpect(status().is2xxSuccessful()).andReturn();

		mockMvc.perform(post("/rest/v1/updateItem")
				.content(asJsonString(new Item()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();

		mockMvc.perform(post("/rest/v1/deleteItem")
				.content(asJsonString(new Item()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();

	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
