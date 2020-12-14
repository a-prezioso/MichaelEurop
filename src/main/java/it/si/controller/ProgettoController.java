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

import it.si.model.Progetto;
import it.si.service.ProgettoService;


@Controller
@RequestMapping("/progetto")
public class ProgettoController {

	
	@Autowired
	private ProgettoService progettoService;
	
	@GetMapping(value = "/lista")
	public ModelAndView caricaLista () {
		
		ModelAndView maw = new ModelAndView("progetto/progetto-list");
		List<Progetto> progetti = progettoService.getAll();
		maw.addObject("progettoList", progetti);
		
		return maw;
	}
	
	@GetMapping(value = "/add")
	public ModelAndView nuovo () {
		
		ModelAndView maw = new ModelAndView("progetto/progetto-edit");
		Progetto progetto = new Progetto();
		maw.addObject("progettoForm", progetto);
		
		return maw;
	}
	
	@GetMapping(value = "/update/{id}")
	public ModelAndView modifica (@PathVariable Integer id) {
		
		ModelAndView maw = new ModelAndView("progetto/progetto-edit");
		Progetto progetto =  progettoService.getById(id);
		maw.addObject("progettoForm", progetto);
		
		return maw;
	}
	
	@PostMapping(value = "/save")
	public ModelAndView salva (@ModelAttribute ("progettoForm") Progetto progetto) {
		progettoService.saveOrUpdate(progetto);
		return new ModelAndView("redirect:/progetto/lista");
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView elimina (@PathVariable Integer id) {
		progettoService.delete(id);
		return new ModelAndView("redirect:/progetto/lista");
	}
}
