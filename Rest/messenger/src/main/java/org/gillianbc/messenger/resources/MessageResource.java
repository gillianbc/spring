package org.gillianbc.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.gillianbc.messenger.model.Message;
import org.gillianbc.messenger.service.MessageService;

@Path("/messages")  //class level path.  Method paths are appended to this
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		// e.g. http://localhost:8080/messenger/webapi/messages?year=2018
		if (filterBean.getYear() > 0) {
			System.out.println(filterBean.getYear());
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		// e.g. http://localhost:8080/messenger/webapi/messages?start=2&count=3
		if (filterBean.getStart() > 0 && filterBean.getCount() > 0)
			return messageService.getAllMessagesPaginated(filterBean.getStart() - 1, filterBean.getCount());
		// e.g. http://localhost:8080/messenger/webapi/messages (or anything else)
		return messageService.getAllMessages();
	}

	//e.g. http://localhost:8080/messenger/webapi/messages/1
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long someId) {

		return messageService.getMessage(someId);
	}

	//http://localhost:8080/messenger/webapi/messages/
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
		/* You can do this if you just want to send back 201 CREATED
		 * 
		 * return Response.status(Status.CREATED)
			.entity(newMessage)
			.build();*/
		
		/* This returns 201 plus the url of the new record */
		String idString = String.valueOf(newMessage.getId());
		
		URI uri = uriInfo.getAbsolutePathBuilder()
		.path(idString).build();
			return Response.created(uri)
					.entity(newMessage)
					.build();
	}

	//e.g.http://localhost:8080/messenger/webapi/messages/1
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long someId, Message message) {
		message.setId(someId);
		return messageService.updateMessage(message);
	}

	//e.g. http://localhost:8080/messenger/webapi/messages/1
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long someId) {
		messageService.deleteMessage(someId);
	}
	
	//No @GET, @POST etc - we want this to fire for all HTTP methods on this endpoint
	@Path("/{messageId}/{comments}")
	public CommentResource getCommentResource() {
		//pass control to the subresource
		return new CommentResource();
	}

}
