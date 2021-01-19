package org.example.activeMq._01;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.example.activeMq.common.MQ;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Derek
 * @DateTime: 2020/12/19 21:34
 * @Description: activemq 发送消息
 */
public class Sender {

    public static void main(String[] args) throws JMSException {

        //1.获取连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "tcp://localhost:61616"
        );
        //2.获取一个ActiveMQ连接
        Connection connection = connectionFactory.createConnection();
        //3.获取Session  TODO transacted : 是否需要事务 开启事务需要session.commit();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.找目的地 获取Destination 消费者也会从这个目的地取消息
        Queue queue = session.createQueue("user");

        //5.1消息创建者
        MessageProducer producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT); //TODO 设置非持久化 默认为持久化

        /*
        QueueRequestor queueRequestor = new QueueRequestor((ActiveMQSession)session, queue);
        Message response = queueRequestor.request(session.createTextMessage("request")); //TODO 同步阻塞
        */

        //5.2创建消息
        for (int i = 0; i < 10; i++) {
            TextMessage textMessage = session.createTextMessage("Hi！"+i);
            //5.3向目的地写入消息
            producer.send(textMessage);
        }

        /*ObjectMessage mq = session.createObjectMessage(new MQ("mq", 10));
        producer.setTimeToLive(1000);
        producer.send(mq);*/


        //6.关闭连接
        connection.close();

        System.out.println("System exit ... ");
    }

}
