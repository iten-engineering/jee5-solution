package ch.itenengineering.cc.ejb;

import javax.ejb.Remote;

import ch.itenengineering.cc.domain.CurrencyType;

@Remote
public interface CurrencyConverterRemote {

	/**
	 * convert the given CHF value to the desired currency
	 * 
	 * @param type
	 *            currency type
	 * @param value
	 *            CHF to convert
	 * @return converted value
	 */
	public double convertCHF(double value, CurrencyType type);

}
