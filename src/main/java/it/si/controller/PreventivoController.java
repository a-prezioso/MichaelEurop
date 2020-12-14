package it.si.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.si.model.Fornitore;
import it.si.model.Preventivo;
import it.si.service.FornitoreService;
import it.si.service.PreventivoService;

@Controller
@RequestMapping("/preventivo")
public class PreventivoController {
	
	@Autowired
	private PreventivoService preventivoService;
	
	@Autowired
	private FornitoreService fornitoreService;
	
	@GetMapping("/gestione")
	public ModelAndView caricaPagina() {
		ModelAndView maw = new ModelAndView("preventivo/preventivo-list");
		List<Fornitore> fornitori = fornitoreService.getAll();
		maw.addObject("fornitoreList", fornitori);
		maw.addObject("chiaveFornitore", 0);
		maw.addObject("preventivoList", null);
		return maw;
	}
	
	@GetMapping(value = "/gestione/list/{id}")
	public ModelAndView caricaPaginaSpeseInvestimentoList (@PathVariable Integer id, HttpSession session) {
		ModelAndView maw = new ModelAndView("preventivo/preventivo-list");
		List<Fornitore> fornitori = fornitoreService.getAll();
		maw.addObject("fornitoreList", fornitori);
		if(id!=0) {
			Fornitore fornitore = fornitoreService.getByIdWithListPreventivi(id);
			session.setAttribute("chiaveFornitore", fornitore.getChiaveFornitore());
			maw.addObject("preventivoList", fornitore.getPreventivi());
		} else {
			maw.addObject("chiaveFornitore", 0);
			maw.addObject("preventivoList", null);
		}
		
		return maw;
	}
	
	@GetMapping(value = "/add")
	public ModelAndView nuovaSpesaInvestimento (HttpSession session) {
		
		ModelAndView maw = new ModelAndView("preventivo/preventivo-edit");
		Preventivo preventivo = new Preventivo();
		preventivo.setFornitore(fornitoreService.getById((Integer) session.getAttribute("chiaveFornitore")));
		maw.addObject("preventivoForm", preventivo);
		
		return maw;
	}
	
	@GetMapping(value = "/update/{id}")
	public ModelAndView modificaSpesaInvestimento (@PathVariable Integer id) {
		
		ModelAndView maw = new ModelAndView("preventivo/preventivo-edit");
		Preventivo preventivo =  preventivoService.getById(id);
		maw.addObject("preventivoForm", preventivo);
		
		return maw;
	}
	
	@PostMapping(value = "/save")
	public ModelAndView salvaSpesaInvestimento (@ModelAttribute ("preventivoForm") Preventivo spesaInvestimento, HttpSession session) {
		preventivoService.saveOrUpdate(spesaInvestimento);
		return new ModelAndView("redirect:/preventivo/gestione/list/"+(Integer) session.getAttribute("chiaveFornitore"));
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView eliminaSpesaInvestimento (@PathVariable Integer id, HttpSession session) {
		preventivoService.delete(id);
		return new ModelAndView("redirect:/preventivo/gestione/list/"+(Integer) session.getAttribute("chiaveFornitore"));
	}
	
	

}
