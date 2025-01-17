package sg.edu.nus.iss.day_25_workshop_subscriber.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.stereotype.Repository;
import sg.edu.nus.iss.day_25_workshop_subscriber.model.Order;
import sg.edu.nus.iss.day_25_workshop_subscriber.model.OrderDetails;
import sg.edu.nus.iss.day_25_workshop_subscriber.util.Util;

@Repository
public class SubscriberRepository {

    private final JdbcTemplate template;

    public SubscriberRepository(JdbcTemplate template) {
        this.template = template;
    }
    
    public boolean saveOrder(Order order) {
        
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int added = template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                Queries.SQL_ADD_ORDER_TO_DB,
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setDate(1, Util.parseToSQLDate(order.getOrderDate()));
            ps.setString(2, order.getCustomerName());
            ps.setString(3, order.getShipAddress());
            ps.setString(4, order.getNotes());
            ps.setDouble(5, order.getTax());
            return ps;
        }, keyHolder);

        if (added > 0 && keyHolder.getKey() != null) {
            int orderIdPriKey = keyHolder.getKey().intValue();
            return addOrderDetailsToDB(orderIdPriKey, order.getLineItems());
        }

        return false;
    }

    public boolean addOrderDetailsToDB(Integer orderIdPriKey, List<OrderDetails> items) {
        for (OrderDetails item : items) {
            int added = template.update(Queries.SQL_ADD_ORDER_DETAILS_TO_DB,
                            item.getProduct(),
                            item.getUnitPrice(),
                            item.getDiscount(),
                            item.getQuantity(),
                            orderIdPriKey);
            if (added <= 0) {
                return false;
            }
        }

        return true;
    } 

   
    
}
