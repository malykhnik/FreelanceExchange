package com.malykhnik.freelanceexchnge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.jdbc.Sql;

@SpringBootApplication
//@Sql("C:/Users/shere/IdeaProjects/FreelanceExchnge/src/main/resources/sql_scrypts/createTable.sql")
public class FreelanceExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreelanceExchangeApplication.class, args);
    }

}
