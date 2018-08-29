package rabbit.producer.producer.rest;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@PropertySource("file:${spring.config.location}")
public class RestHandler {

    AmqpTemplate ampqtemplate;
    @Value("${rabbit_queue_name}")
    private String rabbit_queue_name;

    @Autowired
    public void setAmqpTemplate(AmqpTemplate ampqtemplate) {
        this.ampqtemplate = ampqtemplate;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String restBundleReceive() {

        String response =  (String)ampqtemplate.convertSendAndReceive(rabbit_queue_name, "helloMessage");
        return "hello " + response;
    }


}
