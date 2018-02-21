package com.elokuva.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.elokuva.model.Elokuva;
import com.elokuva.model.Kommentti;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ElokuvaDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//SQL komennot
	final String luoElokuvaSql = "insert into elokuva(elokuvaNimi) values(?)";
	final String luoKommenttiSql = "insert into kommentti(elokuvaID, kommenttiTeksti) values(?,?)";
	final String haeKaikkiElokuvatSql = "select * from elokuva";
	final String haeKaikkiKommentitSql = "select * from kommentti";
	final String haeElokuvaSql = "select * from elokuva where elokuvaID=?";
	final String haeElokuvanKommentitSql = "select * from kommentti where elokuvaID=?";
	final String poistaElokuvanKommentitSql = "delete from kommentti where elokuvaID=?";
	final String poistaElokuvaSql = "delete from elokuva where elokuvaID=?";

	// elokuvan luonti
	public void luoElokuva(Elokuva e) {
		
		// insert lauseet
		final String nimi = e.getNimi();
		// JDBC generoi id:n automaattisesti
		KeyHolder idHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(luoElokuvaSql,
						new String[] { "id" });
				ps.setString(1, nimi);
				return ps;
			}
		}, idHolder);

	}

	// talleta kommentti haluamaan elokuvaan
	public void luoKommentti(Kommentti k) {
		
		final int elokuvaID = k.getElokuvaID();
		final String kommenttiTeksti = k.getKommenttiTeksti();
		KeyHolder idHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(luoKommenttiSql,
						new String[] { "id" });
				ps.setInt(1, elokuvaID);
				ps.setString(2, kommenttiTeksti);
				return ps;
			}
		}, idHolder);
		
	}

	// Listaa kaikki elokuvat
	public List<Elokuva> findAll() {		
		
		RowMapper<Elokuva> mapper = new ElokuvaRowMapper();
		List<Elokuva> elokuvat = jdbcTemplate.query(haeKaikkiElokuvatSql, mapper);		
		return elokuvat;

	}

	// Etsi elokuva ID:llä
	public Elokuva findById(int id) {

		Object[] parametrit = new Object[] { id };
		RowMapper<Elokuva> mapper = new ElokuvaRowMapper();
		Elokuva e;
		try {
			e = jdbcTemplate.queryForObject(haeElokuvaSql, parametrit, mapper);
		} catch (IncorrectResultSizeDataAccessException ex) {
			throw (ex);
		}
		return e;
		
	}
	
	// Listaa kaikki kommentit
		public List<Kommentti> findAllKommentit() {
			
			RowMapper<Kommentti> mapper = new KommenttiRowMapper();
			List<Kommentti> kommentit = jdbcTemplate.query(haeKaikkiKommentitSql, mapper);
			return kommentit;
			
		}

	// Etsi tietyn elokuvan kommentit
	public Elokuva findByIdKommentit(int id) {
		
		Object[] parametrit = new Object[] { id };
		RowMapper<Elokuva> mapper = new ElokuvaRowMapper();
		Elokuva e;
		try {
			e = jdbcTemplate.queryForObject(haeElokuvanKommentitSql, parametrit, mapper);
		} catch (IncorrectResultSizeDataAccessException ex) {
			throw (ex);
		}
		return e;
		
	}

	// Poista elokuva ID:llä
	public void poistaElokuva(int id){
		
		Object[] parametrit = new Object[] { id };
		try {
			jdbcTemplate.update(poistaElokuvanKommentitSql, parametrit);
			jdbcTemplate.update(poistaElokuvaSql, parametrit);
		} catch (IncorrectResultSizeDataAccessException ex) {
			throw (ex);
		}
		
	}


}
