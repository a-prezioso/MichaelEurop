package it.si.controller;

import java.util.Date;
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
import it.si.model.SpesaInvestimento;
import it.si.service.AreaService;
import it.si.service.SottocategoriaService;
import it.si.service.SpesaInvestimentoService;

@Controller
@RequestMapping("/budget")
public class BudgetController {
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private SottocategoriaService sottocategoriaService;
	
	@Autowired
	private SpesaInvestimentoService spesaInvestimentoService;
	
	@GetMapping(value = "/definizione")
	public ModelAndView caricaPaginaDefinizione () {
		return caricaPaginaArea("budget/budget-definizione");
	}
	
	@GetMapping(value = "/sottocat-list/{id}")
	public ModelAndView caricaPaginaDefinizioneSottocategorie (@PathVariable Integer id,  HttpSession session) {
		session.removeAttribute("urlBudget");
		return caricaPaginaAreaSottocategorie(id,"budget/budget-definizione");
	}
	
	@GetMapping(value = "/update/{id}")
	public ModelAndView definisciBudget (@PathVariable Integer id) {
		ModelAndView maw = new ModelAndView("budget/budget-edit");
		Sottocategoria sottocategoria = sottocategoriaService.getById(id);
		maw.addObject("sottocategoriaForm", sottocategoria);
		return maw;
	}
	
	@PostMapping(value = "/save")
	public ModelAndView salva (@ModelAttribute ("sottocategoriaForm") Sottocategoria sottocategoria) {
		Sottocategoria updSottocategoria= sottocategoriaService.updateBudget(sottocategoria);
		return new ModelAndView("redirect:/budget/sottocat-list/"+updSottocategoria.getArea().getChiaveArea());
	}
	
	@GetMapping(value = "/avanzamento")
	public ModelAndView caricaPaginaAvanzamento () {
		return caricaPaginaArea("budget/budget-avanzamento");
	}
	
	@GetMapping(value = "/sottocat-list-av/{id}")
	public ModelAndView caricaPaginaAvanzamentoSottocategorie (@PathVariable Integer id) {
		return caricaPaginaAreaSottocategorie(id,"budget/budget-avanzamento");
	}
	
	@GetMapping(value = "/spesa-investimento")
	public ModelAndView caricaPaginaSpeseInvestimento () {
		ModelAndView maw = new ModelAndView("budget/spesainv-list");
		List<Sottocategoria> sottocategorie = sottocategoriaService.getAll();
		maw.addObject("sottocategoriaList", sottocategorie);
		maw.addObject("chiaveSottocategoria", 0);
		maw.addObject("spesaInvestimentoList", null);
		return maw;
	}
	
	@GetMapping(value = "/spesainv/list/{id}")
	public ModelAndView caricaPaginaSpeseInvestimentoList (@PathVariable Integer id, HttpSession session) {
		ModelAndView maw = new ModelAndView("budget/spesainv-list");
		List<Sottocategoria> sottocategorie = sottocategoriaService.getAll();
		maw.addObject("sottocategoriaList", sottocategorie);
		if(id!=0) {
			Sottocategoria sottocategoria = sottocategoriaService.getById(id);
			session.setAttribute("chiaveSottocategoria", sottocategoria.getChiaveSottocategoria());
			maw.addObject("spesaInvestimentoList", sottocategoria.getSpeseInvestimento());
		} else {
			maw.addObject("chiaveSottocategoria", 0);
			maw.addObject("spesaInvestimentoList", null);
		}
		
		return maw;
	}
	
	@GetMapping(value = "/spesainv/add")
	public ModelAndView nuovaSpesaInvestimento (HttpSession session) {
		
		ModelAndView maw = new ModelAndView("budget/spesainv-edit");
		SpesaInvestimento spesaInvestimento = new SpesaInvestimento();
		spesaInvestimento.setSottocategoria(sottocategoriaService.getById((Integer) session.getAttribute("chiaveSottocategoria")));
		maw.addObject("spesaInvestimentoForm", spesaInvestimento);
		
		return maw;
	}
	
	@GetMapping(value = "/spesainv/update/{id}")
	public ModelAndView modificaSpesaInvestimento (@PathVariable Integer id) {
		
		ModelAndView maw = new ModelAndView("budget/spesainv-edit");
		SpesaInvestimento spesaInvestimento =  spesaInvestimentoService.getById(id);
		maw.addObject("spesaInvestimentoForm", spesaInvestimento);
		
		return maw;
	}
	
	@PostMapping(value = "/spesainv/save")
	public ModelAndView salvaSpesaInvestimento (@ModelAttribute ("spesaInvestimentoForm") SpesaInvestimento spesaInvestimento, HttpSession session) {
		spesaInvestimentoService.saveOrUpdate(spesaInvestimento);
		return new ModelAndView("redirect:/budget/spesainv/list/"+(Integer) session.getAttribute("chiaveSottocategoria"));
	}
	
	@GetMapping(value = "/spesainv/delete/{id}")
	public ModelAndView eliminaSpesaInvestimento (@PathVariable Integer id, HttpSession session) {
		spesaInvestimentoService.delete(id);
		return new ModelAndView("redirect:/budget/spesainv/list/"+(Integer) session.getAttribute("chiaveSottocategoria"));
	}
	
	@GetMapping("/riconciliazione")
	public String riconciliazione() {
		return "/budget/budget-riconciliazione";
	}
	
	@GetMapping("/riconciliazione/riconcilia")
	public ModelAndView riconcilia(@RequestParam(value = "dataInizio") Date dataInizio, @RequestParam(value = "dataFine") Date dataFine) {
		ModelAndView maw = new ModelAndView("/budget/budget-riconciliazione");
		try {
			sottocategoriaService.riconciliazione(dataInizio,dataFine);
			maw.addObject("ok", true);
		}catch(Exception e) {
			maw.addObject("ok", false);
		}
		return maw;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private ModelAndView caricaPaginaArea (String pagina) {
		ModelAndView maw = new ModelAndView(pagina);
		List<Area> aree = areaService.getAll();
		maw.addObject("areaList", aree);
		maw.addObject("chiaveArea", 0);
		maw.addObject("sottocategoriaList", null);
		return maw;
	};
	
	private ModelAndView caricaPaginaAreaSottocategorie (Integer idArea, String pagina) {
		ModelAndView maw = new ModelAndView(pagina);
		List<Area> aree = areaService.getAll();
		maw.addObject("areaList", aree);
		if(idArea!=0) {
			Area area = areaService.getByIdWithList(idArea);
			maw.addObject("chiaveArea", area.getChiaveArea());
			maw.addObject("sottocategoriaList", area.getSottocategorie());
		} else {
			maw.addObject("chiaveArea", 0);
			maw.addObject("sottocategoriaList", null);
		}
		
		return maw;
	}
	
	

}
