package com.javainuse.main;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext ctxLoad = new ClassPathXmlApplicationContext("applicationContext-camel.xml");
        
         CamelContext ctx = (CamelContext) ctxLoad.getBean("id1");
         ProducerTemplate templ =ctx.createProducerTemplate();
         templ.sendBody("direct:input", "");
         
        ctx.start();
        System.out.println("Application context started");
        try {
            Thread.sleep(5 * 60 * 1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
          
        ctx.stop();
        
    }
}