package com.apache.camel.ApacheCamelDemo.router;

import com.apache.camel.ApacheCamelDemo.service.BookService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class BookRoute extends RouteBuilder {


    public void configure() throws Exception {

        rest("/book")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .get("/{name}").id("findBookByName")
                .to("direct:findBookByName")
                .get("/").id("findAllBooks")
                .to("direct:findAllBooks")
                .post("/").id("saveBook")
                .to("direct:saveBook")
                .delete("/{bookId}").id("removeBook")
                .to("direct:removeBook");

        from("direct:findBookByName")
                .log("Received header : ${header.name}")
                .bean("bookService", "findBookByName(${header.name})");

        from("direct:findAllBooks")
                .bean(BookService.class, "findAllBooks");


        from("direct:saveBook")
                .log("Received Body ${body}")
                .bean(BookService.class, "addBook(${body})");


        from("direct:removeBook")
                .log("Received header : ${header.bookId}")
                .bean(BookService.class, "removeBook(${header.bookId})");
    }

    private JacksonDataFormat getJacksonDataFormat(Class<?> unmarshalType) {
        JacksonDataFormat format = new JacksonDataFormat();
        format.setUnmarshalType(unmarshalType);
        return format;
    }
}

