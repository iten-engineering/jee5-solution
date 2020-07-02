package ch.itenengineering.inheritance.i2.ejb;

import java.util.List;

import javax.ejb.Remote;

import ch.itenengineering.inheritance.i2.domain.Person;

@Remote
public interface ManagerRemoteI2 {

	public Person add(Person person);

	public void deleteAll();

	public List findAll();

} // end of class
