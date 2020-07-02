package ch.itenengineering.inheritance.i1.ejb;

import java.util.List;

import javax.ejb.Remote;

import ch.itenengineering.inheritance.i1.domain.Person;

@Remote
public interface ManagerRemoteI1 {

	public Person add(Person person);

	public void deleteAll();

	public List findAll();

} // end of class
