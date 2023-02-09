package ru.ialexdm.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ialexdm.springapp.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
