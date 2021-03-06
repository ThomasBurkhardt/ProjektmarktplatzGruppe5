package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

public class EigeneAusschreibungen extends Showcase {

	
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private CellTable<Ausschreibung> eigeneAusschreibungtabelle = new CellTable<Ausschreibung>();
	final SingleSelectionModel<Ausschreibung> ssmallEigeneAusschreibung = new SingleSelectionModel<Ausschreibung>();
	private Projekt p1 = new Projekt();
	private Person pers1 = new Person();
	private Marktplatz markt1 = new Marktplatz();
	private Organisationseinheit orga = new Organisationseinheit();
	private Ausschreibung ausschr1 = new Ausschreibung();
	
	
	// Buttons 
	private Button bearbeitenAusschreibung = new Button("Gewähltes Ausschreibung bearbeiten");
	private Button anzeigenAusschreibung = new Button("Gewähltes Ausschreibung anzeigen");
	private Button loeschenAusschreibung = new Button("Gewählte Ausschreibung löschen");
	
	//Panels 
	private HorizontalPanel  hpanelEigeneAusschreibung = new HorizontalPanel();
	private VerticalPanel vpanelEigeneAusschreibung = new VerticalPanel();
	
	// Konstruktoren
	
	 public EigeneAusschreibungen( Person person) {
		// TODO Auto-generated constructor stub
		 
		 this.pers1= person;
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1> Ihre eigenen Ausschreibung </h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		eigeneAusschreibungtabelle.setWidth("100%", true);
		eigeneAusschreibungtabelle.setStylePrimaryName("celltable");
		vpanelEigeneAusschreibung.add(eigeneAusschreibungtabelle);
		
			hpanelEigeneAusschreibung.add(anzeigenAusschreibung);
			hpanelEigeneAusschreibung.add(bearbeitenAusschreibung);
			hpanelEigeneAusschreibung.add(loeschenAusschreibung);
			
			this.add(hpanelEigeneAusschreibung);
			this.add(vpanelEigeneAusschreibung);
			
			eigeneAusschreibungtabelle.setSelectionModel(ssmallEigeneAusschreibung);
			
			ssmallEigeneAusschreibung.addSelectionChangeHandler(new Handler() {
				
				@Override
				public void onSelectionChange(SelectionChangeEvent event) {
					// TODO Auto-generated method stub
					ausschr1= ssmallEigeneAusschreibung.getSelectedObject();
				}
			});
			
			//ClickHandler 
			
			anzeigenAusschreibung.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					Showcase showcase = new EigenschaftAusSeite(ausschr1, p1, markt1, pers1);
					RootPanel.get("Anzeige").clear();
					RootPanel.get("Anzeige").add(showcase);
				}
			});
		
	}
}