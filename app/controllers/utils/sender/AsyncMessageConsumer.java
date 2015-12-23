package controllers.utils.sender;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import controllers.utils.pojo.AsyncMessagePojo.AsyncMessagePojo;
import controllers.utils.pojo.AsyncMessagePojo.SimpleMessagePojo;
import org.apache.commons.lang3.SerializationUtils;
import play.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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

        try {
            InputStream inputStream;
            inputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream o = new ObjectInputStream(inputStream);
            AsyncMessagePojo pojo = (AsyncMessagePojo) o.readObject();
            Logger.info("receive new async message of type", pojo.getClass());
            pojo.action();
            Logger.info("execution of message completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleConsumeOk(String s) {
    }

    @Override
    public void handleCancelOk(String s) {
    }

    @Override
    public void handleCancel(String s) throws IOException {
    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {
    }

    @Override
    public void handleRecoverOk(String s) {
    }
}
