package sg.edu.nus.iss.day_25_workshop_publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import sg.edu.nus.iss.day_25_workshop_publisher.service.OrderPubService;

@Component
public class RegistrationNameLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationNameLoader.class);

    private final OrderPubService orderPubService;

    public RegistrationNameLoader(OrderPubService orderPubService) {
        this.orderPubService = orderPubService;
    }

    @Override
    public void run(String... args) throws Exception {
        // if given an application name -> need to parse the argument
                // if not given an application name -> error?
        // add it to the the redis list
        // from the redis list, will populate the options for the customer names in the form

        // Check if any arguments are provided
        if (args.length == 0) {
            logger.error("No application name provided as a command-line argumnet.");
            throw new IllegalArgumentException("Application name is required");
        }

        // Process each argument and add it to the registration list
        for (String appName : args) {
            logger.info("Processing application name: {}", appName);

            // Validate and add to the registration list
            boolean isAdded = orderPubService.addNameToRegistrationSet(appName);

             if (isAdded) {
                logger.info("Successfully added {} to the registration list.", appName);
            } else {
                logger.warn("Failed to add {} to the registration list. Invalid or duplicate entry.", appName);
            }
        }

        logger.info("Completed processing all command-line arguments.");

    }  

    
}
