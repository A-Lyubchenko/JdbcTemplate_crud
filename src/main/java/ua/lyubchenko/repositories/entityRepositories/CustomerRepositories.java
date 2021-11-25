package ua.lyubchenko.repositories.entityRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.lyubchenko.domains.Customer;
import ua.lyubchenko.repositories.IRepositories;

import java.util.List;
@Component
public class CustomerRepositories implements IRepositories<Customer> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepositories(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Customer entity) {
        jdbcTemplate.update("INSERT INTO customers (id,name,location) VALUES(?,?,?)", entity.getId(), entity.getName(), entity.getLocation());
    }

    @Override
    public List<Customer> read() {
        return jdbcTemplate.query("SELECT * FROM customers", new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public void update(int id, Customer entity) {
        jdbcTemplate.update("UPDATE customers SET name = ?, location = ? WHERE id = ?", entity.getName(), entity.getLocation(), id);

    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM customers WHERE id = ?", id);

    }

    @Override
    public Customer get(int id) {
        return jdbcTemplate.query("SELECT * FROM customers WHERE id = ?", new Object[]{id}
                , new BeanPropertyRowMapper<>(Customer.class)).stream().findAny().orElse(null);
    }
}
