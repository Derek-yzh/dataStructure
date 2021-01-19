package org.example.activeMq._02;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: Derek
 * @DateTime: 2020/12/20 14:55
 * @Description: TODO
 */
public class ReceiverTopic {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "tcp://localhost:61616"
        );
        Connection connection = connectionFactory.createConnection();
        connection.start(); //TODO
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination topic = session.createTopic("user");

        MessageConsumer consumer = session.createConsumer(topic);
        while (true) {
            TextMessage message = (TextMessage)consumer.receive(); //阻塞
            System.out.println("message:"+message.getText());
        }

        //connection.close();
    }

}
