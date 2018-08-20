package be.vdab.toysforboys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.toysforboys.services.OrderService;

@Controller
@RequestMapping("/")
class IndexController {
	
	private static final String VIEW = "index";
	private final OrderService orderService;
	
	IndexController(OrderService orderService){
		this.orderService = orderService;
	}
	
	@GetMapping
	ModelAndView orders() {
		return new ModelAndView(VIEW, "orders", orderService.findAllButCancelledAndShipped());
	}
	
	private final static String REDIRECT_NA_DELETE = "redirect:/";

	@PostMapping(params = "shippedId")
	ModelAndView setAsShipped(Long[] shippedId) {
		if (shippedId != null) {
			orderService.setAsShipped(shippedId);
		}
		return new ModelAndView(REDIRECT_NA_DELETE);
	}

}
