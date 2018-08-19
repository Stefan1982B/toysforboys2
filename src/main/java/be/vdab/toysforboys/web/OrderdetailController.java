package be.vdab.toysforboys.web;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.services.OrderService;
import be.vdab.toysforboys.valueobjects.Orderdetail;

@Controller
@RequestMapping("orders")
class OrderdetailController {
	private static final String VIEW = "orders";
	private final static String REDIRECT_ORDER_NIET_GEVONDEN = "redirect:/";
	private final OrderService orderService;

	OrderdetailController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("{id}")
	ModelAndView orderDetail(@PathVariable long id, RedirectAttributes redirectAttribute) {
		Optional<Order> order = orderService.read(id);
		BigDecimal totalePrijs = order.get().getOrderdetails().stream()
				.map(orderdetail -> orderdetail.getPriceEach()
						.multiply(BigDecimal.valueOf(orderdetail.getQuantityOrdered())))
				.reduce(BigDecimal.ZERO, (vorigeSom, getal) -> vorigeSom.add(getal));
		if (order.isPresent()) {
			return new ModelAndView(VIEW).addObject(order.get()).addObject("totalePrijs", totalePrijs);

		}
		redirectAttribute.addAttribute("fout", "order niet gevonden");
		return new ModelAndView(REDIRECT_ORDER_NIET_GEVONDEN);
	}
}
