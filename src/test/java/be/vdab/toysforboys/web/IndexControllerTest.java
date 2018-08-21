package be.vdab.toysforboys.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.toysforboys.services.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

	private IndexController controller;
	@Mock
	private OrderService dummyOrderService;

	@Before
	public void before() {
		controller = new IndexController(dummyOrderService);
	}

	@Test
	public void IndexControllerWerktSamenMetIndexDotJsp() {
		ModelAndView modelAndView = controller.orders();
		assertEquals("index", modelAndView.getViewName());
	}

	@Test
	public void IndexControllerGeeftOrdersDoor() {
		ModelAndView modelAndView = controller.orders();
		assertTrue(modelAndView.getModel().containsKey("orders"));
	}

}
