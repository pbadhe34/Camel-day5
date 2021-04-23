package org.app;

import java.util.Date;
import java.util.Objects;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
import org.apache.camel.main.MainSupport;
import org.apache.camel.main.MainListenerSupport;
import org.apache.camel.Processor;
import org.apache.camel.component.snmp.*;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.CamelContext;
import org.apache.camel.StartupListener;
import org.snmp4j.PDU;

//Test with MIMICSNMP Simulator
public class CamelSNMPTest {

    public static void main(String[] args) throws Exception {

        // CamelContext contains methods to add routes, components etc
        CamelContext context = new DefaultCamelContext();

        // work with Snmp component
        context.addComponent("snmp", new SnmpComponent());

        // Add the route
        context.addRoutes(new RouteBuilder() {
                public void configure() {

                // When a SNMP TRAP is received on port 162 from any source..
                from("snmp:0.0.0.0:162?protocol=udp&type=TRAP").process(myProcessor).bean(PrintBean.class,"print").to("file://D:/traps.log");
                }
            });

        // Get notification when the context is started..
        context.addStartupListener(new StartupListener(){
            public void onCamelContextStarted(CamelContext context, boolean alreadyStarted)
            {
                System.out.println("Camel context is started");
            }
        });

        //The SNMP component gives the ability to poll SNMP capable devices or receiving traps
        
            // Start the context
            context.start();

            // Sleep for sometime, so that the context can keep running
            // otherwise the program exits
            Thread.sleep(1000000);

            context.stop();
    }

    public static Processor myProcessor = new Processor() {
        public void process(Exchange trap) throws Exception {

            // getIn() means get the inbound message
            // Since we are using Snmp, we get SnmpMessage object, we need to typecast from Message
            SnmpMessage msg=(SnmpMessage)trap.getIn();

            // PDU refers to SNMP4J PDU class, the camel SNMP component internally uses it
            PDU pdu=msg.getSnmpMessage();

            // Get the VariableBindings (i.e.) the list of OID and value pairs
            // print them each
            pdu.getVariableBindings().forEach(System.out::println);
        }
    };

    public static class PrintBean {


        // This also does the same thing
        public void print(String msg)
        {
                System.out.println(msg);
        }
    }

}