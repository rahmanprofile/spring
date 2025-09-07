package com.rahman.learning.controller;
import com.rahman.learning.entity.CategoryEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final Map<Integer, CategoryEntity> categoryEntities = new HashMap<>();

    @PostMapping
    public boolean saveCategory(@RequestBody CategoryEntity entity) {
        categoryEntities.put(entity.getId(), entity);
        return true;
    }

    @GetMapping
    public List<CategoryEntity> fetchCategory() {
        return new ArrayList<>(categoryEntities.values());
    }

    @DeleteMapping("/id/{id}")
    public CategoryEntity deleteCategory(@PathVariable int id) {
        return categoryEntities.remove(id);
    }

    @GetMapping("/id/{id}")
    public CategoryEntity detailCategory(@PathVariable int id) {
        return categoryEntities.get(id);
    }

    @PutMapping("/id/{id}")
    public CategoryEntity updateCategory(@RequestBody CategoryEntity entity, @PathVariable int id) {
        return categoryEntities.put(id, entity);
    }
}
