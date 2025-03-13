package com.apache.camel.router;

import com.apache.camel.entities.Employee;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // REST configuration for handling requests
        rest("/employee/")
                .get("/{empName}")
                .to("direct:findEmployeeByName")
                .get("/").produces("application/json")
                .to("direct:findAllEmployees")
                .post("/")
                .type(Employee.class)
                .to("direct:saveEmployee")
                .delete("/{empId}")
                .to("direct:removeEmployee");

        // Route to find employee by name
        from("direct:findAllEmployees")
                .log("Received request to find employee by name: ${header.empName}")
                .bean("employeeService", "findEmployeeByEmpName(${header.empName})");

        // Route to find all employees
        from("direct:findAllEmployees")
                .log("Fetching all employees")
                .bean("employeeService", "findAllEmployees");

        // Route to save employee
        from("direct:saveEmployee")
                .log("Received employee data for saving: ${body}")
                .bean("employeeService", "saveEmployee(${body})");

        // Route to remove employee by ID
        from("direct:removeEmployee")
                .log("Received request to remove employee with ID: ${header.empId}")
                .bean("employeeService", "removeEmployee(${header.empId})");

    }

    // This method is used to set up Jackson DataFormat for JSON marshalling/unmarshalling
    private JacksonDataFormat getJacksonDataFormat(Class<?> unmarshalType) {
        JacksonDataFormat format = new JacksonDataFormat();
        format.setUnmarshalType(unmarshalType);
        return format;
    }
}
