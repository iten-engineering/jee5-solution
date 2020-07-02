package ch.itenengineering.cc.ejb;

import java.util.EnumMap;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import ch.itenengineering.cc.domain.CurrencyType;

@Stateless
public class RateServiceBean implements RateServiceLocal {

	/**
	 * enumeration map holding the desired CHF conversion rates
	 */
	EnumMap<CurrencyType, Double> rates = new EnumMap<CurrencyType, Double>(
			CurrencyType.class);

	/**
	 * initialize conversion rates mehtod
	 * 
	 * note: the method is tagged as post construct callback and will be invoked
	 * by the container after the creation of the bean class and before of any
	 * business method call
	 */
	@PostConstruct
	public void initRates() {
		rates.put(CurrencyType.CHF, 1.0);
		rates.put(CurrencyType.EUR, 0.65);
		rates.put(CurrencyType.USD, 0.83);
	}

	/**
	 * get the CHF conversion rate for the given currency type
	 * 
	 * @param type
	 *            currency type
	 * @return conversion rate CHF to currency type
	 */
	public double getRateCHF(CurrencyType type) {

		return rates.get(type);

	}

} // end of class
