package com.rahman.learning.controller;
import com.rahman.learning.entity.Notes;
import com.rahman.learning.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("notes")
public class NotesController {
    private NoteService noteService;

    @Autowired
    public NoteService setService(NoteService noteService) {
       return this.noteService = noteService;
    }

    @PostMapping
    public Map<String, Object> saveNotes(@RequestBody Notes notes) {
        return noteService.saveNotes(notes);
    }

    @GetMapping()
    public Map<String, Object> fetchNotes(@RequestParam  int page, @RequestParam int size) {
        return noteService.fetchNotes(page, size);
    }

    @GetMapping("/id/{id}")
    public Map<String, Object> viewNotes(@PathVariable Long id) {
        return noteService.viewNotes(id);
    }

    @DeleteMapping("/id/{id}")
    public Map<String, Object> deleteNotes(@PathVariable Long id) {
        return noteService.deleteNotes(id);
    }

    @PutMapping("/id/{id}")
    public Map<String, Object> updateNotes(@PathVariable Long id, @RequestBody Notes notes) {
        return noteService.updateNotes(notes, id);
    }
    @GetMapping("/search")
    public List<Notes> searchNotes(@RequestParam String title, @RequestParam int page, @RequestParam int size) {
        return noteService.searchNotesByTitle(title, page, size);
    }
}
