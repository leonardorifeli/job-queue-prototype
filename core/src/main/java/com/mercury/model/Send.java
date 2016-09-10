package com.mercury.model;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private final String SERVER = "localhost";
    private final String USERNAME = "job";
    private final String PASSWORD = "job";
    private final Integer PORT = -1;
    private final ConnectionFactory connectionFactory;
    private final Connection connection;
    private final Channel channel;

    private ConnectionFactory getConnectionFactory() {
        if(this.connectionFactory) {
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

    private Connection getConnection() {
        if(this.connection) {
            return this.connection;
        }   

        Connection this.connection = this.getConnectionFactory().newConnection();

        return this.connection;
    }

    private Channel getChannel() {
        if(this.channel) {
            return this.channel;
        }

        Channel this.channel = this.getConnection().createChannel();

        return this.channel;
    }

    private void queueDeclare(String queueName, Channel channel) {
        channel.queueDeclare(queueName, false, false, false, null);

        return channel;
    }

    public void sendMessage(String msg, String queueName) throws IOException, TimeoutException {
        Channel channel = this.getChannel();
        this.queueDeclare(queueName, channel);

        channel.basicPublish("", queueName, null, msg.getBytes());

        channel.close();
        this.getConnection().close();
    }

}