package ch.itenengineering.relations.r2.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entität Adresse
 * <ul>
 * <li>Die id ist die Primärschlüssel und wird manuell vergeben.</li>
 * <li>Eine Adresse kann zu einer Firma gehören (optional=true).</li>
 * <li>Sie kann aber auch bestehen, wenn es keine Firma dazu gibt.</li>
 * <li>Die Bezeihung wird mit der Option mappedBy der OneToOne Annotation
 * definiert.</li>
 * <li>mappedBy bezieht sich auf das Feld address in der Klasse Company;
 * indirekt wird damit auch gesagt, dass sich der FK auf der Firmentabelle
 * befindet.</li>
 * <li>Mit cascade=ALL wird definiert, dass alle Operationen auf die Addresse
 * auch auf die dazugehörige Firma ausgeführt werden.</li>
 * </ul>
 */
@Entity
@Table(name = "R2_ADDRESS")
public class R2Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String street;

	private String streetNo;

	private String zipCode;

	private String city;

	private String country;

	@OneToOne(mappedBy = "address", cascade = CascadeType.ALL, optional = true)
	private R2Company company;

	public R2Address() {
	}

	public R2Address(int id, String street, String streetNo, String zipCode,
			String city, String country) {
		this.id = id;
		this.street = street;
		this.streetNo = streetNo;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public R2Company getCompany() {
		return company;
	}

	public void setCompany(R2Company company) {
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

