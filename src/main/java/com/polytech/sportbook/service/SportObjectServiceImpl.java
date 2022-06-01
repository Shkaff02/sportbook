package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Category;
import com.polytech.sportbook.domain.SportObject;
import com.polytech.sportbook.repo.CategoryRepository;
import com.polytech.sportbook.repo.OwnerRepository;
import com.polytech.sportbook.repo.SportObjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class SportObjectServiceImpl implements SportObjectService{
    private final SportObjectRepository sportObjectRepository;
    private final CategoryRepository categoryRepository;
    private final OwnerRepository ownerRepository;


    @Override
    public SportObject saveObject(Long own_id, SportObject object) {
        log.info("Saving object {}", object.getName());
        object.setOwner(ownerRepository.findById(own_id).orElse(null));
        return sportObjectRepository.save(object);
    }

    @Override
    public Category saveCategory(Category category) {
        log.info("Saving category {}", category.getName());
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> categories() {
        log.info("Fetching all categories from database");
        return categoryRepository.findAll();
    }

    @Override
    public void addCategoryToObject(String categoryName, String objectName) {
        log.info("Adding category {} to object {}", categoryName, objectName);
        SportObject object = sportObjectRepository.findByName(objectName);
        Category category = categoryRepository.findByName(categoryName);
        object.getCategories().add(category);
        sportObjectRepository.save(object);
    }

    @Override
    public SportObject getObject(String name) {
        log.info("Fetching object {} from database", name);
        return sportObjectRepository.findByName(name);
    }

    @Override
    public SportObject getObjectById(Long id) {
        log.info("Fetching object by id {}", id);
        return sportObjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<SportObject> objects() {
        log.info("Fetching all objects from database");
        return sportObjectRepository.findAll();
    }

    @Override
    public void deleteObject(Long id) {
        log.info("Deleting object by id {}", id);
        sportObjectRepository.deleteById(id);
    }

    @Override
    public SportObject updateObject(Long id, SportObject object) {
        log.info("Updating object with id {}", id);
        SportObject updated = sportObjectRepository.findById(id).orElse(null);
        if (updated != null) {
            updated.setName(object.getName());
            updated.setDescription(object.getDescription());
            updated.setCity(object.getCity());
            updated.setDistrict(object.getDistrict());
        }
        return sportObjectRepository.save(updated);
    }
}
