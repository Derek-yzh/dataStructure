package org.example.activeMq._02;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: Derek
 * @DateTime: 2020/12/20 14:55
 * @Description: TODO
 */
public class SenderTopic {

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "tcp://localhost:61616"
        );
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //临时节点 生命周期 隔离 connection : session.createTemporaryTopic("user");
        Destination topic = session.createTopic("user");

        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for (int i = 0; i < 10; i++) {
            TextMessage textMessage = session.createTextMessage("Hi！"+i);
            producer.send(textMessage);
        }

        connection.close();
        System.out.println("System exit ... ");
    }


}
