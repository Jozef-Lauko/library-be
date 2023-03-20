package sk.umb.example.library.category.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.*;
import sk.umb.example.library.category.persistance.entity.CategoryEntity;
import sk.umb.example.library.category.persistance.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public List<CategoryDetailDTO> getAllCategories(){
        return mapToDtoList(categoryRepository.findAll());
    }

    public List<CategoryDetailDTO> searchCategoryByName(String categoryName){
        return mapToDtoList(categoryRepository.findByCategory(categoryName));
    }

    public CategoryDetailDTO getCategoryById(Long categoryId){
        return mapToDto(getCategoryEntityById(categoryId));
    }

    @Transactional
    public Long createCategory(CategoryRequestDTO categoryRequestDTO) {
        CategoryEntity entity = mapToEntity(categoryRequestDTO);

        return categoryRepository.save(entity).getId();
    }
    @Transactional
    public void updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) {
        CategoryEntity category = getCategoryEntityById(categoryId);

        if (!Strings.isEmpty(categoryRequestDTO.getCategoryName())){
            category.setCategoryName(categoryRequestDTO.getCategoryName());
        }

        categoryRepository.save(category);

    }
    @Transactional
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    private List<CategoryDetailDTO> mapToDtoList(Iterable<CategoryEntity> categoryEntities){
        List<CategoryDetailDTO> categories = new ArrayList<>();

        categoryEntities.forEach(categoryEntity -> {
            CategoryDetailDTO dto = mapToDto(categoryEntity);
            categories.add(dto);
        });
        return categories;
    }

    private CategoryDetailDTO mapToDto(CategoryEntity categoryEntity) {
        CategoryDetailDTO dto = new CategoryDetailDTO();
        dto.setId(categoryEntity.getId());
        dto.setCategory(categoryEntity.getCategoryName());

        return dto;
    }
    private CategoryEntity mapToEntity(CategoryRequestDTO dto) {
        CategoryEntity category = new CategoryEntity();

        category.setCategoryName(dto.getCategoryName());
        return category;

    }

    private CategoryEntity getCategoryEntityById(Long categoryId) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()){
            throw new IllegalArgumentException("Category not found. ID: " + categoryId);
        }
        return category.get();
    }
}
