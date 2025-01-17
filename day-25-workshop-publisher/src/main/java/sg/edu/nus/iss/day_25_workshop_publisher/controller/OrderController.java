package sg.edu.nus.iss.day_25_workshop_publisher.controller;

// import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import sg.edu.nus.iss.day_25_workshop_publisher.model.Order;
import sg.edu.nus.iss.day_25_workshop_publisher.model.OrderDetails;
import sg.edu.nus.iss.day_25_workshop_publisher.service.OrderPubService;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderPubService orderPubService;
    
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    
    public OrderController(OrderPubService orderPubService) {
        this.orderPubService = orderPubService;
    }

    @GetMapping("")
    public String showOrderForm(Model model) {
        Order order = new Order();
        // pre-populate with one empty OrderDetails
        // OrderDetails details = new OrderDetails();
        // List<OrderDetails> lineItems = new ArrayList<>();
        // lineItems.add(details);
        order.getLineItems().add(new OrderDetails());
        List<String> registeredNames = orderPubService.getAllRegisteredAppNames();
        logger.info("Registered names in Redis under 'registrations': {}", registeredNames);

        model.addAttribute("order", order);
        model.addAttribute("registeredNames", registeredNames);

        return "orderForm";

    }

    @PostMapping("")
    public String processOrder(@Valid @ModelAttribute("order") Order entity, BindingResult results, @RequestParam("customerName") String customerName, Model model) {
        if (results.hasErrors()) {
            List<String> registeredNames = orderPubService.getAllRegisteredAppNames();
            model.addAttribute("registeredNames", registeredNames);
            model.addAttribute("order", entity);
            return "orderForm";
        }

        // do i need to create a new Order Object?..i dont think so
        entity.setCustomerName(customerName);
        logger.info("order to be saved: {}", entity);
        orderPubService.addOrderToApp(entity);

        

        return "redirect:/orders";
    }
}
