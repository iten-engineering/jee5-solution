package ch.itenengineering.relations.r6.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "R6_COURSE")
public class R6Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	public R6Course() {
	}

	public R6Course(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();

		buf.append(id);

		buf.append(", ");
		buf.append(name);

		return buf.toString();
	}

} // end of class
