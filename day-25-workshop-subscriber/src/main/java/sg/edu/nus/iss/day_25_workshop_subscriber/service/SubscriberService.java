package sg.edu.nus.iss.day_25_workshop_subscriber.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.day_25_workshop_subscriber.model.Order;
import sg.edu.nus.iss.day_25_workshop_subscriber.repository.SubscriberRepository;
import sg.edu.nus.iss.day_25_workshop_subscriber.util.Util;

@Service
public class SubscriberService {

    private final SubscriberRepository subscriberRepository;
    
    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Transactional
    public boolean addOrderToDB(String data) {
        Order orderData = Util.toOrder(data);
        if (!subscriberRepository.saveOrder(orderData)) {
            // throw an exception
            return false;
        }

        return true;
    }
    
}
