package models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import models.messages.Message;

import org.codehaus.jackson.JsonNode;

import play.Logger;
import play.Logger.ALogger;
import play.libs.Json;
import play.mvc.WebSocket;

public class WAMPClient {
	static ALogger log = Logger.of(WAMPClient.class);
	final Set<String> topics;
	final Map<String, String> prefixes;
	final String ID;
	final WebSocket.Out<JsonNode> out;

	public WAMPClient(WebSocket.Out<JsonNode> out) {
		this.out = out;
		topics = new HashSet<String>();
		prefixes = new HashMap<String, String>();
		ID = UUID.randomUUID().toString();
	}

	public void send(Message message) {
		JsonNode node = Json.toJson(message.toList());
		out.write(node);
	}

	public void setPrefix(String prefix, String URI) {
		prefixes.put(prefix, URI);
	}

	private String convertCURIEToURI(String curie) {
		// TODO
		// if (prefixes.containsKey(prefix)){
		// return prefixes.get(prefix);
		// }
		return curie;
	}

	public void subscribe(String topic) {
		topics.add(convertCURIEToURI(topic));
	}

	public boolean isSubscribed(String topic) {
		return topics.contains(convertCURIEToURI(topic));
	}

	public void unsubscribe(String topic) {
		topics.remove(convertCURIEToURI(topic));
	}

	public String getID() {
		return this.ID;
	}
	
	public void kill() {
		out.close();
	}
}
