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

import it.si.model.Area;
import it.si.service.AreaService;

@Controller
@RequestMapping("/area")
public class AreaController {

	@Autowired
	private AreaService areaService;
	
	@GetMapping(value = "/lista")
	public ModelAndView caricaLista () {
		
		ModelAndView maw = new ModelAndView("area/area-list");
		List<Area> aree = areaService.getAll();
		maw.addObject("areaList", aree);
		
		return maw;
	}
	
	@GetMapping(value = "/add")
	public ModelAndView nuovo () {
		
		ModelAndView maw = new ModelAndView("area/area-edit");
		Area area = new Area();
		maw.addObject("areaForm", area);
		
		return maw;
	}
	
	@GetMapping(value = "/update/{id}")
	public ModelAndView modifica (@PathVariable Integer id) {
		
		ModelAndView maw = new ModelAndView("area/area-edit");
		Area area =  areaService.getById(id);
		maw.addObject("areaForm", area);
		
		return maw;
	}
	
	@PostMapping(value = "/save")
	public ModelAndView salva (@ModelAttribute ("areaForm") Area area) {
		areaService.saveOrUpdate(area);
		return new ModelAndView("redirect:/area/lista");
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView elimina (@PathVariable Integer id) {
		areaService.delete(id);
		return new ModelAndView("redirect:/area/lista");
	}
	
}
