package ch.itenengineering.relations.r7.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "R7_COURSE")
public class R7Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "courseList")
	private List<R7Student> studentList = new ArrayList<R7Student>();

	public R7Course() {
	}

	public R7Course(int id, String name) {
		this.id = id;
		this.name = name;
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

	public List<R7Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<R7Student> studentList) {
		this.studentList = studentList;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();

		buf.append(toStringCourseFieldsOnly());

		buf.append("\n Student List:");
		if (studentList != null) {
			for (Iterator<R7Student> iterator = studentList.iterator(); iterator
					.hasNext();) {
				R7Student student = iterator.next();

				buf.append("\n ");
				buf.append(student.toStringStudentFieldsOnly());
			}
		} else {
			buf.append("\n Es sind keine Daten vorhanden.");
		}

		return buf.toString();
	}

	public String toStringCourseFieldsOnly() {
		StringBuilder buf = new StringBuilder();

		buf.append(id);

		buf.append(", ");
		buf.append(name);

		return buf.toString();
	}

} // end of class
