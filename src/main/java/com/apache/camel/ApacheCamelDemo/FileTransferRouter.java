package com.apache.camel.ApacheCamelDemo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileTransferRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:C:\\source").to("file:C:\\desti");
    }
}
