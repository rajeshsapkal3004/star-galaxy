package com.cartwheel.galaxy.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "User_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String address;
	private String phoneNumber;
	private boolean termsAccepted;
	private String gender;
	private String UserRole;
	private boolean isActive;

	public User(int id, String userName, String password, String email, String firstName, String lastName, Date dateOfBirth, String address, String phoneNumber, boolean termsAccepted, String gender, String userRole, boolean isActive) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.termsAccepted = termsAccepted;
		this.gender = gender;
		UserRole = userRole;
		this.isActive = isActive;
	}

	public User(int id, String userName, String password, String email, String firstName, String lastName, Date dateOfBirth, String address, String phoneNumber, boolean termsAccepted, String userRole, boolean isActive) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.termsAccepted = termsAccepted;
		UserRole = userRole;
		this.isActive = isActive;
	}


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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isTermsAccepted() {
		return termsAccepted;
	}

	public void setTermsAccepted(boolean termsAccepted) {
		this.termsAccepted = termsAccepted;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", address='" + address + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", termsAccepted=" + termsAccepted +
				", gender='" + gender + '\'' +
				", UserRole='" + UserRole + '\'' +
				", isActive=" + isActive +
				'}';
	}

}
