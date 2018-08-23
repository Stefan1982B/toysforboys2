package be.vdab.toysforboys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.toysforboys.entities.exceptions.OnvoldoendeVoorraadInStockException;
import be.vdab.toysforboys.services.OrderService;

@Controller
@RequestMapping("/")
class IndexController {

	private static final String VIEW = "index";
	private final OrderService orderService;

	IndexController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	ModelAndView orders() {
		return new ModelAndView(VIEW, "orders", orderService.findAllButCancelledAndShipped());
	}

	private final static String REDIRECT_NA_DELETE = "redirect:/";

	@PostMapping(params = "shippedId")
	ModelAndView setAsShippedEnUpdateStock(Long[] shippedId, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView(REDIRECT_NA_DELETE);
		StringBuilder mislukt = new StringBuilder("");
		for (Long id : shippedId) {
			orderService.read(id).ifPresent(order -> {
				try {
					orderService.SetAsShippedAndUpdateStock(order);
				} catch (OnvoldoendeVoorraadInStockException ex) {
					String orderId = String.valueOf(order.getId());
					mislukt.append(orderId + ",");
				}
			}); 
		}
		redirectAttributes.addAttribute("aantalMislukt", mislukt.toString());
		return modelAndView;
	}

	@PostMapping()
	ModelAndView setAsShippedZonderIds() {
		return new ModelAndView(REDIRECT_NA_DELETE);
	}
}
