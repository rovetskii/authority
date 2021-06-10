package com.authority.demo.repositories;

import com.authority.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private final String getUsersSQL = "Select id, username, password from users";
    private final String saveUserSQL = "Insert into users (username, password) values (?, ?)";
    private final String findUserByUsernameSQL = "Select id, username, password from users where username = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> getAllUsers () {
        List<Map<String, Object>> listObject = jdbcTemplate.queryForList(getUsersSQL);
       return listObject.stream()
                .map(e -> new User((Long)e.get("id"),(String)e.get("username"), (String)e.get("password")))
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<User> findUserByUserName(String username) {
        return  Optional.ofNullable(jdbcTemplate.queryForObject(findUserByUsernameSQL, (rs, rowNum) -> {
           if(rs.next()) {
               return new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"));
           } else {
               return null;
           }
        }, username));
    }

    @Transactional
    public User saveUser(User u) {
        int k = jdbcTemplate.update(saveUserSQL, u.getUsername(), u.getPassword());
        return u;
    }
}
