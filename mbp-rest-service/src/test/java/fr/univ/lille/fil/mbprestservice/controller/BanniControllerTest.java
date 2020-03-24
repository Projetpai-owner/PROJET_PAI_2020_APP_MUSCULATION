package fr.univ.lille.fil.mbprestservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import fr.univ.lille.fil.mbprestservice.entity.Banni;
import fr.univ.lille.fil.mbprestservice.service.BanniService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class BanniControllerTest {
	@Autowired
	MockMvc mockMvc;
		
	@MockBean
	BanniService banniService;
	
	@Test
	public void getAllBanniTest() throws Exception {
		Banni expected=new Banni();
		expected.setEmail("antho59.bliecq@gmail.com");
		
		Mockito.when(banniService.findAll()).thenReturn(Stream.of(expected).collect(Collectors.toList()));
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/banni")
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		
		
		//on verifie que findAll est appele lors du get
		Mockito.verify(banniService).findAll();
		
		assertEquals(mvcResult.getResponse().getContentAsString(), "[{\"email\":\"antho59.bliecq@gmail.com\"}]");
		
		
		
		
	}
	
	
	
	
	
}
