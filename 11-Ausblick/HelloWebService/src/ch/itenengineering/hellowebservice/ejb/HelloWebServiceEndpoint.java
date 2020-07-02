package ch.itenengineering.hellowebservice.ejb;

import java.rmi.Remote;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWebServiceEndpoint extends Remote {

	@WebMethod
	@WebResult(name = "echoResult")
	public String echo(@WebParam(name = "message")
	String message);
}
