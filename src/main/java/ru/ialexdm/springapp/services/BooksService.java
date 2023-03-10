package ru.ialexdm.springapp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ialexdm.springapp.models.Book;
import ru.ialexdm.springapp.models.Reader;
import ru.ialexdm.springapp.repositories.BooksRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sortByYear) {
        List<Book> books;
        if (sortByYear){
            books = booksRepository.findAll(Sort.by("year"));
        }else{
            books = booksRepository.findAll();
        }
        return books;
    }

    public PagedBook findAll(int numberOfPage, int itemsPerPage, boolean sortByYear) {
        PagedBook pagedBook;
        if (sortByYear){
            pagedBook = new PagedBook(booksRepository.findAll(PageRequest.of(numberOfPage, itemsPerPage, Sort.by("year"))));
        }else {
            pagedBook = new PagedBook(booksRepository.findAll(PageRequest.of(numberOfPage, itemsPerPage)));
        }
        return pagedBook;
    }

    public Book findOne(Integer id) {
        Optional<Book> foundedBook = booksRepository.findById(id);
        return foundedBook.orElse(null);
    }
    public List<Book> findAllByName(String name){
        if (name == null || name.isEmpty()){
            return Collections.emptyList();
        }
        return booksRepository.findAllByNameContainsIgnoreCase(name);
    }

    public Reader getBookReader(Book book) {
        return book.getBookReader();
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(Integer id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(Integer id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void transfer(Integer id, Reader reader) {
        Optional<Book> transferedBook = booksRepository.findById(id);
        transferedBook.ifPresent(b ->{
            b.setBookReader(reader);
            if (b.getBookReader() == null){
                b.setWasGot(null);
            }else{
                b.setWasGot(LocalDate.now());
            }
        });
    }
    public class PagedBook {
        private List<Book> booksOnPage;
        private List<Integer> pageNumbers;


        public List<Book> getBooksOnPage() {
            return booksOnPage;
        }

        public List<Integer> getPageNumbers() {
            return pageNumbers;
        }

        PagedBook(Page<Book> booksPage) {
            booksOnPage = booksPage.getContent();
            int totalPages = booksPage.getTotalPages();
            if (totalPages > 0) {
                pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                        .boxed()
                        .collect(Collectors.toList());
            }
        }
    }
}
