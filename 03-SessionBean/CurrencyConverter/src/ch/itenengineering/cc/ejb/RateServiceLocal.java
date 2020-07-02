package ch.itenengineering.cc.ejb;

import javax.ejb.Local;

import ch.itenengineering.cc.domain.CurrencyType;

@Local
public interface RateServiceLocal {

	/**
	 * get the CHF conversion rate for the given currency type
	 * 
	 * @param type
	 *            currency type
	 * @return conversion rate CHF to currency type
	 */
	public double getRateCHF(CurrencyType type);

}
