package ru.ialexdm.springapp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ialexdm.springapp.models.Reader;

import java.util.List;

@Component
public class ReaderDAO {
    private final JdbcTemplate jdbcTemplate;
    public ReaderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Reader> index() {

        return jdbcTemplate.query("SELECT * FROM Reader", new BeanPropertyRowMapper<>(Reader.class));
    }

    public void save(Reader reader) {
        jdbcTemplate.update("INSERT INTO Reader (full_name, age) VALUES (?,?)",
                reader.getFullName(), reader.getAge());
    }

    public Reader show(int id){
        return jdbcTemplate.query("SELECT * FROM Reader WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Reader.class)).stream().findAny().orElse(null);
    }
    public void update(int id, Reader updatedReader) {
        jdbcTemplate.update("UPDATE Reader SET full_name=?, age=? WHERE id=?",
                updatedReader.getFullName(),updatedReader.getAge(), id);
    }

    public void delete(int id)
    {
        jdbcTemplate.update("DELETE FROM Reader WHERE id=?", id);
    }
}
