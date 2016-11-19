$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("JMSQueue.feature");
formatter.feature({
  "line": 1,
  "name": "This is a sample feature for JMS Queue scenarios",
  "description": "",
  "id": "this-is-a-sample-feature-for-jms-queue-scenarios",
  "keyword": "Feature"
});
formatter.scenario({
  "comments": [
    {
      "line": 3,
      "value": "# Assumptions:"
    },
    {
      "line": 4,
      "value": "#This scanrio runs locally. And it is a template scenario"
    },
    {
      "line": 5,
      "value": "# We assume the activemq is running locally"
    },
    {
      "line": 6,
      "value": "# And we send a sample dpp xml to the activemq and receiver receives the xml"
    },
    {
      "line": 7,
      "value": "# Then we verify the sent and received xmls match."
    }
  ],
  "line": 9,
  "name": "Receive the xml message from the JMS Queue",
  "description": "",
  "id": "this-is-a-sample-feature-for-jms-queue-scenarios;receive-the-xml-message-from-the-jms-queue",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "I Configure Spring JMS Template",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "Sender sends a xml message to the queue",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "the Receiver should receive the xml message",
  "keyword": "Then "
});
formatter.match({
  "location": "JMSQueueSteps.configureSpring()"
});
formatter.result({
  "duration": 1101517828,
  "status": "passed"
});
formatter.match({
  "location": "JMSQueueSteps.sendXMLMessage()"
});
formatter.result({
  "duration": 1089675167,
  "status": "passed"
});
formatter.match({
  "location": "JMSQueueSteps.receiveXMLMessage()"
});
formatter.result({
  "duration": 140206054,
  "status": "passed"
});
});