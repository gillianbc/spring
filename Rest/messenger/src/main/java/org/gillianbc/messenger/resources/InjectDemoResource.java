package org.gillianbc.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	/**
	 * You can use any use any combination of these params
	 * There are others too.
	 */
	
	
	//e.g. http://localhost:8080/messenger/webapi/injectdemo/frogface
	//Postman shows the available cookies on a separate tab - not sure how they got there
	@GET
	@Path("frogface")
	public String getParamsUsingAnnotations(@CookieParam("JSESSIONID") String cookieParam) {
		return "Method getParamsUsingAnnotations ran " + cookieParam;
	}
	
	/*
	//e.g. http://localhost:8080/messenger/webapi/injectdemo/frogface
	//plus a Header of myCustomParam with a value of anything e.g. a session id
	@GET
	@Path("frogface")
	public String getParamsUsingAnnotations(@HeaderParam("myCustomParam") String headerParam) {
		return "Method getParamsUsingAnnotations ran " + headerParam;
	}
	*/
	
	
	/*
	// e.g. http://localhost:8080/messenger/webapi/injectdemo/frogface;param=fishhead
	//Note it's a semicolon not a colon
	@GET
	@Path("frogface")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam) {
		return "Method getParamsUsingAnnotations ran " + matrixParam;
	}
	*/

	/*
	// e.g. http://localhost:8080/messenger/webapi/injectdemo/frogface
	// Make sure there's no header info in the Postman HTTP request
	@GET
	@Path("frogface")
	public String getParamsUsingAnnotations() {
		return "Method getParamsUsingAnnotations ran";
	}
	*/

}
