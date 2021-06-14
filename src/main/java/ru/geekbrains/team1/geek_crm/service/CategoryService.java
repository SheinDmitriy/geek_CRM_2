package ru.geekbrains.team1.geek_crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.team1.geek_crm.entities.Category;
import ru.geekbrains.team1.geek_crm.repository.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository repository;

    @Autowired
    public void setRepository(CategoryRepository repository) {
        this.repository = repository;
    }

    public void save(Category category) {
        repository.save(category);
    }

}
