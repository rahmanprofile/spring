package com.rahman.learning.repository;
import com.rahman.learning.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface NoteRepository  extends JpaRepository<Notes, Long> {
    Page<Notes> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
