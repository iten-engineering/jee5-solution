package ch.itenengineering.relations.r2.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entität Firma
 * <ul>
 * <li>Die id ist der Primärschlüssel. Dieser wird nicht automatisch vergeben,
 * sondern von der Applikation zugeteilt.</li>
 * <li>Eine Firma hat genau eine Adresse. Dies wird mit der Option
 * optional=false auf der OneToOne Annotation angegeben.</li>
 * <li>Mit cascade=ALL wird definiert, dass alle Operationen auf die Firma auch
 * auf die dazugehörige Adresse ausgeführt werden.</li>
 * <li>Mit der Annotation JoinColumn wird die Tabellenspalte
 * R1_COMPANY.ADDRESS_ID als Foreign Key auf die Tabellenspalte R1_ADDRESS.ID
 * definiert.</li>
 * <li>Die Firma wird weiterhin als Besitzer bezeichnet (da sie nicht das
 * mappedBy Attribut besitzt).</li>
 * </ul>
 */
@Entity
@Table(name = "R2_COMPANY")
public class R2Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private String description;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "ADDRESS_ID")
	private R2Address address;

	public R2Company() {
	}

	public R2Company(int id, String name, String description, R2Address address) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setCompanyId(int id) {
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

	public R2Address getAddress() {
		return address;
	}

	public void setAddress(R2Address address) {
		this.address = address;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();

		buf.append(toStringCompanyFieldsOnly());

		buf.append("\n  ");
		buf.append(address == null ? "Es ist keine Firma vorhanden" : address
				.toStringAddressFieldsOnly());

		return buf.toString();
	}

	public String toStringCompanyFieldsOnly() {
		StringBuilder buf = new StringBuilder();

		buf.append(id);

		buf.append(", ");
		buf.append(name);

		buf.append(", ");
		buf.append(description);

		return buf.toString();
	}

} // end of class
