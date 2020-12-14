package it.si.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController{

	@GetMapping(value = "/")
	public String start () {
		return "redirect:/menu/principale";
	}
	
	@GetMapping(value = "/login")
	public String getLogin (ModelAndView maw) {
		return "login";
	}
	
	
	@GetMapping(value = "/menu/principale")
	public String caricaMenu () {
		return "menu/principale";
	}
	
	
	@GetMapping(value = "menu/{sottomenu}" )
	public String caricaSottoMenu( @PathVariable String sottomenu, HttpSession session){
		pulisciSessione(session);
		return "menu/"+sottomenu;
	}
	
	@GetMapping(value = "menu/sottomenu-archivi/{scelta}")
	public String caricaControllerArchivi( @PathVariable String scelta){
		return "redirect:/"+scelta+"/lista";
	}
	
	@GetMapping(value = "menu/sottomenu-budget/budget/{scelta}" )
	public String caricaControllerBudget( @PathVariable String scelta){
		return "redirect:/budget/"+scelta;
	}
	
	@GetMapping(value = "menu/sottomenu-ordini-acquisto/ordine/{scelta}" )
	public String caricaControllerOrdineAcquisto( @PathVariable String scelta){
		return "redirect:/ordine/"+scelta;
	}
	
	@GetMapping(value = "menu/sottomenu-fatture-passive/fattura/{scelta}" )
	public String caricaControllerFatturaPassiva( @PathVariable String scelta){
		return "redirect:/fattura/"+scelta;
	}
	
	
	
	
	
	private void pulisciSessione(HttpSession session) {
		Enumeration<String> em = session.getAttributeNames();
		while (em.hasMoreElements()) {
		    String element = em.nextElement();
		    session.removeAttribute(element);
		}
	}
	
}
