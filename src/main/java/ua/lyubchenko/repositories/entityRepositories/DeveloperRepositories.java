package ua.lyubchenko.repositories.entityRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.lyubchenko.domains.Developer;
import ua.lyubchenko.repositories.IRepositories;

import java.util.List;

@Component
public class DeveloperRepositories implements IRepositories<Developer> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DeveloperRepositories(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Developer entity) {
        jdbcTemplate.update("INSERT INTO developers (id,name,age,sex,phone_number,salary) VALUES(?,?,?,?,?,?)",
                entity.getId(), entity.getName(), entity.getAge(), entity.getSex(), entity.getPhone_number(), entity.getSalary());
    }

    @Override
    public List<Developer> read() {
        return jdbcTemplate.query("SELECT * FROM developers ORDER BY id", new BeanPropertyRowMapper<>(Developer.class));
    }

    @Override
    public void update(int id, Developer entity) {
        jdbcTemplate.update("UPDATE developers SET name = ?, age = ?, sex = ?, phone_number = ?, salary = ? WHERE id = ?",
                entity.getName(), entity.getAge(), entity.getSex(), entity.getPhone_number(), entity.getSalary(), id);

    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM developers WHERE id = ?", id);

    }

    @Override
    public Developer get(int id) {
        return jdbcTemplate.query("SELECT * FROM developers WHERE id = ?", new Object[]{id}
                , new BeanPropertyRowMapper<>(Developer.class)).stream().findAny().orElse(null);
    }
}
