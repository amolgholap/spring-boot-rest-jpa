package com.ag.restboot.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "\"user\"")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;


	public User() {
		super();
	}
	public User(String name) {
		super();
		//this.id = id;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}


	@Override
	public String toString() {
		return this.id +"," + getName();
	}
}