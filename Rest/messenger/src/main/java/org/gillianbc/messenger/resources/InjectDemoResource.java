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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	/**
	 * You can use any use any combination of these params There are others too.
	 */

	// Example endpoint -
	// http://localhost:8080/messenger/webapi/injectdemo/fish/crab
	@GET
	@Path("/{level1}/{level2}")
	public String getParamsUsingContext(@Context UriInfo uriInfo,
			                            @Context HttpHeaders headers) {
		// uriInfo and httpheaders have loads of useful get methods
		MultivaluedMap<String, String> map = uriInfo.getPathParameters();
		System.out.println(map);
		System.out.println(map.get("level1"));
		
		return "Method getParamsUsingContext ran using path " + uriInfo.getAbsolutePath() + "\n Level 1 "
				+ map.get("level1") + "\n Level 2 "
						+ map.get("level2") + "Cookies " + headers.getCookies().toString();
	}

	// Example endpoint - http://localhost:8080/messenger/webapi/injectdemo/frogface
	// Postman shows the available cookies on a separate tab - you can make cookies with a Response
	@GET
	@Path("frogface")
	public String getParamsUsingAnnotations(@CookieParam("JSESSIONID") String cookieParam) {
		return "Method getParamsUsingAnnotations ran " + cookieParam;
	}

	/*
	 * //Example endpoint -
	 * http://localhost:8080/messenger/webapi/injectdemo/frogface //plus a Header of
	 * myCustomParam with a value of anything e.g. a session id
	 * 
	 * @GET
	 * 
	 * @Path("frogface") public String
	 * getParamsUsingAnnotations(@HeaderParam("myCustomParam") String headerParam) {
	 * return "Method getParamsUsingAnnotations ran " + headerParam; }
	 */

	/*
	 * // Example endpoint -
	 * http://localhost:8080/messenger/webapi/injectdemo/frogface;param=fishhead
	 * //Note it's a semicolon not a colon
	 * 
	 * @GET
	 * 
	 * @Path("frogface") public String
	 * getParamsUsingAnnotations(@MatrixParam("param") String matrixParam) { return
	 * "Method getParamsUsingAnnotations ran " + matrixParam; }
	 */

	/*
	 * // Example endpoint -
	 * http://localhost:8080/messenger/webapi/injectdemo/frogface // Make sure
	 * there's no header info in the Postman HTTP request
	 * 
	 * @GET
	 * 
	 * @Path("frogface") public String getParamsUsingAnnotations() { return
	 * "Method getParamsUsingAnnotations ran"; }
	 */

}
