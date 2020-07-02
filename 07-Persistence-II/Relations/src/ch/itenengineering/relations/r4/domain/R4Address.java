package ch.itenengineering.relations.r4.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entität Adresse
 * <ul>
 * <li>Jede Adresse gehört zwingend zu einer Firma. Dies wird mit der Option
 * optional=false der ManyToOne Annotation definert. Auf der JoinColumn
 * Annotation wird entsprechend die Option nullable=false gesetzt.</li>
 * <li>Mit der JoinColumn Annotation wird zudem der Spaltenname für den Foreign
 * Key auf die Firmen Tabelle definiert.</li>
 * <li>Die Addressen sind Besitzer der Beziehung.</li>
 * </ul>
 */
@Entity
@Table(name = "R4_ADDRESS")
public class R4Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String street;

	private String streetNo;

	private String zipCode;

	private String city;

	private String country;

	@ManyToOne(optional = false)
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	private R4Company company;

	public R4Address() {
	}

	public R4Address(int id, String street, String streetNo, String zipCode,
			String city, String country, R4Company company) {
		this.id = id;
		this.street = street;
		this.streetNo = streetNo;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.company = company;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public R4Company getCompany() {
		return company;
	}

	public void setCompany(R4Company company) {
		this.company = company;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();

		buf.append(toStringAddressFieldsOnly());

		buf.append("\n  ");
		buf.append(company == null ? "Es ist keine Firma vorhanden" : company
				.toString());

		return buf.toString();
	}

	public String toStringAddressFieldsOnly() {
		StringBuilder buf = new StringBuilder();

		buf.append(id);

		buf.append(", ");
		buf.append(street);

		buf.append(" ");
		buf.append(streetNo);

		buf.append(", ");
		buf.append(zipCode);

		buf.append(" ");
		buf.append(city);

		buf.append(", ");
		buf.append(country);

		return buf.toString();
	}

} // end of class

