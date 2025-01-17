package sg.edu.nus.iss.day_25_workshop_publisher.util;


import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.day_25_workshop_publisher.model.Order;
import sg.edu.nus.iss.day_25_workshop_publisher.model.OrderDetails;

public class Util {

    public static JsonObject toJsonObject(Order order) {

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (OrderDetails item : order.getLineItems()) {
            JsonObject itemJson = Json.createObjectBuilder()
                                        .add("product", item.getProduct())
                                        .add("unitPrice", item.getUnitPrice())
                                        .add("discount", item.getDiscount())
                                        .add("quantity", item.getQuantity())
                                        .build();
            jsonArrayBuilder.add(itemJson);
        }

        JsonObject orderJson = Json.createObjectBuilder()
                                .add("orderDate", order.getOrderDate().toString())
                                .add("customerName", order.getCustomerName())
                                .add("shipAddress", order.getShipAddress())
                                .add("notes", order.getNotes())
                                .add("tax", order.getTax())
                                .add("lineItems", jsonArrayBuilder.build())
                                .build();
        return orderJson;
    }
    
}
