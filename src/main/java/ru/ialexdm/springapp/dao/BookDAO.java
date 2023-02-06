package ru.ialexdm.springapp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ialexdm.springapp.models.Book;

import java.util.ArrayList;
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

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?",
                updatedBook.getName(),updatedBook.getAuthor(),updatedBook.getYear(), id);
    }

    public void delete(int id)
    {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
}
