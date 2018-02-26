package com.elokuva.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.elokuva.dao.ElokuvaDao;
import com.elokuva.model.Elokuva;
import com.elokuva.model.Kommentti;

@Controller
@RequestMapping(value = "/")
public class ElokuvaController {

	@Autowired
	ElokuvaDao edao;

	// Elokuvan luonti form
	@RequestMapping(value = "/elokuva/uusi", method = RequestMethod.GET)
	public String LuoE(Model model) throws IOException {
		Elokuva elokuva = new Elokuva();
		model.addAttribute("elokuva", elokuva);
		return "uusiElokuva";
	}

	// Elokuva formin tietojen vastaanotto
	@RequestMapping(value = "/elokuva/uusi", method = RequestMethod.POST)
	public String vastaanotaE(@ModelAttribute(value = "elokuva") Elokuva elokuva) {
		edao.luoElokuva(elokuva);
		return "redirect:/elokuvat";
	}

	// Kommentti formin luonti
	@RequestMapping(value = "/elokuva/kommentoi/{id}", method = RequestMethod.GET)
	public String LuoK(@PathVariable("id") int id, Model model)
			throws IOException {
	
		Kommentti kommentti = new Kommentti();
		kommentti.setElokuvaID(id);
		model.addAttribute("kommentti", kommentti);
		return "Kommentoi";
	}
	
	// Elokuvan poistaminen
		@RequestMapping(value = "/elokuva/poista/{id}", method = RequestMethod.GET)
		public String poistaElokuva(@PathVariable("id") int id, Model model)
				throws IOException {

			edao.poistaElokuva(id);
			return "redirect:/elokuvat";
		}

	// Kommentti formin tietojen vastaanotto
		@RequestMapping(value = "/elokuva/kommentoi/{id}", method = RequestMethod.POST)
		public String vastaanotaK(@ModelAttribute(value = "kommentti") Kommentti kommentti) {
			edao.luoKommentti(kommentti);
			
			return "redirect:/elokuvat";
		}
		
	// Elokuvien listaus HTML
		@RequestMapping(value = "/elokuvatUusi", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
		public String listaaE(Model model) throws IOException {

			return "elokuvaListaUusi.html";
	}


	// Elokuvien listaus db:stä
	@RequestMapping(value = "/elokuvat", method = RequestMethod.GET)
	public String listaaElokuvat(Model model) throws IOException {

		List<Elokuva> elokuvat = new ArrayList<Elokuva>();
		elokuvat = edao.findAll();
		model.addAttribute("elokuvat", elokuvat);
		return "elokuvaLista";
	}
	
	// Kommenttilista elokuvalle
		@RequestMapping(value = "/elokuva/kommentit/{id}", method = RequestMethod.GET)
		public String LuoKdd(@PathVariable("id") int id, Model model)
				throws IOException {
	
			List<Kommentti> kommentti = new ArrayList<Kommentti>();
			kommentti = edao.findAllKommentit();
			Elokuva elokuva = new Elokuva();
			elokuva.setElokuvaID(id);
			model.addAttribute("elokuva", elokuva);
			model.addAttribute("kommentti", kommentti);
			return "kommenttiLista";
		}
		
	
		
}