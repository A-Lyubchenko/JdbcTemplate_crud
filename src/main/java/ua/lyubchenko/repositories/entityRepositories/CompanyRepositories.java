package ua.lyubchenko.repositories.entityRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.lyubchenko.domains.Company;
import ua.lyubchenko.repositories.IRepositories;

import java.util.List;

@Component
public class CompanyRepositories implements IRepositories<Company> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CompanyRepositories(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Company entity) {
        jdbcTemplate.update("INSERT INTO companies (id,name,location) VALUES(?,?,?)", entity.getId(), entity.getName(), entity.getLocation());
    }

    @Override
    public List<Company> read() {
        return jdbcTemplate.query("SELECT * FROM companies", new BeanPropertyRowMapper<>(Company.class));
    }

    @Override
    public void update(int id, Company entity) {
        jdbcTemplate.update("UPDATE companies SET name = ?, location = ? WHERE id = ?", entity.getName(), entity.getLocation(), id);

    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM companies WHERE id = ?", id);

    }

    @Override
    public Company get(int id) {
        return jdbcTemplate.query("SELECT * FROM companies WHERE id = ?", new Object[]{id}
                , new BeanPropertyRowMapper<>(Company.class)).stream().findAny().orElse(null);
    }
}
