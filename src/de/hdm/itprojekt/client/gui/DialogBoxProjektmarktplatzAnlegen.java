package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.Projektmarktplatz;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Person;

public class DialogBoxProjektmarktplatzAnlegen extends DialogBox {
		
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	VerticalPanel vpanel = new VerticalPanel();
	HorizontalPanel hpanel = new HorizontalPanel();
	
	Button ok = new Button("OK");
	Button abbrechen = new Button("Abbrechen");
	
	Label marktplatzname = new Label("Projektmarktplatzbezeichnung: ");
	TextArea bezeichnung = new TextArea();
	Label geschaeftsgebiet = new Label("Geschäftsgebiet: ");
	TextArea gebiet = new TextArea();
	
	private Person person = new Person();
	
	FlexTable marktplatzdialogboxtabelle = new FlexTable();
	public DialogBoxProjektmarktplatzAnlegen(final Person person){
		this.setText("Projektmarktplatz anlegen");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.person = person;
		
		hpanel.add(ok);
		hpanel.add(abbrechen);
		
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (bezeichnung.getText().isEmpty()){
					Window.alert("Bitte geben Sie ein Projektmarktplatzname ein:");
				}
				else{
					gwtproxy.anlegenMarktplatz(gebiet.getText(), bezeichnung.getText(), new marktplatzinDB());
				}
			}
		});
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			hide();
			}
		});
		vpanel.add(marktplatzdialogboxtabelle);
		vpanel.add(hpanel);
		this.add(vpanel);
		marktplatzdialogboxtabelle.setWidget(1, 0, marktplatzname);
		marktplatzdialogboxtabelle.setWidget(1, 1, bezeichnung);
		marktplatzdialogboxtabelle.setWidget(2, 0, geschaeftsgebiet);
		marktplatzdialogboxtabelle.setWidget(2, 1, gebiet);
	}
	private class marktplatzinDB implements AsyncCallback<Marktplatz>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Erstellen des Marktplatzes");
		}

		@Override
		public void onSuccess(Marktplatz result) {
			Window.alert("Neuer Marktplatz wurde angelegt");
			hide();
			Showcase showcase = new ProjektmarktplatzSeite(person);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase);
			
		}
		
	}
}
