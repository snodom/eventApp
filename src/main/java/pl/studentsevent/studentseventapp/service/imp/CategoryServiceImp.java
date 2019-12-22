package pl.studentsevent.studentseventapp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.studentsevent.studentseventapp.controlers.dtos.CategoryDto;
import pl.studentsevent.studentseventapp.controlers.dtos.EventDto;
import pl.studentsevent.studentseventapp.model.event.Category;
import pl.studentsevent.studentseventapp.model.event.Event;
import pl.studentsevent.studentseventapp.respository.CategoryRepository;
import pl.studentsevent.studentseventapp.service.CategoryService;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAll() {
        return categoryDtoListMapper(categoryRepository.findAll());

    }

    @Override
    public List<Category> adminGetAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long category_id) {

        Category category = categoryRepository.getOne(category_id);

        for(Event event : category.getEvents()){
            event.getCategories().remove(category);
        }

        categoryRepository.deleteById(category_id);
    }

    private List<CategoryDto> categoryDtoListMapper(List<Category> categories){
        return categories.stream().map((this::categoryDtoMapper)).collect(Collectors.toList());
    }

//    private List<CategoryDto> categoryDtoLasdistMapper(List<Category> categories){
//        List<CategoryDto> categoryDtos = new ArrayList<>();
//        for(int i=0; i<categories.size(); i++){
//            CategoryDto categoryDto = new CategoryDto();
//            categoryDto.setCategory_id(categories.get(i).getCategory_id());
//            categoryDto.setName(categories.get(i).getName());
//            categoryDtos.add(categoryDto);
//        }
//        return categoryDtos;
//    }

    private CategoryDto categoryDtoMapper(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategory_id(category.getCategory_id());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}




