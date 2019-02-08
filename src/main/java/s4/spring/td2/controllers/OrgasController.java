package s4.spring.td2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orgas/")
public class OrgasController {
	
	@RequestMapping("create")
	@ResponseBody
	public String createOrga() {
		return "";
	}
}
