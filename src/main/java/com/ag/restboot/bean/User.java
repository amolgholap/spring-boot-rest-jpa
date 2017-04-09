package com.ag.restboot.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "\"user\"")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Transient
	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
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

	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return this.id +"," + getName();
	}
}