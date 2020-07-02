package ch.itenengineering.relations.r1.domain;

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
 * <li>Eine Firma kann 0..1 Adressen enthalten. Dies wird mit der Option
 * optional=true auf der OneToOne Annotation angegeben.</li>
 * <li>Mit cascade=ALL wird definiert, dass alle Operationen auf die Firma auch
 * auf die dazugehörige Adresse ausgeführt werden.</li>
 * <li>Mit der Annotation JoinColumn wird die Tabellenspalte
 * R1_COMPANY.ADDRESS_ID als Foreign Key auf die Tabellenspalte R1_ADDRESS.ID
 * definiert.</li>
 * <li>Die Firma ist Besitzer der Beziehung.</li>
 * </ul>
 */
@Entity
@Table(name = "R1_COMPANY")
public class R1Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private String description;

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "ADDRESS_ID")
	private R1Address address;

	public R1Company() {
	}

	public R1Company(int id, String name, String description, R1Address address) {
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

	public R1Address getAddress() {
		return address;
	}

	public void setAddress(R1Address address) {
		this.address = address;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();

		buf.append(id);
		buf.append(", ");

		buf.append(name);
		buf.append(", ");

		buf.append(description);
		buf.append(", ");

		buf.append("\n  ");
		buf.append(address == null ? "Es ist keine Adresse vorhanden" : address
				.toString());

		return buf.toString();
	}

} // end of class
