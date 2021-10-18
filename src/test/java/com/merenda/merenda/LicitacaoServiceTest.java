package com.merenda.merenda;


import com.merenda.merenda.api.cart.Cart;
import com.merenda.merenda.api.cart.CartDTO;
import com.merenda.merenda.api.cart.CartService;
import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LicitacaoServiceTest {

	@Autowired
	private CartService service;

	@Test
	public void testSave() {
		Cart cart = new Cart();
		cart.setLocal(5L);
		cart.setProduto(215L);
		cart.setCategoria(5L);
		cart.setFornecedor(15L);
		cart.setUnidade("kg");
		cart.setCod("560");
		cart.setAlias("Abacaxi");
		cart.setQuantidade(5.0);
		cart.setValor(10.0);
		cart.setTotal(50.0);
		cart.setCreatedAt("20/05/2021");

		CartDTO c = service.insert(cart);
		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		// Buscar o objeto
		c = service.getCartById(id);
		assertNotNull(c);
		assertTrue("5 é menor que 40", c.getLocal() < 40);
		assertTrue("215 é maior que 40", c.getProduto() > 40);
		assertTrue("5 é menor que 40", c.getCategoria() < 40);
		assertTrue("15 é maior que 1", c.getFornecedor() > 1);
		assertEquals("kg",c.getUnidade());
		assertEquals("560",c.getCod());
		assertEquals("Abacaxi",c.getAlias());
		assertTrue("5 é maior que 4", c.getQuantidade() < 20.0);
		assertFalse("10 é não é maior que 50", c.getValor() > 50);
		assertTrue("50 é maior que 20", c.getTotal() > 20.0);
		assertEquals("20/05/2021",c.getCreatedAt());

		// Deletar o objeto
		service.delete(id);

		// Verificar se deletou
		try {
			service.getCartById(id);
			fail("A cart não foi excluído");
		} catch (ObjectNotFoundException e) {
			// OK
		}
	}


	@Test
	//parametros inseridos no data.sql
	public void testGet() {
		CartDTO c = service.getCartById(3L);
		assertNotNull(c);
		assertEquals("pct", c.getUnidade());
	}

	/*@Test
	//parametros inseridos no data.sql
	public void testEnviado() {
		assertEquals(3, service.getCartSemAf());
	}*/
}