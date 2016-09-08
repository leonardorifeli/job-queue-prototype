package com.mercury.model;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;
import java.lang.InterruptedException;
import java.util.concurrent.TimeoutException;

public class Receive {

    private final static String QUEUE_NAME = "hermes";

    public static void main(String[] argv)
            throws IOException,
            InterruptedException,
            TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    }

}
