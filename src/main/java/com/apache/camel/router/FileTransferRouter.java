package com.apache.camel.router;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;

@Component
public class FileTransferRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:C:\\source") .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                Message input = exchange.getIn();
                String data = input.getBody(String.class);
                StringTokenizer str = new StringTokenizer(data,",");
                String eId = str.nextToken();
                String ename = str.nextToken();
                String esal = str.nextToken();
                String dataModified ="{eid:"+eId+",ename:"+ename+",esal:"+esal+"}";
                Message output = exchange.getMessage();
                output.setBody(dataModified);
            }
        }).to("file:C:\\desti?fileName=emp.json");
    }
}
