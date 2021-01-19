package org.example.activeMq._03_linux_mq_1;

import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import java.util.Enumeration;

/**
 * @Author: Derek
 * @DateTime: 2020/12/21 14:23
 * @Description: TODO
 */
public class Receiver {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "nio://192.168.74.21:5671"
        );
        Connection connection = connectionFactory.createConnection();
        connection.start(); //TODO

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(new ActiveMQQueue("user"));

        QueueBrowser browser = session.createBrowser(new ActiveMQQueue("user"));
        Enumeration enumeration = browser.getEnumeration();
        while (enumeration.hasMoreElements()){
            TextMessage textMessage = (TextMessage) enumeration.nextElement();
            System.out.println("textMessage: "+textMessage.getText());
        }

        /*consumer.setMessageListener(new MessageListener() {
            @SneakyThrows
            @Override
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage msg = (TextMessage) message;
                    System.out.println("message:" + msg.getText());
                }
                else if (message instanceof ObjectMessage){
                    ObjectMessage msg = (ObjectMessage) message;
                    System.out.println("message:" + msg.getObject());
                }
            }
        });*/


    }
}
