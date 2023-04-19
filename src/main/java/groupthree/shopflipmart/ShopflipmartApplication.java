package groupthree.shopflipmart;

import groupthree.shopflipmart.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopflipmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopflipmartApplication.class, args);
		System.out.println("http://localhost:8082/login");
	}

}
