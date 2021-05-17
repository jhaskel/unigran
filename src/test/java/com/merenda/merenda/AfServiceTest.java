package com.merenda.merenda;


import com.merenda.merenda.api.af.Af;
import com.merenda.merenda.api.af.AfDTO;
import com.merenda.merenda.api.af.AfService;
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
public class AfServiceTest {

	@Autowired
	private AfService service;

	@Test
	public void testSave() {

		Af af = new Af();
		af.setCode(202105214L);
		af.setFornecedor(15L);
		af.setStatus("Comprando!");
		af.setIsenviado(true);
		af.setCreatedAt("20/05/2021");
		af.setIsativo(true);
		af.setTot(15.98);
		af.setNomefor("Uniao Distribuidora");

		AfDTO c = service.insert(af);
		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		// Buscar o objeto
		c = service.getAfById(id);
		assertNotNull(c);
		assertEquals("Comprando!",c.getStatus());

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
	public void testLista() {
		List<AfDTO> afs = service.getAf();
		assertEquals(14, afs.size());
	}

	@Test
	public void testListaPorFornecedor() {
		assertEquals(4, service.getByFornecedorTest(11L).size());
		assertEquals(5, service.getByFornecedorTest(10L).size());
		assertEquals(5, service.getByFornecedorTest(17L).size());
		assertEquals(0, service.getByFornecedorTest(100L).size());
	}

	@Test
	public void testGet() {
		AfDTO c = service.getAfById(10L);
		assertNotNull(c);
		assertEquals("Pedido com fornececedor!", c.getStatus());
	}
}