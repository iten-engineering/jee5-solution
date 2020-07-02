package ch.itenengineering.cc.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.RemoteBinding;

import ch.itenengineering.cc.domain.CurrencyType;

@Stateless
@RemoteBinding(jndiBinding="ejb/CurrencyConverter")
public class CurrencyConverterBean implements CurrencyConverterRemote {

	/**
	 * local interface to the rate service
	 * 
	 * note: the reference will by injected by the container after the creation
	 * of the bean class and before of any callback and business method calls
	 */
	@EJB
	RateServiceLocal rateService;

	/**
	 * convert the given CHF value to the desired currency
	 * 
	 * @param type
	 *            currency type
	 * @param value
	 *            CHF to convert
	 * @return converted value
	 */
	public double convertCHF(double value, CurrencyType type) {

		// get rate for the given currency type
		double rate = rateService.getRateCHF(type);

		// calulate and return value
		return value * rate;
	}

} // end of class
