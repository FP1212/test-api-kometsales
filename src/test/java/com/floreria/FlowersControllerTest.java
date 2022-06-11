package com.floreria;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class FlowersControllerTest {

	@Autowired
	private MockMvc mvc;
	private String flowersTestList = "[\r\n" + "        {\"id\":0,\"name\":\"zabala arnica\",\"price\":19},\r\n"
			+ "        {\"id\":1,\"name\":\"arnica\",\"price\":11.99},\r\n"
			+ "        {\"id\":2,\"name\":\"cecilia\",\"price\":21}\r\n" + "]";			
			

	@Test
	void testFlowerController() throws Exception {

			//Create Flowers List
			mvc.perform(MockMvcRequestBuilders.post("/flores").contentType(MediaType.APPLICATION_JSON)
					.content(flowersTestList).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
			
			//Test If Could Get List
			mvc.perform(MockMvcRequestBuilders.get("/flores").accept(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$").isArray())
					.andExpect(jsonPath("$[0].name").value("arnica-kometsales"))
					.andExpect(jsonPath("$[0].price").value(11.99))
					.andExpect(jsonPath("$[1].name").value("cecilia-kometsales"))
					.andExpect(jsonPath("$[1].price").value(21))
					.andExpect(jsonPath("$[2].name").value("zabala arnica-kometsales"))
					.andExpect(jsonPath("$[2].price").value(19));		
			
			//Test Get Flowers List By Price >= 20
			mvc.perform(MockMvcRequestBuilders.get("/flores/prices?price").accept(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$").isArray())
					.andExpect(jsonPath("$[0].id").value(2))
					.andExpect(jsonPath("$[0].name").value("cecilia"))
					.andExpect(jsonPath("$[0].price").value(21));		
			
			//Test Delete Flower By Id
			mvc.perform(MockMvcRequestBuilders.delete("/flores/2").accept(MediaType.APPLICATION_JSON))			
					.andExpect(status().isOk())
					.andExpect(jsonPath("$").value("Item identfied by id:2, Deleted"));
			
			//Test Get Flowers List By Price Is Empty Because Not Found
			mvc.perform(MockMvcRequestBuilders.get("/flores/prices?price").accept(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$",Matchers.empty()));
			
			//Test Get Flowers List By Price >= 19
			mvc.perform(MockMvcRequestBuilders.get("/flores/prices?price=19").accept(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$").isArray())
					.andExpect(jsonPath("$[0].id").value(0))
					.andExpect(jsonPath("$[0].name").value("zabala arnica"))
					.andExpect(jsonPath("$[0].price").value(19));	
			
			//Test Get Flowers List By Name Coincidence >= 19
			mvc.perform(MockMvcRequestBuilders.get("/flores/arnica").accept(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$").isArray())
					.andExpect(jsonPath("$[0].id").value(0))
					.andExpect(jsonPath("$[0].name").value("zabala arnica"))
					.andExpect(jsonPath("$[0].price").value(19))
					.andExpect(jsonPath("$[1].id").value(1))
					.andExpect(jsonPath("$[1].name").value("arnica"))
					.andExpect(jsonPath("$[1].price").value(11.99))
					;
	}

}
