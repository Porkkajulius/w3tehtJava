package com.elokuva.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.elokuva.model.Elokuva;
import com.elokuva.model.Kommentti;
public class KommenttiRowMapper implements RowMapper<Kommentti> {
	public Kommentti mapRow(ResultSet rs, int rowNum) throws SQLException {

		Kommentti k = new Kommentti();


		k.setKommenttiID(rs.getInt("kommenttiID"));
		k.setElokuvaID(rs.getInt("elokuvaID"));
		k.setKommenttiTeksti(rs.getString("kommenttiTeksti"));
		return k;
	}
}
