package org.example.activeMq._03_linux_mq_1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: Derek
 * @DateTime: 2020/12/21 14:23
 * @Description: TODO
 */
public class Sender {

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "nio://192.168.74.21:5671"
        );
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //临时节点 生命周期 隔离 connection : session.createTemporaryTopic("user");
        Destination topic = session.createQueue("user");

        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for (int i = 0; i < 10; i++) {
            TextMessage textMessage = session.createTextMessage("Hi！"+i);
            //textMessage.setJMSCorrelationID(""+i); //力度更细
            producer.send(textMessage);
        }

        connection.close();
        System.out.println("System exit ... ");
    }


}
