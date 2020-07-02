package ch.itenengineering.relations.r5.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entität Firma
 * <ul>
 * <li>Eine Firma kann 0..n Adressen enthalten.</li>
 * <li>Mit fetch=EAGER werden beim Lesen einer Firma auch alle dazugehörigen
 * Adressen selektiert.</li>
 * <li>Mit dem mappedBy Attribut wird angegeben welches Feld in der Address
 * Klasse für die Beziehung verantwortlich ist.</li>
 * <li>Dadurch ist auch klar, dass die Adressen Besitzer der Verbindung sind.</li>
 * </ul>
 */
@Entity
@Table(name = "R5_COMPANY")
public class R5Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company")
	private List<R5Address> addressList = new ArrayList<R5Address>();

	public R5Company() {
	}

	public R5Company(int id, String name, String description,
			List<R5Address> addressList) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.addressList = addressList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public List<R5Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<R5Address> addressList) {
		this.addressList = addressList;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();

		buf.append(toStringCompanyFieldsOnly());

		buf.append("\n  Address List:");
		if (addressList != null) {
			for (Iterator<R5Address> iterator = addressList.iterator(); iterator
					.hasNext();) {
				R5Address address = iterator.next();

				buf.append("\n    ");
				buf.append(address.toStringAddressFieldsOnly());
			}
		} else {
			buf.append("\n    Es sind keine Daten vorhanden.");
		}

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
