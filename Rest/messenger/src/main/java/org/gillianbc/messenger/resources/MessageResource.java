package org.gillianbc.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.gillianbc.messenger.model.Message;
import org.gillianbc.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages(@QueryParam("year") int year,
			@QueryParam("start") int start,
			@QueryParam("count") int count) {
		//e.g. http://localhost:8080/messenger/webapi/messages?year=2018
		if (year > 0)
			return messageService.getAllMessagesForYear(year);
		//e.g. http://localhost:8080/messenger/webapi/messages?start=2&count=3
		if (start > 0 && count > 0) 
			return messageService.getAllMessagesPaginated(start-1, count);
		//e.g. http://localhost:8080/messenger/webapi/messages  (or anything else)
		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long someId) {

		return messageService.getMessage(someId);
	}

	@POST
	public Message addMessage(Message message) {
		messageService.addMessage(message);
		return messageService.getLastMessage();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long someId, Message message) {
		message.setId(someId);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long someId) {
		messageService.deleteMessage(someId);
	}

}
