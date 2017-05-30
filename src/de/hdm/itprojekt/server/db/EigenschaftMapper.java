package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;
import java.*;		//Pakete, welche zum Ausf�hren ben�tigt werden.
import javax.*;
import java.sql.*;

import de.hdm.itprojekt.shared.bo.*;

public class EigenschaftMapper {

	private static EigenschaftMapper eigenschaftMapper = null;

	protected EigenschaftMapper() {
	};

	public static EigenschaftMapper eigenschaftMapper() {
		if (eigenschaftMapper == null) {
			eigenschaftMapper = new EigenschaftMapper();
		}
		return eigenschaftMapper;
	}

	public Eigenschaft insertEigenschaft (Eigenschaft eig) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idEigenschaft) AS maxid " + "FROM eigenschaft ");

			if (rs.next()) {

				eig.setIdEigenschaft(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO eigenschaft (idEigenschaft, ausbildung, abschluss, berufserfahrungsJahre, arbeitsgebiet, sprachkenntnisse, employmentStatus)"
									+ " VALUES ('" + eig.getIdEigenschaft() + "','" 
									+ eig.getAusbildung() + "','" 
									+ eig.getAbschluss()+ "','" 
									+ eig.getBerufserfahrungsJahre() + "','" 
									+ eig.getArbeitsgebiet() + "','"
									+ eig.getSprachkenntnisse() + "','" 
									+ eig.getEmploymentStatus() + "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eig;
	}

	public Eigenschaft findEigenschaftById(int idEigenschaft) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idEigenschaft, ausbildung, abschluss, berufserfahrungsJahre, arbeitsgebiet, sprachkenntnisse, employmentStatus FROM eigenschaft "
											+ " WHERE idEigenschaft= " + idEigenschaft 
											+ " ORDER BY idEigenschaft");
			
			// Eigenschaft sollen nach id angezeigt werden
			if (rs.next()) {
				Eigenschaft eig = new Eigenschaft();
				eig.setIdEigenschaft(rs.getInt("'idEigenschaft'"));
				eig.setAusbildung(rs.getString("'ausbildung'"));
				eig.setAbschluss(rs.getString("'abschluss'"));
				eig.setBerufserfahrungsJahre(rs.getFloat("'berufserfahrungsJahre'"));
				eig.setArbeitsgebiet(rs.getString("'arbeitsgebiet'"));
				eig.setSprachkenntnisse(rs.getString("'sprachkenntnisse'"));
				eig.setEmploymentStatus(rs.getString("'employmentStatus'"));

				return eig;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public Vector<Eigenschaft> findAllEigenschaften () {
		Connection con = DBConnection.connection();
		Vector<Eigenschaft> vec = new Vector<Eigenschaft>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT idEigenschaft, ausbildung, abschluss, berufserfahrungsJahre, arbeitsgebiet, sprachkenntnisse, employmentStatus "
							+ " FROM eigenschaft " + " ORDER BY idEigenschaft ");

			while (rs.next()) {
				Eigenschaft eig = new Eigenschaft();
				eig.setIdEigenschaft(rs.getInt("idEigenschaft"));
				eig.setAusbildung(rs.getString("ausbildung"));
				eig.setAbschluss(rs.getString("abschluss"));
				eig.setBerufserfahrungsJahre(rs.getFloat("berufserfahrungsJahre"));
				eig.setArbeitsgebiet(rs.getString("arbeitsgebiet"));
				eig.setSprachkenntnisse(rs.getString("sprachkenntnisse"));
				eig.setEmploymentStatus(rs.getString("employmentStatus"));

				vec.addElement(eig);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vec;
	}

	public Eigenschaft updateEigenschaft (Eigenschaft eig) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

					stmt.executeUpdate("UPDATE eigenschaft " 
					+ "SET idEigenschaft='" + eig.getIdEigenschaft() + "' ,'" 
					+ "ausbildung='" + eig.getAusbildung() + "' ,'" 
					+ "abschluss='" + eig.getAbschluss() + "' ,'" 
					+ "berufserfahrungsJahre='" + eig.getBerufserfahrungsJahre() + "' ,'" 
					+ "arbeitsgebiet='" + eig.getArbeitsgebiet() + "' ,'" 
					+ "sprachkenntnisse='" + eig.getSprachkenntnisse() + "' ,'" 		
					+ "employmentStatus='" + eig.getEmploymentStatus() + "' ,'" 
					+ "WHERE idEigenschaft ='"+ eig.getIdEigenschaft());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eig;
	}

	public void deleteEigenschaft (Eigenschaft eig) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM eigenschaft " 
								+ " WHERE idEigenschaft= " + eig.getIdEigenschaft());
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		}
	}
		
	