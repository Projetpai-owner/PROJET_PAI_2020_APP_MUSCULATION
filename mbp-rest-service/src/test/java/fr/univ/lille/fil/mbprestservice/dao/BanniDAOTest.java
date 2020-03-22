package fr.univ.lille.fil.mbprestservice.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.univ.lille.fil.mbprestservice.entity.Banni;
import fr.univ.lille.fil.mbprestservice.repository.BanniRepository;

@RunWith(SpringRunner.class)
public class BanniDAOTest {

	@Mock
	BanniRepository banniRepository;

	@InjectMocks
	BanniDAO dao;
	
	@Before
	public void init() {
		Banni expected=new Banni();
		expected.setEmail("antho59.bliecq@gmail.com");
		Mockito.when(banniRepository.findAll()).thenReturn(Arrays.asList(expected));
	}
	
	@Test
	public void findAllTest() {

		List<Banni> list=dao.findAll();
		Mockito.verify(banniRepository).findAll();
		assertEquals(list.size(),1);
	}
	
}
