package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Category;
import com.polytech.sportbook.domain.SportObject;

import java.util.List;

public interface SportObjectService {
    SportObject saveObject(Long own_id, SportObject object);

    Category saveCategory(Category category);

    List<Category> categories();

    void addCategoryToObject(String categoryName, String objectName);

    SportObject getObject(String name);

    SportObject getObjectById(Long id);

    List<SportObject> objects();

    void deleteObject(Long id);

    SportObject updateObject(Long id, SportObject object);
}
