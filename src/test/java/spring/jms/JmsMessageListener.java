package spring.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class JmsMessageListener {

	static String receivedMsg;

	@JmsListener(destination = "test")
	@SuppressWarnings("rawtypes")
	public String processMessage( Message msg) {
		receivedMsg = msg.getPayload().toString();
		System.out
				.println("The header of the message is..." + msg.getHeaders());
		return "ACK from handleMessage";
	}

	public static String returnReceivedMsg() {
		return receivedMsg;
	}
}