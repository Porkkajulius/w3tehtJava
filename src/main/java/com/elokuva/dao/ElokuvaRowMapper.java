package com.elokuva.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.elokuva.model.Elokuva;
import com.elokuva.model.Kommentti;
public class ElokuvaRowMapper implements RowMapper<Elokuva> {
	public Elokuva mapRow(ResultSet rs, int rowNum) throws SQLException {

		Elokuva e = new Elokuva();


		e.setElokuvaID(rs.getInt("elokuvaID"));
		e.setNimi(rs.getString("elokuvaNimi"));
		
		return e;
	}
}
