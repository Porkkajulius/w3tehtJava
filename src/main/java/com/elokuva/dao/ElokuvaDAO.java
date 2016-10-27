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

import com.elokuva.bean.Elokuva;
import com.elokuva.bean.Kommentti;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ElokuvaDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// elokuvan luonti
	public void luoElokuva(Elokuva e) {
		// insert lauseet
		final String sql = "insert into elokuva(elokuvaNimi) values(?)";
		final String nimi = e.getNimi();
		// JDBC generoi id:n automaattisesti
		KeyHolder idHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						new String[] { "id" });
				ps.setString(1, nimi);
				return ps;
			}
		}, idHolder);

	}

	// talleta kommentti haluamaan elokuvaan
	public void luoKommentti(Kommentti k) {
		final String sql = "insert into kommentti(elokuvaID, kommenttiTeksti) values(?,?)";

		final int elokuvaID = k.getElokuvaID();
		final String kommenttiTeksti = k.getKommenttiTeksti();

		KeyHolder idHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						new String[] { "id" });
				ps.setInt(1, elokuvaID);
				ps.setString(2, kommenttiTeksti);
				return ps;
			}
		}, idHolder);

	}

	// Listaa kaikki elokuvat
	public List<Elokuva> findAll() {

		String sql = "select * from elokuva";

		RowMapper<Elokuva> mapper = new ElokuvaRowMapper();
		List<Elokuva> elokuvat = jdbcTemplate.query(sql, mapper);

		return elokuvat;

	}

	// Etsi elokuva ID:llä
	public Elokuva findById(int id) {

		String sql = "select * from elokuva where elokuvaID=?";
		Object[] parametrit = new Object[] { id };
		RowMapper<Elokuva> mapper = new ElokuvaRowMapper();

		Elokuva e;
		try {
			e = jdbcTemplate.queryForObject(sql, parametrit, mapper);
		} catch (IncorrectResultSizeDataAccessException ex) {
			throw (ex);
		}
		return e;

	}
	
	// Listaa kaikki kommentit
		public List<Kommentti> findAllKommentit() {

			String sql = "select * from kommentti";

			RowMapper<Kommentti> mapper = new KommenttiRowMapper();
			List<Kommentti> kommentit = jdbcTemplate.query(sql, mapper);

			return kommentit;

		}

	// Etsi tietyn elokuvan kommentit
	public Elokuva findByIdKommentit(int id) {

		String sql = "select * from kommentti where elokuvaID=?";
		Object[] parametrit = new Object[] { id };
		RowMapper<Elokuva> mapper = new ElokuvaRowMapper();

		Elokuva e;
		try {
			e = jdbcTemplate.queryForObject(sql, parametrit, mapper);
		} catch (IncorrectResultSizeDataAccessException ex) {
			throw (ex);
		}
		return e;

	}

}
