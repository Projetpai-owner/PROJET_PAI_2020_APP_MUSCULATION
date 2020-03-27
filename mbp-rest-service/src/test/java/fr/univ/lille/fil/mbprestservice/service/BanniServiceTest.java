package fr.univ.lille.fil.mbprestservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import fr.univ.lille.fil.mbprestservice.entity.Banni;
import fr.univ.lille.fil.mbprestservice.repository.BanniRepository;
import fr.univ.lille.fil.mbprestservice.service.BanniService;

@RunWith(SpringRunner.class)
public class BanniServiceTest {

	@Mock
	BanniRepository banniRepository;

	@InjectMocks
	BanniService service;
	
	@Before
	public void init() {
		Banni expected=new Banni();
		expected.setEmail("antho59.bliecq@gmail.com");
		Mockito.when(banniRepository.findAll()).thenReturn(Arrays.asList(expected));
	}
	
	@Test
	public void findAllTest() {

		List<Banni> list=service.findAll();
		Mockito.verify(banniRepository).findAll();
		assertEquals(list.size(),1);
	}
	
}
