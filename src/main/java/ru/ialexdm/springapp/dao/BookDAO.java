package ru.ialexdm.springapp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ialexdm.springapp.models.Book;
import ru.ialexdm.springapp.models.Reader;

import java.util.List;
import java.util.Optional;

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
        Optional<Book> book = jdbcTemplate.query("SELECT * FROM Book WHERE Book.id=?",
                new BeanPropertyRowMapper<>(Book.class),
                new Object[]{id}
        ).stream().findAny();
        return book.orElse(null);

    }

    public Optional<Reader> getBookReader(Integer bookId){
        return jdbcTemplate
                .query("SELECT Reader.* FROM Book join Reader on Book.reader_id = Reader.id where Book.id=?",
                        new BeanPropertyRowMapper<>(Reader.class),
                        new Object[]{bookId})
                .stream().findAny();
    }

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
