package com.example.tobispring.chap04;

import com.example.tobispring.chap04.user.dao.UserDao;
import com.example.tobispring.chap04.user.dao.UserDaoJdbc;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class AppConfigChap04 {
    @Bean
    public UserDao userDao() {
        UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
        userDaoJdbc.setDataSource(dataSource());
        return userDaoJdbc;
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
