package ch.itenengineering.basicmapping.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Name implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String firstname;

	protected String lastname;

	public Name() {
	}

	public Name(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String toString() {
		return (firstname + " " + lastname);
	}

} // end of class

