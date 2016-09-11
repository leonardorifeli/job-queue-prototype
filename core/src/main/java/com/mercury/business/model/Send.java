package com.mercury.business.model;

import com.mercury.business.enums.RabbitInfoToConfiguration;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private RabbitInfoToConfiguration rabbitConf;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    private RabbitInfoToConfiguration getRabbitConf() {
        return this.rabbitConf;
    }

    private ConnectionFactory getConnectionFactory() {
        if(this.connectionFactory != null) {
            return this.connectionFactory;
        }

        this.connectionFactory = new ConnectionFactory();
        this.setConnectionSecurityInformation(this.connectionFactory);

        return this.connectionFactory;
    }

    private ConnectionFactory setConnectionSecurityInformation(ConnectionFactory factory) {
        factory.setHost(this.getRabbitConf().getServer());
        factory.setUsername(this.getRabbitConf().getUsername());
        factory.setPort(this.getRabbitConf().getPort());
        factory.setPassword(this.getRabbitConf().getPassword());

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