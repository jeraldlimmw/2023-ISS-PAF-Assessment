package ibf2022.assessment.paf.batch3.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Orders;

@Repository
public class OrderRepository {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	// TODO: Task 5
	public String insertOrders(Orders orders) {
		mongoTemplate.insert(orders, "orders");
		return orders.getOrderId();
	}
}
