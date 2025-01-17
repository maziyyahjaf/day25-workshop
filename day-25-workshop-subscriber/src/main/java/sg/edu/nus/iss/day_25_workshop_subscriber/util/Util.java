package sg.edu.nus.iss.day_25_workshop_subscriber.util;

import java.io.StringReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.day_25_workshop_subscriber.model.Order;
import sg.edu.nus.iss.day_25_workshop_subscriber.model.OrderDetails;

public class Util {
    
    public static Order toOrder(String dataJsonString) {
        JsonReader jsonReader = Json.createReader(new StringReader(dataJsonString));
        JsonObject root = jsonReader.readObject();
        String dateString = root.getString("orderDate");
        LocalDate orderDate = parseToLocalDate(dateString);
        String customerName = root.getString("customerName");
        String shipAddress = root.getString("shipAddress");
        // String notes = (root.getString("notes") != null) ? root.getString("notes") : "No notes provided";
        // String notes = (root.containsKey("notes") && !root.getString("notes").isEmpty()) 
        //                 ? root.getString("notes") 
        //                 : "No notes provided";
        String notes = root.getString("notes", "No notes provided");
        // only provides a default value if the key "notes" is missing from the JSON object.
        Double tax = root.getJsonNumber("tax").doubleValue();

        JsonArray jsonArray = root.getJsonArray("lineItems");
        List<OrderDetails> details= new ArrayList<>();
        for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
            OrderDetails item = toOrderDetails(jsonObject);
            details.add(item);
        }

        Order order = new Order(orderDate, customerName, shipAddress, notes, tax, details);
        return order;

    }

    public static OrderDetails toOrderDetails(JsonObject jsonObject) {
        String product = jsonObject.getString("product");
        Double unitPrice = jsonObject.getJsonNumber("unitPrice").doubleValue();
        Double discount = jsonObject.getJsonNumber("discount").doubleValue();
        Integer quantity = jsonObject.getInt("quantity");

        OrderDetails item = new OrderDetails(product, unitPrice, discount, quantity);
        return item;
    }

    private static LocalDate parseToLocalDate(String dateString) {
        return LocalDate.parse(dateString);
    }

    public static Date parseToSQLDate(LocalDate date){
        return Date.valueOf(date);
    }
}
