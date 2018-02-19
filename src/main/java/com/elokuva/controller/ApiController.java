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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.elokuva.dao.ElokuvaDAO;
import com.elokuva.model.Elokuva;
import com.elokuva.model.Kommentti;

@Controller
@RequestMapping(value = "/")
public class ApiController {

	@Autowired
	ElokuvaDAO edao;

		// Elokuva API listaa elokuvat
		@RequestMapping(value = "/api/elokuva/elokuvat", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
		@ResponseStatus(HttpStatus.OK)
		public @ResponseBody List<Elokuva> apiElokuvat() {
			
			List<Elokuva> elokuvat;
			elokuvat = edao.findAll();

			return elokuvat;
		}
		
		// Elokuva API vastaanota uusi elokuva ja lisää tietokantaan
		@RequestMapping(value = "/api/elokuva/uusi", method = {RequestMethod.GET, RequestMethod.POST}, produces="application/json;charset=UTF-8")
		public @ResponseBody String elokuva(@RequestBody Elokuva jsonString) {
			System.out.println("apissa "+jsonString);
			edao.luoElokuva(jsonString);
			return "redirect:/api/elokuva/elokuvat";

		}
		

		// Elokuvan lisäys API:n avulla toisesta palvelusta
		@RequestMapping(value = "/vuokraamo/uusi", method = RequestMethod.GET)
		public String LuoElokuva(Model model) throws IOException {
			String elokuva=null;
			model.addAttribute("elokuva", elokuva);
			return "vuokraamoLisaaElokuva";
		}
		
		// Elokuva lähetä elokuva Apille
	/*	@RequestMapping(value = "/vuokraamo/uusi", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
		@ResponseBody 
		public String vastaanotaElokuva(@RequestBody String elokuva) {
			System.out.println("vuokraamo lähettää apille elokuvan "+elokuva);
			return "redirect:/api/elokuva/uusi";

		}
		*/
		
		// Elokuva lähetä elokuva Apille
		@RequestMapping(value = "/vuokraamo/uusi", method = RequestMethod.POST)
		@ResponseBody 
		public String vastaanotaElokuva(@RequestBody String elokuva) {
			System.out.println("vuokraamo lähettää apille elokuvan "+elokuva);
			return "redirect:/api/elokuva/uusi";

		}


		

}