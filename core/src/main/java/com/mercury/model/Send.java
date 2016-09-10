package com.mercury.model;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private final static String SERVER = "localhost";
    private final static String USERNAME = "guest";
    private final static String PASSWORD = "guest";
    private final static Integer PORT = -1;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    private ConnectionFactory getConnectionFactory() {
        if(this.connectionFactory != null) {
            return this.connectionFactory;
        }

        this.connectionFactory = new ConnectionFactory();
        this.setConnectionSecurityInformation(this.connectionFactory);

        return this.connectionFactory;
    }

    private ConnectionFactory setConnectionSecurityInformation(ConnectionFactory factory) {
        factory.setHost(SERVER);
        factory.setUsername(USERNAME);
        factory.setPort(PORT);
        factory.setPassword(PASSWORD);

        return factory;
    }

    private Connection getConnection() throws IOException, TimeoutException {
        if(this.connection != null) {
            return this.connection;
        }

        this.connection = this.getConnectionFactory().newConnection();

        return this.connection;
    }

    private Channel getChannel() throws IOException, TimeoutException {
        if(this.channel !=  null) {
            return this.channel;
        }

        this.channel = this.getConnection().createChannel();

        return this.channel;
    }

    private void queueDeclare(String queueName, Channel channel) throws IOException, TimeoutException {
        channel.queueDeclare(queueName, false, false, false, null);
    }

    public void sendMessage(String msg, String queueName) throws IOException, TimeoutException {
        Channel channel = this.getChannel();
        this.queueDeclare(queueName, channel);

        channel.basicPublish("", queueName, null, msg.getBytes());

        channel.close();
        this.getConnection().close();
    }

}