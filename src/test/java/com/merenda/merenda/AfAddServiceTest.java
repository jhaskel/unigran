package com.merenda.merenda;

import com.merenda.merenda.api.af.afAdd.AfAdd;
import com.merenda.merenda.api.af.afAdd.AfAddDTO;
import com.merenda.merenda.api.af.afAdd.AfAddService;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AfAddServiceTest {

	@Autowired
	private AfAddService service;

	@Test
	public void testSave() {

		AfAdd af = new AfAdd();
		af.setStatus("teste");
		af.setIsenviado(true);
		af.setCode(202105214L);



		AfAddDTO c = service.insert(af);

		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		// Buscar o objeto
		c = service.getAfAddById(id);
		assertNotNull(c);
		assertEquals("teste",c.getStatus());

		// Deletar o objeto
		service.delete(id);

		// Verificar se deletou
		try {
			service.getAfAddById(id);
			fail("A af não foi excluído");
		} catch (ObjectNotFoundException e) {
			// OK
		}
	}

	/*@Test
	public void testLista() {

		List<CarroDTO> carros = service.getCarros();

		assertEquals(30, carros.size());
	}

	@Test
	public void testListaPorTipo() {

		assertEquals(10, service.getCarrosByTipo("classicos").size());
		assertEquals(10, service.getCarrosByTipo("esportivos").size());
		assertEquals(10, service.getCarrosByTipo("luxo").size());

		assertEquals(0, service.getCarrosByTipo("x").size());
	}

	@Test
	public void testGet() {

		CarroDTO c = service.getCarroById(11L);

		assertNotNull(c);


		assertEquals("Ferrari FF", c.getNome());
	}*/
}