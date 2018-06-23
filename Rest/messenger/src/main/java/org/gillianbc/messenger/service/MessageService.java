package org.gillianbc.messenger.service;

import java.util.ArrayList;
import java.util.List;

import org.gillianbc.messenger.model.Message;

public class MessageService {

	public List<Message> getAllMessages() {
		Message m1 = new Message(1L, "Hello 1", "Gillian");
		Message m2 = new Message(2L, "Hello 2", "Gillian");
		Message m3 = new Message(3L, "Hello 3", "Gillian");
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		list.add(m3);
		return list;

	}

}
