package sg.edu.nus.iss.day_25_workshop_publisher.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day_25_workshop_publisher.constant.Constant;

@Repository
public class OrderPubRepository {

    @Qualifier(Constant.template01)
    private final RedisTemplate<String, String> template;

    public OrderPubRepository(@Qualifier(Constant.template01) RedisTemplate<String, String> template) {
        this.template = template;
    }
    
    public Boolean addNameToRegistrationSet(String appName) {
        Long result = template.opsForSet().add(Constant.REDIS_KEY_REGISTRATIONS, appName);
        return result != null && result > 0; // returns true if the name was added, false if it already exists
    }

    // check if the registration name already exists
    // check if app name exists in the registrations set
    public Boolean isAppNameRegistered(String appName) {
        return template.opsForSet().isMember(Constant.REDIS_KEY_REGISTRATIONS, appName);
    }

    // get all registered app names
    public List<String> getAllRegisteredAppNames() {
        return template.opsForSet().members(Constant.REDIS_KEY_REGISTRATIONS).stream().toList();
    }

    // add an order to the list for a specific app name
    public void addOrderToApp(String appName, String orderJson) {
        template.opsForList().leftPush(appName, orderJson);
    }


    
    
}
