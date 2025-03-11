package com.apache.camel.ApacheCamelDemo.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

@Component
public class SampleKafkaCamel extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Kafka Producer
        from("timer://foo?period=1000")
                .setBody(constant("Message from Camel Kafka"))
                .setHeader(KafkaConstants.KEY, constant("Camel")) // Key of the message
                .setHeader(KafkaConstants.OFFSET,constant(25))
                .setHeader(KafkaConstants.PARTITION,constant(2))
                .to("kafka:myTopic3?brokers=localhost:9092");

        // Kafka Consumer
        from("kafka:myTopic3?brokers=localhost:9092")
                .log("Message received from Kafka : ${body}")
                .log("    on the topic ${headers[kafka.TOPIC]}")
                .log("    on the partition ${headers[kafka.PARTITION]}")
                .log("    with the offset ${headers[kafka.OFFSET]}")
                .log("    with the key ${headers[kafka.KEY]}");
    }

}