package ch.itenengineering.mex.ejb;

import java.util.List;

import javax.ejb.Remote;

import ch.itenengineering.mex.domain.CurrencyType;
import ch.itenengineering.mex.domain.Rate;

@Remote
public interface MoneyExchangeRemote {

	public double getRate(CurrencyType from, CurrencyType to);

	public void setRates(List<Rate> rates);

	public int getVIPBonus();

	public void setVIPBonus(int percent);
}
