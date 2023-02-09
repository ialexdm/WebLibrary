package ru.ialexdm.springapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ialexdm.springapp.models.Book;
import ru.ialexdm.springapp.models.Reader;
import ru.ialexdm.springapp.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }
    public Book findOne(Integer id){
        Optional<Book> foundedBook = booksRepository.findById(id);
        return foundedBook.orElse(null);
    }
    public Reader getBookReader(Book book) {
        return book.getBookReader();
    }
    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }
    @Transactional
    public void update(Integer id, Book updatedBook){
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }
    @Transactional
    public void delete(Integer id){
        booksRepository.deleteById(id);
    }
    @Transactional
    public void transfer(Integer id, Reader reader) {
        Optional<Book> transferedBook = booksRepository.findById(id);
        transferedBook.ifPresent(b -> b.setBookReader(reader));
    }
}
