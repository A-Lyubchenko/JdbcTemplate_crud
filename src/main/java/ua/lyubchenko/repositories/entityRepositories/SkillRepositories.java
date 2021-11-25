package ua.lyubchenko.repositories.entityRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.lyubchenko.domains.Skill;
import ua.lyubchenko.repositories.IRepositories;

import java.util.List;
@Component
public class SkillRepositories implements IRepositories<Skill> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SkillRepositories(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Skill entity) {
        jdbcTemplate.update("INSERT INTO skills (id,department,level) VALUES(?,?,?)", entity.getId(), entity.getDepartment(), entity.getLevel());
    }

    @Override
    public List<Skill> read() {
        return jdbcTemplate.query("SELECT * FROM skills", new BeanPropertyRowMapper<>(Skill.class));
    }

    @Override
    public void update(int id, Skill entity) {
        jdbcTemplate.update("UPDATE skills SET department = ?, level = ? WHERE id = ?", entity.getDepartment(), entity.getLevel(), id);

    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM skills WHERE id = ?", id);

    }

    @Override
    public Skill get(int id) {
        return jdbcTemplate.query("SELECT * FROM skills WHERE id = ?", new Object[]{id}
                , new BeanPropertyRowMapper<>(Skill.class)).stream().findAny().orElse(null);
    }
}
