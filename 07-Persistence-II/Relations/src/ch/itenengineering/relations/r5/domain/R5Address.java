package ch.itenengineering.relations.r5.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entität Adresse
 * <ul>
 * <li>Die Adressen können zu einer Firma gehören müssen aber nicht. Dies wird
 * mit der Option optional=true der ManyToOne Annotation definert. Es handelt
 * sich also um eine ManyToOne 0..1 Bezeihung.</li>
 * <li>Möchte man eine ManyToOne 1..1 Bezeihung, so ist bei der OneToMany
 * Annotation die Option optional=false zu setzten und zusätzlich bei der
 * JoinColumn Annotation die Option nullable=false zu definieren.</li>
 * <li>Die Bezeihung wird mit der ManyToOne Annotation definiert.</li>
 * <li>Mit der JoinColumn Annotation wird zudem der Spaltenname für den Foreign
 * Key auf die Firmen Tabelle definiert.</li>
 * <li>Die Addressen sind Besitzer der Beziehung.</li>
 * </ul>
 */
@Entity
@Table(name = "R5_ADDRESS")
public class R5Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String street;

	private String streetNo;

	private String zipCode;

	private String city;

	private String country;

	@ManyToOne(optional = true)
	@JoinColumn(name = "COMPANY_ID", nullable = true)
	private R5Company company;

	public R5Address() {
	}

	public R5Address(String street, String streetNo, String zipCode,
			String city, String country, R5Company company) {
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

	public R5Company getCompany() {
		return company;
	}

	public void setCompany(R5Company company) {
		this.company = company;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();

		buf.append(toStringAddressFieldsOnly());

		buf.append("\n  ");
		buf.append(company == null ? "Es ist keine Firma vorhanden" : company
				.toStringCompanyFieldsOnly());

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

