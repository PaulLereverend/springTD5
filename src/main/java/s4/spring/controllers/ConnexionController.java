package s4.spring.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.entities.History;
import s4.spring.entities.Languages;
import s4.spring.entities.Scripts;
import s4.spring.entities.Users;
import s4.spring.repositories.CategoryRepository;
import s4.spring.repositories.HistoryRepository;
import s4.spring.repositories.LanguagesRepository;
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
	@Autowired
	private LanguagesRepository languagesRepo; 
	@Autowired
	private CategoryRepository categoryRepo; 
	
	@PostMapping("login")
	public RedirectView login(Model model, Users utilisateur, HttpSession session) {
		Users us = usersRepo.findByLogin(utilisateur.getLogin());
		if (us != null) {
			session.setAttribute("connectedUser", us);
			return new RedirectView("/accueil");
		}
		return new RedirectView("/login");
	}
	
	@GetMapping("login")
	public String loginView(Model model) {
		model.addAttribute("utilisateur", new Users());
		return "login";
	}
	@GetMapping("logout")
	public RedirectView logout(Model model, HttpSession session) {
		session.removeAttribute("connectedUser");
		return new RedirectView("/login");
	}
	/*@GetMapping("index")
	public RedirectView isConnected(Model model, HttpSession session) {
		if(session.getAttribute("connectedUser") == null) {
			return new RedirectView("/login");
		}else {
			return new RedirectView("/accueil");
		}
	}*/
	@GetMapping("accueil")
	public String accueil(Model model, HttpSession session) {
			if (isConnected(session)) {
				model.addAttribute("user", session.getAttribute("connectedUser"));
				model.addAttribute("scripts", scriptsRepo.findAll());
				return "accueil";
			}else {
				return "logout";
			}			
	}
	@GetMapping("script/new")
	public String nouveau(Model model) {
		model.addAttribute("script", new Scripts());
		model.addAttribute("languages", languagesRepo.findAll());
		model.addAttribute("categorys", categoryRepo.findAll());

		return "script/new";
	}
	@GetMapping("script/{id}")
	public String edit(@PathVariable int id, Scripts script, Model model) {
		Optional<Scripts> opt=scriptsRepo.findById(id);
		if(opt.isPresent()) {
			Scripts newScript = opt.get();
			model.addAttribute("script", newScript);
		}
		model.addAttribute("languages", languagesRepo.findAll());
		model.addAttribute("categorys", categoryRepo.findAll());
		//model.addAttribute("script", script);
		return "script/new";
	}
	@PostMapping("script/submit")
	public RedirectView ajouter(@ModelAttribute("scripts") Scripts script, Model model, HttpSession session) {
		System.out.println(script);
		Optional<Scripts> opt = scriptsRepo.findById(script.getId());
		Scripts newScript;
		if(opt.isPresent()) {
			System.out.println("salut");
			newScript = opt.get();
			History hist = new History();
			hist.setScripts(newScript);
			hist.setContent(newScript.getContent());
			hist.setDate(getDate());
			historyRepo.save(hist);
		}else {
			newScript = new Scripts();
			System.out.println("pas salut");
		}
		copyFrom(script, newScript, session);
		scriptsRepo.save(newScript);
		return new RedirectView("/accueil");
	}
	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	private void copyFrom(Scripts source, Scripts dest, HttpSession session) {
		dest.setId(source.getId());
		dest.setTitle(source.getTitle());
		dest.setUser((Users)session.getAttribute("connectedUser"));
		dest.setDescription(source.getDescription());
		dest.setContent(source.getContent());
		dest.setLanguage(source.getLanguage());
		dest.setCategory(source.getCategory());

		dest.setCreation(getDate());
	}
	@GetMapping("script/delete/{id}")
	public RedirectView delete(@PathVariable int id, Scripts script) {
		Optional<Scripts> opt=scriptsRepo.findById(id);
		if(opt.isPresent()) {
			Scripts oldScript = opt.get();
			scriptsRepo.delete(oldScript);
		}
		return new RedirectView("/accueil");
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
	@RequestMapping("createScript")
	@ResponseBody
	public String createScript() {
		Scripts script = new Scripts();
		script.setTitle("Test titre");
		script.setDescription("test description");
		script.setContent("test content");

		scriptsRepo.save(script);
		return script + " ajoutée dans la bdd";
	}
	@RequestMapping("createLanguage")
	@ResponseBody
	public String createLanguage() {
		Languages language = new Languages();
		language.setName("html");
		languagesRepo.save(language);
		return language + " ajoutée dans la bdd";
	}
	
	public boolean isConnected(HttpSession session) {
		if(session.getAttribute("connectedUser") != null) {
			return true;
		}else {
			return false;
		}
	}
}
