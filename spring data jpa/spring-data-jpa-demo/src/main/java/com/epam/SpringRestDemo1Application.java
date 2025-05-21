package com.epam;

import com.epam.dtos.EmployeeDTO;
import com.epam.service.EmployeeService;
import com.epam.welcome.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringRestDemo1Application implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringRestDemo1Application.class, args);
        Greeting greeting = context.getBean(Greeting.class);
        greeting.sayHello();
    }

    @Override
    public void run(String... args) throws Exception {
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setName("Mr Bean");
        employeeDTO1.setAge(50);
        employeeDTO1.setEmail("bean@example.com");
        employeeDTO1.setDepartment("Field Work Department");
        employeeDTO1.setLocation("America");

        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setName("Mr Stark");
        employeeDTO2.setAge(35);
        employeeDTO2.setEmail("stark@example.com");
        employeeDTO2.setDepartment("Robotics Department");
        employeeDTO2.setLocation("New York");

        employeeService.saveEmployee(employeeDTO1);
        employeeService.saveEmployee(employeeDTO2);

    }
}
