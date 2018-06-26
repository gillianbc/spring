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
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
//JSON: header value Accept = application/json   XML:  header value Accept = text/xml
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessagesJSON(@BeanParam MessageFilterBean filterBean) {
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
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getMessagesXML(@BeanParam MessageFilterBean filterBean) {
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
	public Message getMessage(@PathParam("messageId") long someId,
			@Context UriInfo uriInfo) {
		Message message = messageService.getMessage(someId);
		//Now use the context of this resource class and method to create appropriate links
		//Check if links is empty - we don't want to keep adding same links on every GET
		if (message != null && message.getLinks().isEmpty()) {
			getUriForSelf(uriInfo, message);
			getUriForAuthorProfile(uriInfo, message);
			getUriForComments(uriInfo, message);
		}
		return message;
	}

	private void getUriForComments(UriInfo uriInfo, Message message) {
		if (message.getComments() == null) 
			return;
		String uriStr = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.resolveTemplate("messageId", message.getId())
				.build().toString();
		message.addLink(uriStr, "comments");
	}

	private void getUriForAuthorProfile(UriInfo uriInfo, Message message) {
		if (message.getAuthor() == null) 
			return;
		String uriStr = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build().toString();
		message.addLink(uriStr, "author");
	}

	private void getUriForSelf(UriInfo uriInfo, Message message) {
		String uriStr = uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)
		.path(Long.toString(message.getId()))
		.build().toString();
		message.addLink(uriStr, "self");
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
		//This is how we append to the existing path
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
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		//pass control to the subresource
		return new CommentResource();
	}

}
