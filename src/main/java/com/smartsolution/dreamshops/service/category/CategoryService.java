package com.smartsolution.dreamshops.service.category;

import com.smartsolution.dreamshops.exceptions.AlreadyExistsException;
import com.smartsolution.dreamshops.exceptions.CategoryNotFoundException;
import com.smartsolution.dreamshops.model.Category;
import com.smartsolution.dreamshops.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found!"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found!"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category)
                .filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new AlreadyExistsException(category.getName()+ " Already exists!"));
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
         Category oldCategory= getCategoryById(categoryId);
         oldCategory.setName(category.getName());
         return categoryRepository.save(oldCategory);
    }

    @Override
    public void deleteCategoryById(Long id) {
    categoryRepository.findById(id)
            .ifPresentOrElse(categoryRepository::delete, () -> {
                throw new CategoryNotFoundException("Category not found!");
            } );
    }
}
