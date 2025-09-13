package com.rahman.learning.repository;
import com.rahman.learning.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository  extends JpaRepository<Notes, Long> { }
