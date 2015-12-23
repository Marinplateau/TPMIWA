package controllers.utils.sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import controllers.utils.Service;
import controllers.utils.ServiceName;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class AsyncMessageSender {

    protected Channel channel;
    protected Connection connection;
    protected String queueName;

    public AsyncMessageSender(String queueName) throws IOException, TimeoutException {
        this.queueName = queueName;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("sigl");
        factory.setPassword("sigl2016");
        factory.setHost(Service.getInstances().getServiceURL(ServiceName.RABBITMQ));
        this.connection = factory.newConnection();
        this.channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);
    }

    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}
