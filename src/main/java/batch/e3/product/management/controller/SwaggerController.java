package batch.e3.product.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {

	@GetMapping("/")
	public String loadSwagger() {
		return "redirect:/swagger-ui/index.html";
	}
}
