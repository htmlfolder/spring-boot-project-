package batch.e3.product.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Product Management REST API", description = "Rest api which has endpoints for performing crud operation on product table",version = "1.0.0",
contact = @Contact(name = "Poornima",email = "poornig183@gmail.com",url = "https://www.github.com/htmlfolder")))
public class ProductManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementApplication.class, args);
	}

}
