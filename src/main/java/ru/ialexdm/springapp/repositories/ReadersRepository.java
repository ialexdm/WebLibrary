package ru.ialexdm.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ialexdm.springapp.models.Reader;

@Repository
public interface ReadersRepository extends JpaRepository<Reader, Integer> {
    Reader findByFullName(String name);
}
