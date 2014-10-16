package com.rmsc.codeweek.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class to handle the several events
 * @author ruicouto
 *
 */
public class Eventos implements Serializable {
	
	/**
	 * The list of events
	 */
	private ArrayList<Evento> eventos;
	
	/**
	 * Initialize the internal data
	 */
	public Eventos() {
		eventos = new ArrayList<Evento>();
	}
	
	/**
	 * Get the events (no clone)
	 * @return
	 */
	public ArrayList<Evento> getEventos() {
		return eventos;
	}
	
}
