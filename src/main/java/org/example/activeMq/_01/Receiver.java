package org.example.activeMq._01;

import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: Derek
 * @DateTime: 2020/12/19 21:40
 * @Description: activemq 接收消息
 */
public class Receiver {

    public static void main(String[] args) throws JMSException {
        //1.获取连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "tcp://localhost:61616"
        );
        connectionFactory.setTrustAllPackages(true); //设置新人序列化：消费序列化对象需要
        //2.获取一个ActiveMQ连接
        Connection connection = connectionFactory.createConnection();
        connection.start(); //TODO
        //3.获取Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.找目的地 获取Destination 消费者也会从这个目的地取消息
        Destination queue = session.createQueue("user");
        //TODO Destination queue = session.createQueue("user?consumer.exclusive=true"); //所有消息只有自己消费 (独占消费者)

        //5.获取消息
        MessageConsumer consumer = session.createConsumer(queue);

        //添加监听器
        consumer.setMessageListener(new MessageListener() {
            @SneakyThrows
            @Override
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage msg = (TextMessage) message;
                    System.out.println("message:" + msg.getText());

                    /*TODO 回应sender阻塞确认
                    Destination replyTo = message.getJMSReplyTo();
                    MessageProducer producer = session.createProducer(replyTo);
                    producer.send(session.createTextMessage("回应sender阻塞确认"));
                    */
                }
                else if (message instanceof ObjectMessage){
                    ObjectMessage msg = (ObjectMessage) message;
                    System.out.println("message:" + msg.getObject());
                }
            }
        });

        Destination dlq = session.createQueue("DLQ.user"); //死信队列
        MessageConsumer dlqConsumer = session.createConsumer(dlq);
        dlqConsumer.setMessageListener(new MessageListener() {
            @SneakyThrows
            @Override
            public void onMessage(Message message) {
                System.out.print("DLQ:----------:");
                if (message instanceof TextMessage) {
                    TextMessage msg = (TextMessage) message;
                    System.out.println("message:" + msg.getText());
                }
                else if (message instanceof ObjectMessage){
                    ObjectMessage msg = (ObjectMessage) message;
                    System.out.println("message:" + msg.getObject());
                }
            }
        });


        //6.关闭连接
        //connection.close();
    }

}
