package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Projekt;

public class AusschreibungSeite extends Showcase {

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private Projekt p1 = new Projekt();
	CellTable<Ausschreibung> ausschreibungtabelle = new CellTable<Ausschreibung>();
	
	private HorizontalPanel hpanelAusschreibung = new HorizontalPanel();
	private VerticalPanel vpanelAusschreibung = new VerticalPanel();
	
	final SingleSelectionModel<Ausschreibung> ssmalleausschreibung = new SingleSelectionModel<Ausschreibung>();
	
	Button anlegenAusschreibung = new Button("Anlegen");
	
	 public AusschreibungSeite() {
		
	}
	 // konstruktor um fremdschlüssel zu übergeben
	 // damit die ausschreibungen zum passenden projekt angezeigt werden 
	 public AusschreibungSeite(Projekt p1) {
			this.p1=p1;
		}
	 
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Ausschreibung durchsuchen</h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		ausschreibungtabelle.setWidth("100%", true);
		vpanelAusschreibung.add(ausschreibungtabelle);
		hpanelAusschreibung.add(anlegenAusschreibung);
		this.add(hpanelAusschreibung);
		this.add(vpanelAusschreibung);
		
		ausschreibungtabelle.setSelectionModel(ssmalleausschreibung);
		
		ssmalleausschreibung.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		TextColumn<Ausschreibung> ausschrBez = new TextColumn<Ausschreibung>(){

			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		
		
	};
		
		TextColumn<Ausschreibung> ausschrBeschr = new TextColumn<Ausschreibung>(){
	
			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getBeschreibung();
			}
		
		
	};
	
		TextColumn<Ausschreibung> ausschrBefrist = new TextColumn<Ausschreibung>(){
	
			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getEndDatum().toString();
			}
		
		
	};
	
		TextColumn<Ausschreibung> ausschrStatus = new TextColumn<Ausschreibung>(){
	
			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getAusschreibungsstatus().toString();
			}
		
		
	};
		
		ausschreibungtabelle.addColumn(ausschrBeschr, "Bezeichnung");
		ausschreibungtabelle.addColumn(ausschrBeschr, "Beschreibung");
		ausschreibungtabelle.addColumn(ausschrBefrist,"Bewerbungsfrist");
		ausschreibungtabelle.addColumn(ausschrStatus, "Status der Ausschreibung");
		gwtproxy.getAusschreibungByProjekt(p1, new getAusschreibungAusDB());
		anlegenAusschreibung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				// neue Auss anlegen
			}
		});
	}		
		private class getAusschreibungAusDB implements AsyncCallback<Vector<Ausschreibung>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("leider etwas schief gelaufen");
			}

			@Override
			public void onSuccess(Vector<Ausschreibung> result) {
				// TODO Auto-generated method stub
				ausschreibungtabelle.setRowData(0, result);
				ausschreibungtabelle.setRowCount(result.size(), true);
				
			}
		}
	}

