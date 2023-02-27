package sk.umb.example.library.category.service;

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

    public CategoryDetailDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        CategoryDetailDTO categoryDetailDTO = new CategoryDetailDTO();
        categoryDetailDTO.setId(lastIndex.getAndIncrement());
        categoryDetailDTO.setName(Strings.isBlank(categoryRequestDTO.getName()) ? null : categoryRequestDTO.getName());
        categoryDatabase.put(categoryDetailDTO.getId(), categoryDetailDTO);
        return categoryDetailDTO;
    }

    public CategoryDetailDTO getCategory(Long categoryId) {
        return categoryDatabase.get(categoryId);
    }

    public List<CategoryDetailDTO> getAllCategories() {
        return new ArrayList<>(categoryDatabase.values());
    }

    public CategoryDetailDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) {
        CategoryDetailDTO categoryDetailDTO = categoryDatabase.get(categoryId);
        if (categoryDetailDTO != null) {
            categoryDetailDTO.setName(Strings.isBlank(categoryRequestDTO.getName()) ? null : categoryRequestDTO.getName());
            return categoryDetailDTO;
        }
        return null;
    }

    public boolean deleteCategory(Long categoryId) {
        return categoryDatabase.remove(categoryId) != null;
    }

}
