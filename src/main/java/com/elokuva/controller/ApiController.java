package com.elokuva.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

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
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.elokuva.dao.ElokuvaDao;
import com.elokuva.model.Elokuva;
import com.elokuva.model.Kommentti;

@Controller
@RequestMapping(value = "/")
public class ApiController {

	@Autowired
	ElokuvaDao edao;

		// Elokuva API listaa elokuvat
		@RequestMapping(value = "/api/elokuva/elokuvat", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
		@ResponseStatus(HttpStatus.OK)
		public @ResponseBody List<Elokuva> apiElokuvat() {
			
			List<Elokuva> elokuvat;
			elokuvat = edao.findAll();

			return elokuvat;
		}
		
		// Elokuvan poistaminen
		@RequestMapping(value = "/api/elokuva/poista/{id}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
		@ResponseStatus(HttpStatus.OK)
		public @ResponseBody String poistaElokuva(@PathVariable("id") int id, Model model)
				throws IOException {

			edao.poistaElokuva(id);
			return "redirect:/elokuvat";
		}
		
		
		// Elokuva API vastaanota uusi elokuva ja lisää tietokantaan
		@RequestMapping(value = "/api/elokuva/uusi", method = {RequestMethod.GET, RequestMethod.POST}, produces="application/json;charset=UTF-8")
		public @ResponseBody String elokuva(@RequestBody Elokuva jsonString) {
			System.out.println("apissa "+jsonString);
			edao.luoElokuva(jsonString);
			return "redirect:/api/elokuva/elokuvat";

		}


}