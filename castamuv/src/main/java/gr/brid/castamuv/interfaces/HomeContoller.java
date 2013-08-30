package gr.brid.castamuv.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeContoller {
	@RequestMapping("/")
	public String home() {
		return "home";
	}
}
