package com.merenda.merenda;


import com.merenda.merenda.api.itens.Itens;
import com.merenda.merenda.api.itens.ItensDTO;
import com.merenda.merenda.api.itens.ItensService;
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
public class ItensServiceTest {

	@Autowired
	private ItensService service;

	@Test
	public void testSave() {
		Itens itens = new Itens();
		itens.setLocal(5L);
		itens.setProduto(215L);
		itens.setPedido(215L);
		itens.setCategoria(5L);
		itens.setFornecedor(15L);
		itens.setAno(5L);
		itens.setAf(15L);
		itens.setQuantidade(5.0);
		itens.setValor(10.0);
		itens.setTotal(50.0);
		itens.setAlias("Abacaxi");
		itens.setUnidade("kg");
		itens.setMes("MAI");
		itens.setStatus("Comprado");
		itens.setCreatedAt("20/05/2021");
		itens.setModifiedAt("21/05/2021");
		itens.setIsativo(true);

		ItensDTO c = service.insert(itens);
		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		// Buscar o objeto
		c = service.getItenById(id);
		assertNotNull(c);
		assertTrue("5 é menor que 40", c.getLocal() < 40);
		assertTrue("215 é maior que 40", c.getProduto() > 40);
		assertTrue("215 é maior que 40", c.getPedido() > 40);
		assertTrue("5 é menor que 40", c.getCategoria() < 40);
		assertTrue("15 é maior que 1", c.getFornecedor() > 1);
		assertTrue("5 é maior que 1", c.getAno() > 1);
		assertTrue("15 é maior que 1", c.getAf() > 1);
		assertTrue("5 é maior que 4", c.getQuantidade() < 20.0);
		assertFalse("10 é não é maior que 50", c.getValor() > 50);
		assertTrue("50 é maior que 20", c.getTotal() > 20.0);
		assertEquals("Abacaxi",c.getAlias());
		assertEquals("kg",c.getUnidade());
		assertEquals("MAI",c.getMes());
		assertEquals("Comprado",c.getStatus());
		assertEquals("20/05/2021",c.getCreatedAt());
		assertEquals("21/05/2021",c.getModifiedAt());
		assertTrue(c.getIsativo());

		// Deletar o objeto
		service.delete(id);

		// Verificar se deletou
		try {
			service.getItenById(id);
			fail("A itens não foi excluído");
		} catch (ObjectNotFoundException e) {
			// OK
		}
	}


	@Test
	//parametros inseridos no data.sql
	public void testGet() {
		ItensDTO c = service.getItenById(11L);
		assertNotNull(c);
		assertEquals("Pedido Realizado!", c.getStatus());
	}

	@Test
	//parametros inseridos no data.sql
	public void testTotalAno() {
		Long ano = 2021L;
		double c = service.getTotal(ano);
		assertNotNull(c);
		assertEquals(4717.580000000001,c);

	}

	/*@Test
	//parametros inseridos no data.sql
	public void testTotalEscola() {
		Long ano = 2021L;
		Long escola = 3L;
		double c = service.getTotalEscola(escola,ano);
		System.out.println("totalEscola " + c);
		assertNotNull(c);
	   assertEquals(2566.66,c);

	}*/

	@Test
	//parametros inseridos no data.sql
	public void testMaisPedidos() {
		Long ano = 2021L;
		List<ItensDTO> c = service.getMaisPedidos(ano);
		System.out.println("totalEscola " + c);
		assertNotNull(c);
	//	assertEquals(2566.66,c);

	}


}