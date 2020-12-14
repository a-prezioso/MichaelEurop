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

import it.si.model.AliquotaIVA;
import it.si.service.AliquotaIVAService;

@Controller
@RequestMapping("/aliquota")
public class AliquotaIVAController{

	@Autowired
	private AliquotaIVAService aliquotaService;

	@GetMapping(value = "/lista")
	public ModelAndView caricaLista () {
		
		ModelAndView maw = new ModelAndView("aliquota/aliquota-list");
		List<AliquotaIVA> aliquote = aliquotaService.getAll();
		maw.addObject("aliquotaList", aliquote);
		
		return maw;
	}
	
	@GetMapping(value = "/add")
	public ModelAndView nuovo () {
		
		ModelAndView maw = new ModelAndView("aliquota/aliquota-edit");
		AliquotaIVA aliquota = new AliquotaIVA();
		maw.addObject("aliquotaForm", aliquota);
		
		return maw;
	}
	
	@GetMapping(value = "/update/{id}")
	public ModelAndView modifica (@PathVariable Integer id) {
		
		ModelAndView maw = new ModelAndView("aliquota/aliquota-edit");
		AliquotaIVA aliquota =  aliquotaService.getById(id);
		maw.addObject("aliquotaForm", aliquota);
		
		return maw;
	}
	
	@PostMapping(value = "/save")
	public ModelAndView salva (@ModelAttribute ("aliquotaForm") AliquotaIVA aliquota) {
		aliquotaService.saveOrUpdate(aliquota);
		return new ModelAndView("redirect:/aliquota/lista");
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView elimina (@PathVariable Integer id) {
		aliquotaService.delete(id);
		return new ModelAndView("redirect:/aliquota/lista");
	}
}
