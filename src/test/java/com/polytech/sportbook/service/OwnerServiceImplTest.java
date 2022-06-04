package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Owner;
import com.polytech.sportbook.repo.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class OwnerServiceImplTest {
    private OwnerService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    @BeforeEach
    void setup(){
        ownerService = new OwnerServiceImpl(ownerRepository);
    }

    @Test
    public void saveOwnerTest() {
        Owner owner = new Owner();
        ownerService.saveOwner(owner);
        Mockito.verify(ownerRepository).save(owner);
    }

    @Test
    public void getOwnersTest() {
        ownerService.owners();
        Mockito.verify(ownerRepository).findAll();
    }

    @Test
    public void getOwnerByIdTest() {
        ownerService.getOwnerById(1L);
        Mockito.verify(ownerRepository.findById(1L));
    }

    @Test
    public void updateOwnerTest(){
        Owner owner = new Owner();
        owner.setNickName("updated owner");
        owner = ownerService.updateOwner(1L, owner);
        Mockito.verify(ownerRepository).findById(1L);
        Mockito.verify(ownerRepository).save(owner);
    }

    @Test
    public void deleteOwnerTest(){
        ownerService.deleteOwner(1L);
        Mockito.verify(ownerRepository).deleteById(1L);
    }

}
