package com.merenda.merenda;


import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import com.merenda.merenda.api.pedidos.Pedido;
import com.merenda.merenda.api.pedidos.PedidoDTO;
import com.merenda.merenda.api.pedidos.PedidoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidoServiceTest {

	@Autowired
	private PedidoService service;

	@Test
	public void testSave() {
		Pedido pedido = new Pedido();
		pedido.setStatus("Comprando!");
		pedido.setModifiedAt("20/06/2021");
		pedido.setCreatedAt("20/05/2021");
		pedido.setUnidade(5L);
		pedido.setTotal(520.50);
		pedido.setIsaf(false);
		pedido.setIsativo(true);

		PedidoDTO c = service.insert(pedido);
		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		// Buscar o objeto
		c = service.getPedidoById(id);
		assertNotNull(c);
		assertFalse("500 é maior que 200.60", c.getTotal() < 200.60);
		assertTrue("15 é maior que 4", c.getUnidade() > 4);
		assertEquals("Comprando!",c.getStatus());
		assertEquals("20/05/2021",c.getCreatedAt());
		assertEquals("20/06/2021",c.getModifiedAt());
		assertFalse(c.getIsaf());
		assertTrue(c.getIsativo());


		// Deletar o objeto
		service.delete(id);

		// Verificar se deletou
		try {
			service.getPedidoById(id);
			fail("A pedido não foi excluído");
		} catch (ObjectNotFoundException e) {
			// OK
		}
	}



	@Test
	//parametros inseridos no data.sql
	public void testGet() {
		PedidoDTO c = service.getPedidoById(7L);
		assertNotNull(c);
		assertEquals("Pedido com fornececedor!", c.getStatus());
	}

	@Test
	//parametros inseridos no data.sql
	public void testEnviado() {
		assertEquals(3, service.getPedidoSemAf());
	}
}