package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Category;
import com.polytech.sportbook.domain.Owner;
import com.polytech.sportbook.domain.SportObject;
import com.polytech.sportbook.repo.CategoryRepository;
import com.polytech.sportbook.repo.OwnerRepository;
import com.polytech.sportbook.repo.SportObjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SportObjectServiceImplTest {
    private SportObjectService sportObjectService;

    @BeforeEach
    void setup(){
        sportObjectService = new SportObjectServiceImpl(sportObjectRepository, categoryRepository, ownerRepository);
    }

    @Mock
    private SportObjectRepository sportObjectRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @Test
    public void saveObjectTest(){
        SportObject object = new SportObject();
        object.setName("Test object");
        object.setCity("Test city");
        object.setId(1L);
        Owner owner = new Owner();
        owner.setId(1L);
        sportObjectService.saveObject(owner.getId(), object);
        Mockito.verify(sportObjectRepository).save(object);
    }

    @Test
    public void saveCategoryTest() {
        Category category = new Category();
        category.setName("Test category");
        sportObjectService.saveCategory(category);
        Mockito.verify(categoryRepository).save(category);
    }

    @Test
    public void getCategoriesTest() {
        List<Category> categoryList = sportObjectService.categories();
        Mockito.verify(categoryRepository).findAll();
    }

    @Test
    public void getObjectTest() {
        String objectName = "Test object name";
        sportObjectService.getObject(objectName);
        Mockito.verify(sportObjectRepository).findByName(objectName);
    }

    @Test
    public void getObjectByIdTest() {
        Long id = 1L;
        sportObjectService.getObjectById(id);
        Mockito.verify(sportObjectRepository).findById(id);
    }

    @Test
    public void getObjectsTest() {
        sportObjectService.objects();
        Mockito.verify(sportObjectRepository).findAll();
    }

    @Test
    public void deleteObject() {
        sportObjectService.deleteObject(1L);
        Mockito.verify(sportObjectRepository).deleteById(1L);
    }

    @Test
    public void updateObjectTest() {
        SportObject object = new SportObject();
        object.setName("updated name");
        object = sportObjectService.updateObject(1L, object);
        Mockito.verify(sportObjectRepository).findById(1L);
        Mockito.verify(sportObjectRepository).save(object);
    }

}
