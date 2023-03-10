package com.example.tobispring.chap05;

import com.example.tobispring.chap05.user.dao.UserDao;
import com.example.tobispring.chap05.user.dao.UserDaoJdbc;
import com.example.tobispring.chap05.user.service.UserService;
import java.sql.DriverManager;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class AppConfigChap05 {
    @Bean
    public UserService userService() {
        UserService userService = new UserService();
        userService.setUserDao(userDao());
        userService.setTransactionManager(transactionManager());
        return userService;
    }

    @Bean
    public UserDao userDao() {
        UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
        userDaoJdbc.setDataSource(dataSource());
        return userDaoJdbc;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
//        return new JtaTransactionManager();
    }

    /*@Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }*/

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost/tobi?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }
}
