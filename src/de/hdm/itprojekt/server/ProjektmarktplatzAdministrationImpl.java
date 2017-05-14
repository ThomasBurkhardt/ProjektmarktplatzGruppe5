package de.hdm.itprojekt.server;

import java.util.ArrayList;
import java.util.Vector;

import de.hdm.itprojekt.server.db.*;
import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ProjektmarktplatzAdministrationImpl extends RemoteServiceServlet
		implements ProjektmarktplatzAdministration {


	//private BusinessObjectMapper boMapper = null;
	private EigenschaftMapper eigMapper = null;
	private OrganisationseinheitMapper orgaMapper = null; // Bleibt die Mapper bestehen?
	private PartnerprofilMapper ppMapper = null;
	private PersonMapper persMapper = null;
	private ProjektMapper prjktMapper = null;
	private ProjektmarktplatzMapper pmpMapper = null;
	private TeamMapper teamMapper = null;
	private UnternehmenMapper unternehmenMapper = null;
	
	//No-Argument-Konstruktor
	public ProjektmarktplatzAdministrationImpl() throws IllegalArgumentException{
		
	}

	//Initialisierung
	public void init() throws IllegalArgumentException{
		
		//this.boMapper = BusinessObjectMapper.businessObjectMapper(); (Klasse wird gel�scht)
		this.eigMapper = EigenschaftMapper.eigenschaftMapper();
		this.orgaMapper = OrganisationseinheitMapper.organisationseinheitMapper();
		this.ppMapper = PartnerprofilMapper.partnerprofilMapper();
		this.persMapper = PersonMapper.personMapper();
		this.prjktMapper = ProjektMapper.projektMapper();
		this.pmpMapper = ProjektmarktplatzMapper.projektmarktplatzMapper();
		this.teamMapper = TeamMapper.teamMapper();
		this.unternehmenMapper = UnternehmenMapper.unternehmenMapper();	
	}
	
	//createEigenschaft
		public Eigenschaft createEigenschaft(
		/*int idEigenschaft,*/ 
		String arbeitsgebiet,
		String ausbildung,
		float berufserfahrungsJahre,
		String sprachkenntnisse,
		String employmentStatus,
		String abschluss
		) throws IllegalArgumentException {
			Eigenschaft eig = new Eigenschaft();
			
			/*eig.setIdEigenschaft(idEigenschaft);		Ist diese Methode richtig?*/
			eig.setArbeitsgebiet(arbeitsgebiet);
			eig.setAusbildung(ausbildung);
			eig.setBerufserfahrungsJahre(berufserfahrungsJahre);
			eig.setSprachkenntnisse(sprachkenntnisse);
			eig.setEmploymentStatus(employmentStatus);
			eig.setAbschluss(abschluss);
			
			return this.eigMapper.insert(eig);
		}
		
		//getEigenschaftById
		public Eigenschaft getEigenschaftById(int idEigenschaft) 
				throws IllegalArgumentException{
			return this.eigMapper.findByKey(idEigenschaft);
		}
		
		public Vector <Eigenschaft> getAll()
				throws IllegalArgumentException{
			return this.eigMapper.findAll();
		}
	
}