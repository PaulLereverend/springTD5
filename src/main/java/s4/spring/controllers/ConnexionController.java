package s4.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.entities.Users;
import s4.spring.repositories.HistoryRepository;
import s4.spring.repositories.ScriptsRepository;
import s4.spring.repositories.UsersRepository;



@Controller
@RequestMapping("")
public class ConnexionController {
	@Autowired
	private ScriptsRepository scriptsRepo;
	@Autowired
	private UsersRepository usersRepo;
	@Autowired
	private HistoryRepository historyRepo; 
	
	@PostMapping("login")
	public RedirectView login(Model model, Users utilisateur, HttpSession session) {
		List<Users> us = usersRepo.findAll();
		for (Users user : us) {
			if (utilisateur.getLogin().equals(user.getLogin()) && utilisateur.getPassword().equals(user.getPassword())) {
				session.setAttribute("connectedUser", user);
				return new RedirectView("/index");
			}
		}
		return new RedirectView("/login");
	}
	
	@GetMapping("login")
	public String loginView(Model model) {
		model.addAttribute("utilisateur", new Users());
		return "login";
	}
	@GetMapping("index")
	public RedirectView isConnected(Model model, HttpSession session) {
		if(session.getAttribute("connectedUser") == null) {
			return new RedirectView("/login");
		}else {
			return new RedirectView("/accueil");
		}
	}
	@GetMapping("accueil")
	public String accueil(Model model, HttpSession session) {
		model.addAttribute("user", session.getAttribute("connectedUser"));
		return "accueil";
	}
	@RequestMapping("create")
	@ResponseBody
	public String createUser() {
		Users user = new Users();
		user.setIdentity("Hugo Huet");
		user.setLogin("login");
		user.setPassword("password");
		user.setEmail("email.e@gmail.com");

		usersRepo.save(user);
		return user + " ajoutée dans la bdd";
	}
}
