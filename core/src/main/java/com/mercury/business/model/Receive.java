package com.mercury.business.model;

import com.mercury.business.enums.RabbitInfoToConfiguration;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.lang.InterruptedException;
import java.util.concurrent.TimeoutException;

public class Receive {

    private RabbitInfoToConfiguration rabbitConf;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    public static void main(String job) throws IOException, InterruptedException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(8000);
        factory.setUsername("job");
        factory.setPassword("job");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(job, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };

        channel.basicConsume(job, true, consumer);

    }

}
