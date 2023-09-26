package com.cartwheel.galaxy.entity;

import javax.persistence.*;

@Entity
@Table(name = "User_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String userName;
	private String password;
	private String UserRole;
	private boolean isActive;

	public User(int id, String userName, String password, String userRole, boolean isActive) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		UserRole = userRole;
		this.isActive = isActive;
	}

	public User() {
		super();

	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", UserRole=" + UserRole + "]";
	}

}
