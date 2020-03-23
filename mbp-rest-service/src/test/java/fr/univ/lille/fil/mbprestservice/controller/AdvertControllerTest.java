package fr.univ.lille.fil.mbprestservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import fr.univ.lille.fil.mbprestservice.dao.BanniDAO;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AdvertControllerTest {
	
	@Autowired
	MockMvc mockMvc;
		
	@MockBean
	BanniDAO banniDAO;
	
	@Test
	public void createAdvertTest() throws Exception {

		
	}

}
