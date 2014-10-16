package com.rmsc.codeweek.data;

import java.io.Serializable;

/**
 * A class to represent an Event
 * @author ruicouto
 *
 */
public class Evento implements Serializable {
	
	/**
	 * Static variable to hold new ids
	 */
	private static int ID;
	
	/**
	 * Initialize the id variable
	 */
	static {
		ID=0;
	}
	
	/**
	 * Get a new id, which is incremented automatically.
	 * @return
	 */
	public static int getNewId() {
		return ID++;
	}
	
	/**
	 * THe event id
	 */
	private int id;
	/**
	 * The event name
	 */
	private String name;
	/**
	 * The event description
	 */
	private String description;
	
	/**
	 * Default constructor
	 * @param name
	 * @param description
	 */
	public Evento(String name, String description) {
		this.id = getNewId();
		this.name = name;
		this.description = description;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * toString, used in the listview
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Events are compared by their id
	 */
	@Override
	public boolean equals(Object o) {
		return ((Evento)o).getId() == id;
	}
	
}
