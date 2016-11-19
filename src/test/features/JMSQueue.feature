Feature: This is a sample feature for JMS Queue scenarios

# Assumptions:
#This scanrio runs locally. And it is a template scenario
# We assume the activemq is running locally
# And we send a sample dpp xml to the activemq and receiver receives the xml
# Then we verify the sent and received xmls match.

Scenario: Receive the xml message from the JMS Queue
Given I Configure Spring JMS Template
When Sender sends a xml message to the queue
Then the Receiver should receive the xml message
And The received message should match the sent message