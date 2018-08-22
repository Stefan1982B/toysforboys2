package be.vdab.toysforboys.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.toysforboys.entities.Country;
import be.vdab.toysforboys.entities.Customer;
import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.enums.Status;
import be.vdab.toysforboys.services.OrderService;
import be.vdab.toysforboys.valueobjects.Address;

@RunWith(MockitoJUnitRunner.class)
public class OrderDetailControllerTest {

	private OrderdetailController controller;
	@Mock
	private OrderService dummyOrderService;
	@Mock
	private RedirectAttributes redirectAttributes;

	@Before
	public void before() {
		when(dummyOrderService.read(1)).thenReturn(Optional.of(
				new Order(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 2, 2), LocalDate.of(2019, 1, 15), "testComment",
						new Customer("testCustomer1", 1,
								new Address("testStraat1", "testCity1", "testState1", "testPostalCode1"),
								new Country("testCountry1", 1)),
						Status.SHIPPED, 1)));
		controller = new OrderdetailController(dummyOrderService);
	}

	@Test
	public void OrderdetailControllerWerktSamenMetOrdersDotJsp() {
		ModelAndView modelAndView = controller.orderDetail(1, redirectAttributes);
		assertEquals("orders", modelAndView.getViewName());
	}

	@Test
	public void OrderdetailControllerGeeftOrdersDoor() {
		ModelAndView modelAndView = controller.orderDetail(1, redirectAttributes);
		assertTrue(modelAndView.getModel().containsKey("totalePrijs"));
	}
}
