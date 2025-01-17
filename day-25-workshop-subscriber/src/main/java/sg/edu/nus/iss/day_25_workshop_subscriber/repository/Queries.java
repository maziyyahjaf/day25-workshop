package sg.edu.nus.iss.day_25_workshop_subscriber.repository;

public class Queries {

    public static final String SQL_ADD_ORDER_TO_DB = 
            """
            INSERT INTO orders(order_date, customer_name, ship_address, notes, tax)
                values(?, ?, ?, ?, ?)
            
            """;
    
    public static final String SQL_ADD_ORDER_DETAILS_TO_DB = 
            """
                INSERT INTO order_details(product, unit_price, discount, quantity, order_id)
                values(?, ?, ?, ?, ?)
            
            """;
}
