package sg.edu.nus.iss.day_25_workshop_publisher.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.day_25_workshop_publisher.model.Order;
import sg.edu.nus.iss.day_25_workshop_publisher.repository.OrderPubRepository;
import sg.edu.nus.iss.day_25_workshop_publisher.util.Util;

@Service
public class OrderPubService {
    
    // need a rest template to send the order data to the subscriber ?
    // i dont think so.. the subscriber app will have the message poller that will
    // listen to the right 'list'
    // this method it seems like you need to create separate subscriber apps for every registered name

    private final OrderPubRepository orderPubRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderPubService.class);

    public OrderPubService(OrderPubRepository orderPubRepository) {
        this.orderPubRepository = orderPubRepository;
    }

    public Boolean addNameToRegistrationSet(String name) {
       return orderPubRepository.addNameToRegistrationSet(name);
    }

    public List<String> getAllRegisteredAppNames() {
        return orderPubRepository.getAllRegisteredAppNames();
    }

    public void addOrderToApp(Order order) {
        JsonObject orderJson = Util.toJsonObject(order);
        logger.info("order json: {}", orderJson);
        orderPubRepository.addOrderToApp(order.getCustomerName(), orderJson.toString());
    }

   
   

    
}
