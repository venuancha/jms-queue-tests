package step_definitions;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.jms.Queue;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.activemq.command.ActiveMQQueue;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import spring.jms.JmsMessageListener;
import spring.jms.JmsMessageSender;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class JMSQueueSteps {

	static ApplicationContext ctx;
	static String receivedMsg;

	@Given("^I Configure Spring JMS Template$")
	public void configureSpring() throws IOException {
		// initialise the context
		ctx = new ClassPathXmlApplicationContext("app-context.xml");
	}

	@When("^Sender sends a xml message to the queue$")
	public void sendXMLMessage() throws IOException {
		// get bean from context
		JmsMessageSender jmsMessageSender = (JmsMessageSender) ctx
				.getBean("jmsMessageSender");

		// send to default destination
		// jmsMessageSender.send("hello Venu1");

		// send to a code specified destination
		Queue queue = new ActiveMQQueue("test");
		String content = readXML();
		System.out.println("Sending the xml message:" + content);
		jmsMessageSender.send(queue, content);

		// close spring application context
		((ClassPathXmlApplicationContext) ctx).close();
	}

	@Then("^the Receiver should receive the xml message$")
	public void receiveXMLMessage() throws Exception {

		// call Jmslistener to get the received message
		receivedMsg = JmsMessageListener.returnReceivedMsg();
		Assert.assertTrue(receivedMsg != null);
	}

	@And("^The received message should match the sent message$")
	public void verifyXML() throws FactoryConfigurationError,
			ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document source = builder.parse(new InputSource(new StringReader(
				receivedMsg)));
		Document dest = builder.parse(new InputSource(new StringReader(
				readXML())));

		Diff df = new Diff(source, dest);

		df.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());

		DetailedDiff detailedDiff = new DetailedDiff(df);
		System.out.println("Received Msg is..." + receivedMsg);
		System.out.println("Detailed differences: "
				+ detailedDiff.getAllDifferences().toString());

		Assert.assertTrue("Differences are more", detailedDiff
				.getAllDifferences().size() == 0);
	}

	private String readXML() throws IOException {
		// read the dpp xml from test data
		Path p = Paths.get(".", "/src/test/resources/testdata/DPP.xml");
		String content = new String(Files.readAllBytes(p));
		return content;
	}

}
