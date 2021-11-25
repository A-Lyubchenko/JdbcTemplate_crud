package ua.lyubchenko.repositories.entityRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.lyubchenko.domains.Developer;
import ua.lyubchenko.domains.Project;
import ua.lyubchenko.repositories.IRepositories;

import java.sql.Date;
import java.util.List;

@Component
public class ProjectRepositories implements IRepositories<Project> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectRepositories(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Project entity) {
        jdbcTemplate.update("INSERT INTO projects (id,name,start,coast) VALUES(?,?,?,?)",
                entity.getId(), entity.getName(), Date.valueOf(entity.getStart()), entity.getCoast());
    }

    @Override
    public List<Project> read() {
        return jdbcTemplate.query("SELECT * FROM projects ORDER BY id", new BeanPropertyRowMapper<>(Project.class));
    }

    @Override
    public void update(int id, Project entity) {
        jdbcTemplate.update("UPDATE projects SET name = ?, start = ?, coast = ? WHERE id = ?",
                entity.getName(), Date.valueOf(entity.getStart()), entity.getCoast(), id);

    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM projects WHERE id = ?", id);

    }

    @Override
    public Project get(int id) {
        return jdbcTemplate.query("SELECT * FROM projects WHERE id = ?", new Object[]{id}
                , new BeanPropertyRowMapper<>(Project.class)).stream().findAny().orElse(null);
    }
}
