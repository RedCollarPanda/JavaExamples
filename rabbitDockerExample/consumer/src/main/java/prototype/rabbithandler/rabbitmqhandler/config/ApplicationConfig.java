package prototype.rabbithandler.rabbitmqhandler.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableRabbit
@PropertySource("file:${spring.config.location}")
public class ApplicationConfig {

    @Value("${rabbit_queue_host}")
    private String rabbit_queue_host;

    @Value("${rabbit_queue_name}")
    private String rabbit_queue_name;

    @Value("${spring.rabbitmq.username}")
    private String spring_rabbitmq_username;

    @Value("${spring.rabbitmq.password}")
    private String spring_rabbitmq_password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(rabbit_queue_host);
        connectionFactory.setRequestedHeartBeat(100);
        connectionFactory.setUsername(spring_rabbitmq_username);
        connectionFactory.setPassword(spring_rabbitmq_password);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    @Bean
    public Queue createMessageInQueue() {
        return new Queue(rabbit_queue_name);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMaxConcurrentConsumers(5);
        return factory;
    }

}
