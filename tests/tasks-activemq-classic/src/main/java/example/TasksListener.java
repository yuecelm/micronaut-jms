package example;

import io.micronaut.jms.annotations.JMSListener;
import io.micronaut.jms.annotations.Topic;
import io.micronaut.messaging.annotation.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static io.micronaut.jms.activemq.classic.configuration.ActiveMqClassicConfiguration.CONNECTION_FACTORY_BEAN_NAME;

@JMSListener(CONNECTION_FACTORY_BEAN_NAME)
public class TasksListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TasksListener.class);

    public static final AtomicInteger TASKS_PROCESSED = new AtomicInteger();

    @Topic(value = TaskConstants.FIFO_QUEUE)
    public void receive(@MessageBody byte[] message) {
        LOGGER.info("Received byte[]: " + Arrays.toString(message));
        TASKS_PROCESSED.incrementAndGet();
    }

}