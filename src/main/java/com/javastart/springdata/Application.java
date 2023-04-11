package com.javastart.springdata;

import com.javastart.springdata.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * @author Karl
 * @link <a href="https://babayan.keenetic.link/">https://babayan.keenetic.link/</a>
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute(
                "INSERT INTO Account (id, name, email, bill)" +
                        "VALUES (1, 'Lori', 'lori@cat.xyz', 2000)");

//        Map<String, Object> resultSet = jdbcTemplate.queryForMap("SELECT * FROM Account");
//        System.out.println(resultSet.get("email"));
        Account accountById = findAccountById(1L);
        System.out.println(accountById);
    }

    private Account findAccountById(Long accountId) {
        String query = "SELECT * FROM Account WHERE id=%s";
        Map<String, Object> resultSet = jdbcTemplate.queryForMap(String.format(query, accountId));
        Account account = new Account();
        account.setId(accountId);
        account.setName((String) resultSet.get("name"));
        account.setEmail((String) resultSet.get("email"));
        account.setBill((Integer) resultSet.get("bill"));
        return account;
    }
}
