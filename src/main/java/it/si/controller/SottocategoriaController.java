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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.si.model.Area;
import it.si.model.Sottocategoria;
import it.si.service.AreaService;
import it.si.service.SottocategoriaService;

@Controller
@RequestMapping("/sottocategoria")
public class SottocategoriaController {

	@Autowired
	private SottocategoriaService sottocategoriaService;
	
	@Autowired
	private AreaService areaService;
	
	
	@GetMapping(value = "/lista")
	public ModelAndView caricaLista (@RequestParam(required = false, value = "urlBudget") Integer urlBudget,HttpSession session) {
		
		ModelAndView maw = new ModelAndView("sottocategoria/sottocategoria-list");
		List<Sottocategoria> sottocategorie = sottocategoriaService.getAll();
		maw.addObject("sottocategoriaList", sottocategorie);
		if(urlBudget!=null) {
			session.setAttribute("urlBudget", urlBudget);
		}
		
		return maw;
	}
	
	@GetMapping(value = "/add")
	public ModelAndView nuovo () {
		
		ModelAndView maw = new ModelAndView("sottocategoria/sottocategoria-edit");
		Sottocategoria sottocategoria = new Sottocategoria();
		List<Area> aree = areaService.getAll();
		
		maw.addObject("sottocategoriaForm", sottocategoria);
		maw.addObject("areaList", aree);
		
		return maw;
	}
	
	@GetMapping(value = "/update/{id}")
	public ModelAndView modifica (@PathVariable Integer id) {
		
		ModelAndView maw = new ModelAndView("sottocategoria/sottocategoria-edit");
		Sottocategoria sottocategoria =  sottocategoriaService.getById(id);
		List<Area> aree = areaService.getAll();
		
		maw.addObject("sottocategoriaForm", sottocategoria);
		maw.addObject("areaList", aree);
		
		return maw;
	}
	
	@PostMapping(value = "/save")
	public ModelAndView salva (@ModelAttribute ("sottocategoriaForm") Sottocategoria sottocategoria) {
		sottocategoriaService.saveOrUpdate(sottocategoria);
		return new ModelAndView("redirect:/sottocategoria/lista");
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView elimina (@PathVariable Integer id) {
		sottocategoriaService.delete(id);
		return new ModelAndView("redirect:/sottocategoria/lista");
	}
	
}
