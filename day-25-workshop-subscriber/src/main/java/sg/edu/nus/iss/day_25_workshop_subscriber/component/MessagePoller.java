package sg.edu.nus.iss.day_25_workshop_subscriber.component;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import sg.edu.nus.iss.day_25_workshop_subscriber.constant.Constant;
import sg.edu.nus.iss.day_25_workshop_subscriber.service.SubscriberService;

@Component
public class MessagePoller {

    @Qualifier(Constant.template01)
    private final RedisTemplate<String, String> template;

    private final SubscriberService subscriberService;

    private static final Logger logger = LoggerFactory.getLogger(MessagePoller.class);

    public MessagePoller( @Qualifier(Constant.template01) RedisTemplate<String, String> template, SubscriberService subscriberService) {
        this.template = template;
        this.subscriberService = subscriberService;
    }

    @Async
    public void start() {
        Runnable poller = () -> {
            ListOperations<String, String> acmeList = template.opsForList();
            while (true) {
                Optional<String> opt = Optional.ofNullable(acmeList.rightPop("acme", Duration.ofSeconds(5)));
                if(opt.isPresent()) {
                    String acmeData = opt.get();
                    logger.info("fetched order data for acme: {}", acmeData);
                    subscriberService.addOrderToDB(acmeData);
                }
            }
        };
        Executors.newSingleThreadExecutor().execute(poller);
    }

    


    
}
