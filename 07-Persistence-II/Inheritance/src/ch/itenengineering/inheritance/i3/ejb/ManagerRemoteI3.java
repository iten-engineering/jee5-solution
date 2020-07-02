package ch.itenengineering.inheritance.i3.ejb;

import java.util.List;

import javax.ejb.Remote;

import ch.itenengineering.inheritance.i3.domain.Person;

@Remote
public interface ManagerRemoteI3 {

	public Person add(Person person);

	public void deleteAll();

	public List findAll();

} // end of class
