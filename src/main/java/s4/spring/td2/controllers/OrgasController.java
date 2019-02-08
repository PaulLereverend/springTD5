package s4.spring.td2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import s4.spring.td2.entities.Organisation;
import s4.spring.td2.repositories.OrgaRepositoriy;

@Controller
@RequestMapping("/orgas/")
public class OrgasController {
	
	@Autowired
	private OrgaRepositoriy orgasRepo;
	
	@RequestMapping("create")
	@ResponseBody
	public String createOrga() {
		Organisation organisation = new Organisation();
		organisation.setNom("Iut Ifs");
		organisation.setDomain("unicaen.fr");
		organisation.setAliases("iutc3.unicaen.fr");
		orgasRepo.save(organisation);
		
		return organisation + " ajoutée dans la bdd";
	}
}
