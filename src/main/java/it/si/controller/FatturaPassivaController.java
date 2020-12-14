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
import org.springframework.web.servlet.ModelAndView;

import it.si.model.FatturaPassiva;
import it.si.model.FatturaPassivaDettaglio;
import it.si.model.Fornitore;
import it.si.service.AliquotaIVAService;
import it.si.service.FatturaPassivaService;
import it.si.service.FornitoreService;
import it.si.service.PreventivoService;
import it.si.service.SpesaInvestimentoService;

@Controller
@RequestMapping("/fattura")
public class FatturaPassivaController {
	
	@Autowired
	private FatturaPassivaService fatturaPassivaService;
	
	@Autowired
	private SpesaInvestimentoService spesaInvestimentoService;
	
	@Autowired
	private PreventivoService preventivoService;
	
	@Autowired
	private FornitoreService fornitoreService;
	
	@Autowired
	private AliquotaIVAService aliquotaService;
	
	
	
	@GetMapping("/registrazione")
	public ModelAndView creazione (HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura/fattura-edit");
		maw.addObject("fatturaPassivaForm", new FatturaPassiva());
		maw.addObject("fornitoreList", fornitoreService.getAll());
		session.setAttribute("dettagli", new ArrayList<FatturaPassivaDettaglio>());
		session.setAttribute("urlFattura", "/menu/sottomenu-fatture-passive");
		return maw;
	}
	
	@PostMapping("/dettaglio/add")
	public ModelAndView nuovaFatturaPassivaDettaglio (@ModelAttribute("fatturaPassivaForm") FatturaPassiva fatturaPassiva, HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura/dettaglio-edit");
		session.setAttribute("fatturaPassiva", fatturaPassiva);
		session.setAttribute("chiaveFornitore", fatturaPassiva.getFornitore()!=null?fatturaPassiva.getFornitore().getChiaveFornitore():null);
		session.setAttribute("chiaveSpesa",null);
		session.setAttribute("chiavePreventivo",null);
		session.setAttribute("chiaveAliquotaIVA",null);
		session.setAttribute("index", null);
		maw.addObject("spesaInvestimentoList", spesaInvestimentoService.getAll());
		maw.addObject("preventivoList", fatturaPassiva.getFornitore()!=null?fatturaPassiva.getFornitore().getPreventivi():null);
		maw.addObject("aliquotaList", aliquotaService.getAll());
		maw.addObject("dettaglioForm", new FatturaPassivaDettaglio());
		return maw;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/dettaglio/update/{index}")
	public ModelAndView modificaFatturaPassivaDettaglio (@PathVariable Integer index, @ModelAttribute("fatturaPassivaForm") FatturaPassiva fatturaPassiva, HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura/dettaglio-edit");
		session.setAttribute("fatturaPassiva", fatturaPassiva);
		session.setAttribute("chiaveFornitore", fatturaPassiva.getFornitore()!=null?fatturaPassiva.getFornitore().getChiaveFornitore():null);
		session.setAttribute("index", index);
		maw.addObject("spesaInvestimentoList", spesaInvestimentoService.getAll());
		maw.addObject("preventivoList", preventivoService.getByFornitore(fatturaPassiva.getFornitore()));
		maw.addObject("aliquotaList", aliquotaService.getAll());
		FatturaPassivaDettaglio dettaglio = ((List<FatturaPassivaDettaglio>) session.getAttribute("dettagli")).get(index);
		session.setAttribute("chiaveSpesa",dettaglio.getSpesaInvestimento()!=null?dettaglio.getSpesaInvestimento().getChiaveSpesaInvestimento():null);
		session.setAttribute("chiavePreventivo",dettaglio.getPreventivo()!=null?dettaglio.getPreventivo().getChiavePreventivo():null);
		session.setAttribute("chiaveAliquotaIVA",dettaglio.getAliquota()!=null?dettaglio.getAliquota().getChiaveAliquotaIVA():null);
		maw.addObject("dettaglioForm", dettaglio);
		return maw;
	}
	
	@PostMapping("/dettaglio/delete/{index}")
	public String rimuoviFatturaPassivaDettaglio (@PathVariable int index, @ModelAttribute("fatturaPassivaForm") FatturaPassiva fatturaPassiva, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<FatturaPassivaDettaglio> dettagli = (List<FatturaPassivaDettaglio>) session.getAttribute("dettagli");
		dettagli.remove(index);
		session.setAttribute("fatturaPassiva", fatturaPassiva);
		session.setAttribute("chiaveFornitore", fatturaPassiva.getFornitore()!=null?fatturaPassiva.getFornitore().getChiaveFornitore():null);
		return "redirect:/fattura/load";
	}
	
	@GetMapping("/load")
	public ModelAndView caricaFattura(HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura/fattura-edit");
		List<Fornitore> fornitoreList = fornitoreService.getAll();
		maw.addObject("fatturaPassivaForm", (FatturaPassiva) session.getAttribute("fatturaPassiva"));
		session.removeAttribute("chiaveSpesa");
		session.removeAttribute("chiavePreventivo");
		session.removeAttribute("chiaveAliquotaIVA");
		session.removeAttribute("fatturaPassiva");
		maw.addObject("fornitoreList", fornitoreList);
		return maw;
	}
	
	@PostMapping("/dettaglio/save")
	public String salvaFatturaPassivaDettaglio (@ModelAttribute("dettaglioForm") FatturaPassivaDettaglio dettaglio, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<FatturaPassivaDettaglio> dettagli = (List<FatturaPassivaDettaglio>) session.getAttribute("dettagli");
		Integer index = (Integer) session.getAttribute("index");
		if(index==null) {
			dettagli.add(dettaglio);
		} else {
			dettagli.set(index, dettaglio);
		}
		return "redirect:/fattura/load";
	}
	
	@PostMapping("/save")
	public String salvaFattura(@ModelAttribute("fatturaPassivaForm") FatturaPassiva fattura, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<FatturaPassivaDettaglio> dettagli = (List<FatturaPassivaDettaglio>) session.getAttribute("dettagli");
		fattura.setDettagli(dettagli);
		fatturaPassivaService.saveOrUpdate(fattura);
		return "redirect:"+(String) session.getAttribute("urlFattura");
	}
	
	@GetMapping("/gestione")
	public ModelAndView gestione() {
		ModelAndView maw = new ModelAndView("fattura/fattura-list");
		List<Fornitore> fornitori = fornitoreService.getAll();
		maw.addObject("fornitoreList", fornitori);
		maw.addObject("chiaveFornitore", 0);
		maw.addObject("fatturaList", null);
		return maw;
	}
	
	@GetMapping(value = "/gestione/list/{id}")
	public ModelAndView gestioneList (@PathVariable Integer id, HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura/fattura-list");
		List<Fornitore> fornitori = fornitoreService.getAll();
		maw.addObject("fornitoreList", fornitori);
		if(id!=0) {
			Fornitore fornitore = fornitoreService.getByIdWithListFatture(id);
			session.setAttribute("chiaveFornitore", fornitore.getChiaveFornitore());
			session.setAttribute("urlFattura", "/fattura/gestione/list/"+fornitore.getChiaveFornitore());
			maw.addObject("fatturaList", fornitore.getFatture());
			
		} else {
			maw.addObject("chiaveFornitore", 0);
			maw.addObject("fatturaList", null);
		}
		
		return maw;
	}
	
	@GetMapping("/add")
	public ModelAndView nuovo (HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura/fattura-edit");
		maw.addObject("fatturaPassivaForm", new FatturaPassiva());
		maw.addObject("fornitoreList", fornitoreService.getAll());
		session.setAttribute("dettagli", new ArrayList<FatturaPassivaDettaglio>());
		
		return maw;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView modifica (@PathVariable Integer id, HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura/fattura-edit");
		FatturaPassiva fattura = fatturaPassivaService.getByIdWithDetails(id);
		maw.addObject("fatturaPassivaForm", fattura);
		maw.addObject("fornitoreList", fornitoreService.getAll());
		session.setAttribute("dettagli", fattura.getDettagli());
		return maw;
	}
	
	@GetMapping("/delete/{id}")
	public String elimina (@PathVariable Integer id, HttpSession session) {
		fatturaPassivaService.delete(id);
		return "redirect:/fattura/gestione/list/"+session.getAttribute("chiaveFornitore");
	}

}
