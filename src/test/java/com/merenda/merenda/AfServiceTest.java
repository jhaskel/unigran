package com.merenda.merenda;


import com.merenda.merenda.api.af.Af;
import com.merenda.merenda.api.af.AfDTO;
import com.merenda.merenda.api.af.AfService;
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
public class AfServiceTest {

	@Autowired
	private AfService service;

	@Test
	public void testSave() {

		Af af = new Af();
		af.setCode(500L);
		af.setFornecedor(15L);
		af.setStatus("Comprando!");
		af.setCreatedAt("20/05/2021");
		af.setIsenviado(false);
		af.setIsativo(true);

		AfDTO c = service.insert(af);
		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		// Buscar o objeto
		c = service.getAfById(id);
		assertNotNull(c);
		assertFalse("500 é maior que 4", c.getCode() < 4);
		assertTrue("15 é maior que 4", c.getFornecedor() > 4);
		assertEquals("Comprando!",c.getStatus());
		assertEquals("20/05/2021",c.getCreatedAt());
		assertFalse(c.getIsenviado());
		assertTrue(c.getIsativo());


		// Deletar o objeto
		service.delete(id);

		// Verificar se deletou
		try {
			service.getAfById(id);
			fail("A af não foi excluído");
		} catch (ObjectNotFoundException e) {
			// OK
		}
	}

	@Test
	//parametros inseridos no data.sql
	public void testLista() {
		List<AfDTO> afs = service.getAf();
		assertEquals(14, afs.size());
	}

	@Test
	//parametros inseridos no data.sql
	public void testListaPorFornecedor() {
		assertEquals(4, service.getByFornecedorTest(11L).size());
		assertEquals(5, service.getByFornecedorTest(10L).size());
		assertEquals(5, service.getByFornecedorTest(17L).size());
		assertEquals(0, service.getByFornecedorTest(100L).size());
	}

	@Test
	//parametros inseridos no data.sql
	public void testGet() {
		AfDTO c = service.getAfById(10L);
		assertNotNull(c);
		assertEquals("Pedido com fornececedor!", c.getStatus());
	}

	@Test
	//parametros inseridos no data.sql
	public void testEnviado() {
		assertEquals(4, service.getAfEnviada());
	}
}