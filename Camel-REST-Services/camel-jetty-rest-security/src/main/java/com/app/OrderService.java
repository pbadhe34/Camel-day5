package com.app;

import java.util.Map;
import java.util.List;

public interface OrderService {
	
	Order[]  getAllOrders();
	String handlePostMethod(int id);

    Order getOrder(int orderId);

    void updateOrder(Order order);

    String createOrder(Order order);

    void cancelOrder(int orderId);

}
