package ru.ialexdm.springapp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ialexdm.springapp.models.Book;

import java.util.List;
@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {

        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book (name, author, year) VALUES (?,?,?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public Book show(Integer id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE Book.id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public List<Book> showReaderBook(Integer id){
        return  jdbcTemplate.query("SELECT * FROM Book WHERE Book.reader_id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));   }

    public void update(Integer id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?",
                updatedBook.getName(),
                updatedBook.getAuthor(),
                updatedBook.getYear(),
                id);
    }
    public void transfer(Integer id, Integer readerId) {
        jdbcTemplate.update("UPDATE Book SET reader_id=? WHERE id=?",
                readerId, id);
    }

    public void delete(Integer id)
    {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
}
