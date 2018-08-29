package prototype.rabbithandler.rabbitmqhandler.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",
            queues = "#{'${rabbit_queue_name}' != null?'${rabbit_queue_name}':'rabbit_handler_example2'}")
    public String consumerInsertMessage(Message message) {

        System.out.println(message.toString());
        System.out.println(new String(message.getBody()));

        return "stub";
    }

}


