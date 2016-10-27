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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elokuva.dao.ElokuvaDAO;
import com.elokuva.bean.Elokuva;
import com.elokuva.bean.Kommentti;

@Controller
@RequestMapping(value = "/")
public class ElokuvaController {

	@Autowired
	ElokuvaDAO edao;

	// hello testi
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String listInactiveProjects(Model model) throws IOException {

		return "hello";
	}

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
	
	// Kommentti formin tietojen vastaanotto
		@RequestMapping(value = "/elokuva/kommentoi/{id}", method = RequestMethod.POST)
		public String vastaanotaK(@ModelAttribute(value = "kommentti") Kommentti kommentti) {
			edao.luoKommentti(kommentti);
			
			return "redirect:/elokuvat";
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