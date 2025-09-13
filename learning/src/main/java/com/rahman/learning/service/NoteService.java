package com.rahman.learning.service;
import com.rahman.learning.entity.Notes;
import com.rahman.learning.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public Map<String, Object> saveNotes(Notes notes) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (Objects.isNull(notes.getTitle()) || notes.getContent().isBlank()) {
                response.put("error", "Title cannot be empty");
            }
            if (Objects.isNull(notes.getContent())) {
                response.put("error", "Content cannot be empty.");
            }
            if (!response.isEmpty()) {
                return response;
            }
            noteRepository.save(notes);
            response.put("success", "Note saved successfully");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Map<String, Object> fetchNotes(int page, int size) {
        Map<String, Object> response = new HashMap<>();
        try {
            Page notesPage = noteRepository.findAll(PageRequest.of(page - 1, size));

            response.put("success", true);
            response.put("message", "Fetched successfully");
            response.put("page", page);
            response.put("pageSize", size);
            response.put("totalPages", notesPage.getTotalPages());
            response.put("totalElements", notesPage.getTotalElements());
            response.put("data", notesPage.getContent());

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to fetch notes: " + e.getMessage());
            response.put("page", page);
            response.put("pageSize", size);
            response.put("data", new java.util.ArrayList<>());
            e.printStackTrace();
        }
        return response;
    }


    public Map<String, Object> viewNotes(Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (Objects.isNull(id)) {
                response.put("error", "Id cannot be empty");
            }
            final Notes notes = noteRepository.findById(id).orElse(null);
            if (Objects.isNull(notes)) {
                response.put("error", "Notes not found");
            }
            response.put("success", true);
            response.put("note", notes);
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public Map<String, Object> updateNotes(Notes notes, Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (Objects.isNull(id)) {
                response.put("success", false);
                response.put("message", "Id cannot be null");
                return response;
            }

            var existingNoteOpt = noteRepository.findById(id);
            if (existingNoteOpt.isEmpty()) {
                response.put("success", false);
                response.put("message", "Note not found with id: " + id);
                return response;
            }

            if (Objects.isNull(notes)) {
                response.put("success", false);
                response.put("message", "Notes object cannot be null");
                return response;
            }

            Notes existingNote = existingNoteOpt.get();
            existingNote.setTitle(notes.getTitle());
            existingNote.setContent(notes.getContent());
            noteRepository.save(existingNote);

            response.put("success", true);
            response.put("message", "Note updated successfully");
            response.put("data", existingNote);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Failed to update note: " + e.getMessage());
        }

        return response;
    }


    public Map<String, Object> deleteNotes(Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (Objects.isNull(id)) {
                response.put("error", "Id cannot be empty");
            }
            if (Objects.isNull(noteRepository.findById(id))) {
                response.put("error", "Notes not found");
            }
            noteRepository.deleteById(id);
            response.put("success", "Note deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
