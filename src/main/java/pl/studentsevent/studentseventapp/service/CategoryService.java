package pl.studentsevent.studentseventapp.service;

import pl.studentsevent.studentseventapp.controlers.dtos.CategoryDto;
import pl.studentsevent.studentseventapp.model.event.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    List<Category> adminGetAll();
    void addCategory(CategoryDto categoryDto);
    void deleteCategory(Long category_id);
}
