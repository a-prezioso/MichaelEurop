package it.si.controller;


import java.util.ArrayList;
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

import it.si.model.Fornitore;
import it.si.model.OrdineAcquisto;
import it.si.model.OrdineAcquistoDettaglio;
import it.si.model.Progetto;
import it.si.model.Sottocategoria;
import it.si.service.FornitoreService;
import it.si.service.OrdineAcquistoService;
import it.si.service.ProgettoService;
import it.si.service.SottocategoriaService;
import it.si.service.SpesaInvestimentoService;

@Controller
@RequestMapping("/ordine")
public class OrdineAcquistoController{

	@Autowired
	private OrdineAcquistoService ordineAcquistoService;
	
	@Autowired
	private SpesaInvestimentoService spesaInvestimentoService;
	
	@Autowired
	private ProgettoService progettoService;
	
	@Autowired
	private FornitoreService fornitoreService;
	
	@Autowired
	private SottocategoriaService sottocategoriaService;
	
	
	
	@GetMapping("/creazione")
	public ModelAndView creazione (HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine/ordine-edit");
		maw.addObject("ordineAcquistoForm", new OrdineAcquisto());
		maw.addObject("fornitoreList", fornitoreService.getAll());
		session.setAttribute("dettagli", new ArrayList<OrdineAcquistoDettaglio>());
		session.setAttribute("urlOrdine", "/menu/sottomenu-ordini-acquisto");
		return maw;
	}
	
	@PostMapping("/dettaglio/add")
	public ModelAndView nuovoOrdineAcquistoDettaglio (@ModelAttribute("ordineAcquistoForm") OrdineAcquisto ordineAcquisto, HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine/dettaglio-edit");
		session.setAttribute("ordineAcquisto", ordineAcquisto);
		session.setAttribute("chiaveFornitore", ordineAcquisto.getFornitore()!=null?ordineAcquisto.getFornitore().getChiaveFornitore():null);
		session.setAttribute("chiaveSpesa",null);
		session.setAttribute("chiaveProgetto",null);
		session.setAttribute("index", null);
		maw.addObject("spesaInvestimentoList", spesaInvestimentoService.getAll());
		maw.addObject("progettoList", progettoService.getAll());
		maw.addObject("dettaglioForm", new OrdineAcquistoDettaglio());
		return maw;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/dettaglio/update/{index}")
	public ModelAndView modificaOrdineAcquistoDettaglio (@PathVariable Integer index, @ModelAttribute("ordineAcquistoForm") OrdineAcquisto ordineAcquisto, HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine/dettaglio-edit");
		session.setAttribute("ordineAcquisto", ordineAcquisto);
		session.setAttribute("chiaveFornitore", ordineAcquisto.getFornitore()!=null?ordineAcquisto.getFornitore().getChiaveFornitore():null);
		session.setAttribute("index", index);
		maw.addObject("spesaInvestimentoList", spesaInvestimentoService.getAll());
		maw.addObject("progettoList", progettoService.getAll());
		OrdineAcquistoDettaglio dettaglio = ((List<OrdineAcquistoDettaglio>) session.getAttribute("dettagli")).get(index);
		session.setAttribute("chiaveSpesa",dettaglio.getSpesaInvestimento()!=null?dettaglio.getSpesaInvestimento().getChiaveSpesaInvestimento():null);
		session.setAttribute("chiaveProgetto",dettaglio.getProgetto()!=null?dettaglio.getProgetto().getChiaveProgetto():null);
		maw.addObject("dettaglioForm", dettaglio);
		return maw;
	}
	
	@PostMapping("/dettaglio/delete/{index}")
	public String rimuoviOrdineAcquistoDettaglio (@PathVariable int index, @ModelAttribute("ordineAcquistoForm") OrdineAcquisto ordineAcquisto, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<OrdineAcquistoDettaglio> dettagli = (List<OrdineAcquistoDettaglio>) session.getAttribute("dettagli");
		dettagli.remove(index);
		session.setAttribute("ordineAcquisto", ordineAcquisto);
		session.setAttribute("chiaveFornitore", ordineAcquisto.getFornitore()!=null?ordineAcquisto.getFornitore().getChiaveFornitore():null);
		return "redirect:/ordine/load";
	}
	
	@GetMapping("/load")
	public ModelAndView caricaOrdine(HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine/ordine-edit");
		List<Fornitore> fornitoreList = fornitoreService.getAll();
		maw.addObject("ordineAcquistoForm", (OrdineAcquisto) session.getAttribute("ordineAcquisto"));
		session.removeAttribute("chiaveSpesa");
		session.removeAttribute("chiaveProgetto");
		session.removeAttribute("ordineAcquisto");
		maw.addObject("fornitoreList", fornitoreList);
		return maw;
	}
	
	@PostMapping("/dettaglio/save")
	public String salvaOrdineAcquistoDettaglio (@ModelAttribute("dettaglioForm") OrdineAcquistoDettaglio dettaglio, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<OrdineAcquistoDettaglio> dettagli = (List<OrdineAcquistoDettaglio>) session.getAttribute("dettagli");
		Integer index = (Integer) session.getAttribute("index");
		if(index==null) {
			dettagli.add(dettaglio);
		} else {
			dettagli.set(index, dettaglio);
		}
		return "redirect:/ordine/load";
	}
	
	@PostMapping("/save")
	public String salvaOrdine(@ModelAttribute("ordineAcquistoForm") OrdineAcquisto ordine, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<OrdineAcquistoDettaglio> dettagli = (List<OrdineAcquistoDettaglio>) session.getAttribute("dettagli");
		ordine.setDettagli(dettagli);
		ordineAcquistoService.saveOrUpdate(ordine);
		return "redirect:"+(String) session.getAttribute("urlOrdine");
	}
	
	@GetMapping("/gestione")
	public ModelAndView gestione() {
		ModelAndView maw = new ModelAndView("ordine/ordine-list");
		List<Fornitore> fornitori = fornitoreService.getAll();
		maw.addObject("fornitoreList", fornitori);
		maw.addObject("chiaveFornitore", 0);
		maw.addObject("ordineList", null);
		return maw;
	}
	
	@GetMapping(value = "/gestione/list/{id}")
	public ModelAndView gestioneList (@PathVariable Integer id, HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine/ordine-list");
		List<Fornitore> fornitori = fornitoreService.getAll();
		maw.addObject("fornitoreList", fornitori);
		if(id!=0) {
			Fornitore fornitore = fornitoreService.getByIdWithListOrdini(id);
			session.setAttribute("chiaveFornitore", fornitore.getChiaveFornitore());
			session.setAttribute("urlOrdine", "/ordine/gestione/list/"+fornitore.getChiaveFornitore());
			maw.addObject("ordineList", fornitore.getOrdini());
			
		} else {
			maw.addObject("chiaveFornitore", 0);
			maw.addObject("ordineList", null);
		}
		
		return maw;
	}
	
	@GetMapping("/add")
	public ModelAndView nuovo (HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine/ordine-edit");
		maw.addObject("ordineAcquistoForm", new OrdineAcquisto());
		maw.addObject("fornitoreList", fornitoreService.getAll());
		session.setAttribute("dettagli", new ArrayList<OrdineAcquistoDettaglio>());
		
		return maw;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView modifica (@PathVariable Integer id, HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine/ordine-edit");
		OrdineAcquisto ordine = ordineAcquistoService.getByIdWithDetails(id);
		maw.addObject("ordineAcquistoForm", ordine);
		maw.addObject("fornitoreList", fornitoreService.getAll());
		session.setAttribute("dettagli", ordine.getDettagli());
		return maw;
	}
	
	@GetMapping("/delete/{id}")
	public String elimina (@PathVariable Integer id, HttpSession session) {
		ordineAcquistoService.delete(id);
		return "redirect:/ordine/gestione/list/"+session.getAttribute("chiaveFornitore");
	}
	
	@GetMapping("/ricerca")
	public ModelAndView ricerca() {
		ModelAndView maw = new ModelAndView("ordine/ricerca-list");
		List<Fornitore> fornitori = fornitoreService.getAll();
		List<Progetto> progetti = progettoService.getAll();
		List<Sottocategoria> sottocategorie = sottocategoriaService.getAll();
		maw.addObject("fornitoreList", fornitori);
		maw.addObject("progettoList", progetti);
		maw.addObject("sottocategoriaList", sottocategorie);
		maw.addObject("chiaveFornitore", 0);
		maw.addObject("chiaveProgetto", 0);
		maw.addObject("chiaveSottocategoria", 0);
		maw.addObject("ordineList", null);
		maw.addObject("chiaveOrdine", null);
		return maw;
	}
	
	@GetMapping(value = "/ricerca/list")
	public ModelAndView ricercaList (@RequestParam (value = "idF") Integer idFornitore, @RequestParam (value = "idP") Integer idProgetto, @RequestParam (value = "idS") Integer idSottocategoria, HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine/ricerca-list");
		List<Fornitore> fornitori = fornitoreService.getAll();
		List<Progetto> progetti = progettoService.getAll();
		List<Sottocategoria> sottocategorie = sottocategoriaService.getAll();
		maw.addObject("fornitoreList", fornitori);
		maw.addObject("progettoList", progetti);
		maw.addObject("sottocategoriaList", sottocategorie);
		
		List<OrdineAcquisto> ordini = ordineAcquistoService.ricercaOrdini(idFornitore, idProgetto, idSottocategoria);
		
		session.setAttribute("chiaveFornitore", idFornitore);
		session.setAttribute("chiaveProgetto", idProgetto);
		session.setAttribute("chiaveSottocategoria", idSottocategoria);
		maw.addObject("ordineList", ordini);
		maw.addObject("chiaveOrdine", null);
		return maw;
	}
	
	@GetMapping(value = "/ricerca/list/{id}")
	public ModelAndView ricercaListDettagli (@PathVariable Integer id, HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine/ricerca-list");
		List<Fornitore> fornitori = fornitoreService.getAll();
		List<Progetto> progetti = progettoService.getAll();
		List<Sottocategoria> sottocategorie = sottocategoriaService.getAll();
		
		List<OrdineAcquisto> ordini = ordineAcquistoService.ricercaOrdini(
				(Integer) session.getAttribute("chiaveFornitore"), 
				(Integer) session.getAttribute("chiaveProgetto"), 
				(Integer) session.getAttribute("chiaveSottocategoria"));
		
		maw.addObject("fornitoreList", fornitori);
		maw.addObject("progettoList", progetti);
		maw.addObject("sottocategoriaList", sottocategorie);
		maw.addObject("ordineList", ordini);
		maw.addObject("chiaveOrdine", id);
		maw.addObject("dettagli", ordineAcquistoService.getByIdWithDetails(id).getDettagli());
		return maw;
	}
	
	
	
	
	
	
}
