package com.merenda.merenda;
import com.merenda.merenda.api.carros.Carro;
import com.merenda.merenda.api.carros.CarroService;
import com.merenda.merenda.api.carros.CarroDTO;
import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MerendaServiceTest {

	@Autowired
	private CarroService service;

	@Test
	public void testSave() {

		Carro carro = new Carro();
		carro.setNome("Porshe");
		carro.setTipo("esportivos");

		CarroDTO c = service.insert(carro);

		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		// Buscar o objeto
		c = service.getCarroById(id);
		assertNotNull(c);

		assertEquals("Porshe",c.getNome());
		assertEquals("esportivos",c.getTipo());

		// Deletar o objeto
		service.delete(id);

		// Verificar se deletou
		try {
			service.getCarroById(id);
			fail("O carro não foi excluído");
		} catch (ObjectNotFoundException e) {
			// OK
		}
	}

	@Test
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
	}
}