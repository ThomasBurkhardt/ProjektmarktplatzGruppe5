package de.hdm.itprojekt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
//Rueckgaengig
//ZweiterVersuch!
/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

	void findPersonbyKey(int key, AsyncCallback<String> callback);
	
	// mm erneut wichtig!!!!
}
