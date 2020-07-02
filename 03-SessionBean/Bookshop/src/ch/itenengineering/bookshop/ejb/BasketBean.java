package ch.itenengineering.bookshop.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.ejb3.annotation.RemoteBinding;

import ch.itenengineering.bookshop.domain.Item;

@Stateful
@RemoteBinding(jndiBinding = "ejb/Basket")
public class BasketBean implements BasketRemote {

	private Map<String, Item> basket = new HashMap<String, Item>();

	public void addItem(Item item) {
		basket.put(item.getIsbn(), item);
	}

	public void removeItem(String isbn) {
		basket.remove(isbn);
	}

	public Map<String, Item> getBasket() {
		return basket;
	}

	@Remove
	public void order() {
		System.out.println("order basket " + basket);
	}

	@Remove
	public void cancel() {
		System.out.println("cancel basket " + basket);
	}
}
