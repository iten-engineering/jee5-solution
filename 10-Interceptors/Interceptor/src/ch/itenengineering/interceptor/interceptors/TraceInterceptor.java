package ch.itenengineering.interceptor.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class TraceInterceptor {

	@AroundInvoke
	public Object trace(InvocationContext invocation) throws Exception {

		long startTime = System.currentTimeMillis();

		try {

			return invocation.proceed();

		} finally {

			long endTime = System.currentTimeMillis() - startTime;

			System.out.println("invoked method "
					+ invocation.getMethod().getName() + " used " + endTime
					+ " ms");
		}
	}

} // end of class
