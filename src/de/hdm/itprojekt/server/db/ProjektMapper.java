package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

public class ProjektMapper {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static ProjektMapper projektMapper = null;

	protected ProjektMapper() {
	};

	public static ProjektMapper projektMapper() {
		if (projektMapper == null) {
			projektMapper = new ProjektMapper();
		}
		return projektMapper;
	}

	//insert
	public Projekt insertProjekt(Projekt p) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idProjekt) AS maxid " + " FROM projekt ");

			if (rs.next()) {

				p.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						" INSERT INTO projekt (idProjekt, beschreibung, bezeichnung, idPerson, idMarktplatz, startDatum, endDatum)"
								+ " VALUES (" 
								+ p.getId() + " ,'" 
								+ p.getBeschreibung() + "','" 
								+ p.getBezeichnung()+ "','" 
								+ p.getIdPerson() + "','" 
								+ p.getIdMarktplatz() + "','" 
								+ format.format(p.getStartDatum())  + "', '" 
								+ format.format(p.getEndDatum())
								+ "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return p;
	}

	//Projekt nach Id ausgeben
	public Projekt findProjektByKey (int idProjekt) {
		
		Connection con = DBConnection.connection();
		
		
	try	{
		Statement stmt = con.createStatement(); 
		
		ResultSet rs = stmt.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson, idMarktplatz FROM projekt"
				+ " WHERE idProjekt= " + idProjekt + " ORDER BY bezeichnung");
		// Projekte sollen alphabetisch nach Namen bzw. Bezeichnung angezeigt werden	
		
		if (rs.next()) {
			Projekt p = new Projekt ();
			p.setId(rs.getInt("idProjekt"));
			p.setBezeichnung(rs.getString("bezeichnung"));
			p.setBeschreibung(rs.getString("beschreibung"));
			p.setStartDatum(rs.getDate("startDatum"));
			p.setEndDatum(rs.getDate("endDatum"));
			p.setIdPerson(rs.getInt("idPerson")); 
			p.setIdMarktplatz(rs.getInt("idMarktplatz"));
			
			return p; 
		}
	}
	catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	}
	return null;
	
}

	//Objekt Projekt ausgeben
	public Projekt findByProjekt(Projekt p){
		  return this.findProjektByKey(p.getId());		  
	 }
	
	//Alle projekte ausgeben
	public Vector<Projekt> findAllProjekt() {
		Connection con = DBConnection.connection();
		Vector<Projekt> result = new Vector<Projekt>();

		try {
			Statement stmt = con.createStatement();

			// Datenbankabfrage aller Projekte alphabetisch sortiert nach
			// bezeichnung

			ResultSet rs = stmt
					.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson, idMarktplatz FROM projekt " 
			+ " ORDER BY idProjekt DESC");
			while (rs.next()) {
				Projekt p = new Projekt();
				p.setId(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setIdPerson(rs.getInt("idPerson"));
				p.setIdMarktplatz(rs.getInt("idMarktplatz"));
			
				result.addElement(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	//Projekte nach Bezeichnung ausgeben
	public Vector<Projekt> findProjektByBezeichnung(String bezeichnung) {
		Connection con = DBConnection.connection();
		Vector<Projekt> result = new Vector<Projekt>();

		try {
			Statement stmt = con.createStatement();

			// SQL Statement, gibt Eintraege aus welche die eingegeben
			// Bezeichung enth�lt
			ResultSet rs = stmt
					.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson, idMarktplatz FROM projekt "
							+ " WHERE bezeichnung= '" + bezeichnung + "' ORDER BY bezeichnung");

			while (rs.next()) {
				Projekt p = new Projekt();
				p.setId(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setIdPerson(rs.getInt("idPerson")); 
				p.setIdMarktplatz(rs.getInt("idMarktplatz"));
				
				result.addElement(p);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//Marktplatz mit dem zugehörigen Projekt ausgeben
	public Vector<Projekt> findProjektbyMarktplatz(int idMarktplatz) {
		Connection con = DBConnection.connection();
		Vector<Projekt> result = new Vector<Projekt>();

		try {
			Statement stmt = con.createStatement();

			// SQL Statement, gibt Eintraege aus welche die eingegeben
			// Bezeichung enth�lt
			ResultSet rs = stmt
					.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson, idMarktplatz FROM projekt "
							+ " WHERE idMarktplatz= '" + idMarktplatz + "' ORDER BY idProjekt DESC");

			while (rs.next()) {
				Projekt p = new Projekt();
				p.setId(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setIdPerson(rs.getInt("idPerson")); 
				p.setIdMarktplatz(rs.getInt("idMarktplatz"));
				
				result.addElement(p);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//Personen mit dem zugehörigen Projekten ausgeben
	public Vector<Projekt> findProjektbyPerson(int idPerson) {
		Connection con = DBConnection.connection();
		Vector<Projekt> result = new Vector<Projekt>();

		try {
			Statement stmt = con.createStatement();

			// SQL Statement, gibt Eintraege aus welche die eingegeben
			// Bezeichung enth�lt
			ResultSet rs = stmt
					.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson, idMarktplatz FROM projekt "
							+ "WHERE idPerson= '" + idPerson + "' ORDER BY idProjekt DESC");

			while (rs.next()) {
				Projekt p = new Projekt();
				p.setId(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setIdPerson(rs.getInt("idPerson")); 
				p.setIdMarktplatz(rs.getInt("idMarktplatz"));
				
				result.addElement(p);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//update
	public Projekt updateProjekt(Projekt p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// SQL Statment, welches das Updaten von Projekte erlaubt

//			stmt.executeUpdate("UPDATE projekt " + " SET bezeichnung='" + p.getBezeichnung() + "' ,"
//				+ "beschreibung='" + p.getBeschreibung() + "' ,'" 
//					+ "startDatum='" + format.format(p.getStartDatum()) + "' ,'"
//					+ "endDatum='" + format.format(p.getEndDatum()) + "' ,'" 
//					+ "idPerson='" + p.getIdPerson() + "' ,'"
//					+ "idMarktplatz='" + p.getIdMarktplatz() + "' ,'"
//					+ "WHERE idProjekt= '" + p.getId());
			 stmt.executeUpdate("UPDATE projekt " + "SET bezeichnung='"
			          + p.getBezeichnung() + "', beschreibung='" + p.getBeschreibung() + "', startDatum='" 
			          + format.format(p.getStartDatum()) + "', endDatum= '" 
			          + format.format(p.getEndDatum()) 
			          + "'WHERE idProjekt=" + p.getId());

		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	//delete
	public void deleteProjekt(Projekt p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM projekt" + " WHERE idProjekt= " + p.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
