package com.scott.accountservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

// For debuging
@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent> {
    private boolean setup = false;
    @Autowired
    private JdbcTemplate jdbcTmpl;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (setup) return;

        try {

            jdbcTmpl.execute("INSERT INTO account_type VALUES(1, 'Loan')");
            jdbcTmpl.execute("INSERT INTO account_type VALUES(2, 'Savings')");
            jdbcTmpl.execute("INSERT INTO account_type VALUES(3, 'Term Investment')");

        } catch(Exception e) {

        }
        // Create Account Types

    }
}
