package com.rahman.learning.service;
import com.rahman.learning.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryRepository> getAllCategories() {
        return repository.findAll();
    }

    public CategoryRepository saveCategory(Category category) {
        return repository.save(category);
    }
}
