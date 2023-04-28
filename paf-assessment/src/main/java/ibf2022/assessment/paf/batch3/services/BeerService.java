package ibf2022.assessment.paf.batch3.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.assessment.paf.batch3.models.Orders;
import ibf2022.assessment.paf.batch3.repositories.OrderRepository;

@Service
public class BeerService {

	@Autowired
	private OrderRepository oRepo;

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(/* You can add any number parameters here */Orders orders) {
		// TODO: Task 5 
		String orderId = UUID.randomUUID().toString().substring(0, 8);
		LocalDateTime orderDate = LocalDateTime.now();
		orders.setOrderId(orderId);
		orders.setDate(orderDate);

		return oRepo.insertOrders(orders);
	}

}
