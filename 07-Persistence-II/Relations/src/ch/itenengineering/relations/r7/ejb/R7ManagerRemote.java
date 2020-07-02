package ch.itenengineering.relations.r7.ejb;

import javax.ejb.Remote;

@Remote
public interface R7ManagerRemote {

	public void clear();

	public void book(int studentId, int courseId);

	public void cancel(int studentId, int courseId);

	public Object persist(Object entity);

	public Object merge(Object entity);

	@SuppressWarnings("unchecked")
	public Object find(Class clazz, Object primaryKey);

	@SuppressWarnings("unchecked")
	public void remove(Class clazz, Object primaryKey);

} // end
