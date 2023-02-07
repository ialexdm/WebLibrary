package ru.ialexdm.springapp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ialexdm.springapp.models.Book;
import ru.ialexdm.springapp.models.Reader;

import java.util.List;
import java.util.Optional;

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

    public Reader show(Integer id){
        Optional<Reader> reader = jdbcTemplate.query("SELECT * FROM Reader WHERE id=?",
                new BeanPropertyRowMapper<>(Reader.class),
                new Object[]{id}
                ).stream().findAny();
        return reader.orElse(null);
    }
    public Reader show(String fullName) {
         Optional<Reader> reader = jdbcTemplate.query("select * from reader where full_name=?",
                 new BeanPropertyRowMapper<>(Reader.class),
                 new Object[]{fullName}
                ).stream().findAny();
        return reader.orElse(null);
    }
    public List<Book> showReaderBook(Integer readeId){
        return  jdbcTemplate.query("SELECT * FROM Book WHERE Book.reader_id=?",
                new BeanPropertyRowMapper<>(Book.class),
                readeId);
    }
    public void update(Integer id, Reader updatedReader) {
        jdbcTemplate.update("UPDATE Reader SET full_name=?, age=? WHERE id=?",
                updatedReader.getFullName(),updatedReader.getAge(), id);
    }

    public void delete(Integer id)
    {
        jdbcTemplate.update("DELETE FROM Reader WHERE id=?", id);
    }


}
