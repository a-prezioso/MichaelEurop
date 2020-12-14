package it.si.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.si.model.Fornitore;
import it.si.service.FornitoreService;


@Controller
@RequestMapping("/fornitore")
public class FornitoreController {

	@Autowired
	private FornitoreService fornitoreService;
	
	@GetMapping(value = "/lista")
	public ModelAndView caricaLista () {
		
		ModelAndView maw = new ModelAndView("fornitore/fornitore-list");
		List<Fornitore> fornitori = fornitoreService.getAll();
		maw.addObject("fornitoreList", fornitori);
		
		return maw;
	}
	
	@GetMapping(value = "/add")
	public ModelAndView nuovo () {
		
		ModelAndView maw = new ModelAndView("fornitore/fornitore-edit");
		Fornitore fornitore = new Fornitore();
		maw.addObject("fornitoreForm", fornitore);
		
		return maw;
	}
	
	@GetMapping(value = "/update/{id}")
	public ModelAndView modifica (@PathVariable Integer id) {
		
		ModelAndView maw = new ModelAndView("fornitore/fornitore-edit");
		Fornitore fornitore =  fornitoreService.getById(id);
		maw.addObject("fornitoreForm", fornitore);
		
		return maw;
	}
	
	@PostMapping(value = "/save")
	public ModelAndView salva (@ModelAttribute ("fornitoreForm") Fornitore fornitore) {
		fornitoreService.saveOrUpdate(fornitore);
		return new ModelAndView("redirect:/fornitore/lista");
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView elimina (@PathVariable Integer id) {
		fornitoreService.delete(id);
		return new ModelAndView("redirect:/fornitore/lista");
	}
}
