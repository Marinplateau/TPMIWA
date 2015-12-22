package controllers.utils.sender;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import controllers.utils.pojo.AsyncMessagePojo.AsyncMessagePojo;
import org.apache.commons.lang3.SerializationUtils;
import play.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AsyncMessageConsumer extends AsyncMessageSender implements Runnable, Consumer {
    public AsyncMessageConsumer(String queueName) throws IOException, TimeoutException {
        super(queueName);
    }

    @Override
    public void run() {
        try {
            this.channel.basicConsume(this.queueName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when new message is available.
     */
    @Override
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {

        AsyncMessagePojo asyncMessagePojo = (AsyncMessagePojo) SerializationUtils.deserialize(bytes);
        Logger.info("Message is type of: {}", asyncMessagePojo.getClass());
        asyncMessagePojo.action();
    }

    @Override
    public void handleConsumeOk(String s) {}

    @Override
    public void handleCancelOk(String s) {}

    @Override
    public void handleCancel(String s) throws IOException {}

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {}

    @Override
    public void handleRecoverOk(String s) {}
}
