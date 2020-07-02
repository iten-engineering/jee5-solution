package ch.itenengineering.relations.r2.ejb;

import javax.ejb.Remote;

@Remote
public interface R2ManagerRemote {

	public Object persist(Object entity);

	public Object merge(Object entity);

	@SuppressWarnings("unchecked")
	public Object find(Class clazz, Object primaryKey);

	@SuppressWarnings("unchecked")
	public void remove(Class clazz, Object primaryKey);

} // end
