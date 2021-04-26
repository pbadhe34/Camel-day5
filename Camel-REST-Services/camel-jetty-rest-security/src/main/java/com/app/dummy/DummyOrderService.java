package com.app.dummy;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.app.Order;
import com.app.OrderService;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class DummyOrderService implements OrderService {

    // in memory dummy order system
    private Map<Integer, Order> orders = new HashMap<>();

    private final AtomicInteger idGen = new AtomicInteger();

    public DummyOrderService() {
        // setup some dummy orders to start with
        setupDummyOrders();
    }

    
    @Override
    public Order[] getAllOrders() {
    	 System.out.println("DummyOrderService getAllOrders ");
			
			  List list = new ArrayList<Order>(orders.values()); Object [] src =
			  list.toArray(); Order [] array = Arrays.copyOf(src, src.length,
			  Order[].class);
			  return array;
			         
        
    }
    
    
     
    public Order getSingleOrder() {
    	com.app.Order obj = new com.app.Order();
    	obj.setId(0);
    	obj.setPartName("BIGGER PARTS############");
        return obj;
    }
    
    
    @Override
    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    @Override
    public void updateOrder(Order order) {
        int id = order.getId();
        orders.remove(id);
        orders.put(id, order);
    }

    @Override
    public String createOrder(Order order) {
        int id = idGen.incrementAndGet();
        order.setId(id);
        orders.put(id, order);
        return "" + id;
    }
    
    @Override
    public void cancelOrder(int orderId) {
        orders.remove(orderId);
    }
    
    

    public void setupDummyOrders() {
    	Order order = new Order();
        order.setAmount(1);
        order.setPartName("motor");
        order.setCustomerName("honda");
        createOrder(order);

        order = new Order();
        order.setAmount(3);
        order.setPartName("brake");
        order.setCustomerName("toyota");
        createOrder(order);
    }


	@Override
	public String handlePostMethod(int id) {
		System.out.println("Running handlePostMethod");
		String response = "{\"id\":"+id+",\"partName\":\"Unknown\",\"amount\":0,\"customerName\":\"UNKNOWN\"}";
		 return  response;
		
	}

}
