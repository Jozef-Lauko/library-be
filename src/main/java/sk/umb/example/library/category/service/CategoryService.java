package sk.umb.example.library.category.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.logging.log4j.util.Strings;

@Service
public class CategoryService {
    private final Map<Long, CategoryDetailDTO> categoryDatabase = new HashMap<>();
    private final AtomicLong lastIndex = new AtomicLong(0);
    @Transactional
    public CategoryDetailDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        CategoryDetailDTO categoryDetailDTO = new CategoryDetailDTO();
        categoryDetailDTO.setId(lastIndex.getAndIncrement());
        categoryDetailDTO.setCategory(Strings.isBlank(categoryRequestDTO.getCategory()) ? null : categoryRequestDTO.getCategory());
        categoryDatabase.put(categoryDetailDTO.getId(), categoryDetailDTO);
        return categoryDetailDTO;
    }

    public CategoryDetailDTO getCategory(Long categoryId) {
        return categoryDatabase.get(categoryId);
    }

    public List<CategoryDetailDTO> getAllCategories() {
        return new ArrayList<>(categoryDatabase.values());
    }
    @Transactional
    public void updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) {
        CategoryDetailDTO categoryDetailDTO = categoryDatabase.get(categoryId);
        if (categoryDetailDTO != null) {
            categoryDetailDTO.setCategory(Strings.isBlank(categoryRequestDTO.getCategory()) ? null : categoryRequestDTO.getCategory());
        }
    }
    @Transactional
    public void deleteCategory(Long categoryId) {
        categoryDatabase.remove(categoryId);
    }

}
