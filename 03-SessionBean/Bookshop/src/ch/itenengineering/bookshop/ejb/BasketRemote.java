package ch.itenengineering.bookshop.ejb;

import java.util.Map;

import javax.ejb.Remote;

import ch.itenengineering.bookshop.domain.Item;

@Remote
public interface BasketRemote {

	public void addItem(Item item);

	public void removeItem(String isbn);
	
	public Map<String, Item> getBasket();
	
	public void order();

	public void cancel();
}
