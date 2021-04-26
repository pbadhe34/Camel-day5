package com.app;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CustomProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("The CustomProcessor for rest get data ");
		String headerValue = (String) exchange.getIn().getHeader(Exchange.HTTP_URI);
		System.out.println("The Processor http  path is  " + headerValue);

		String text = headerValue.substring(headerValue.lastIndexOf("/") + 1);
		int num = Integer.parseInt(text);

		if (num > 100) {
			System.out.println("The Processor throwing new CustomException");
			throw new CustomException();
		}	 
		 
		// Normal message to output-->next stage endpoint
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());

	}

}
