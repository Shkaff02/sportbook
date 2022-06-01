package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Owner;
import com.polytech.sportbook.domain.SportObject;

import java.util.List;

public interface OwnerService {
    Owner saveOwner(Owner owner);

//    Owner addObject(Long own_id, SportObject object);

    List<Owner> owners();

    Owner getOwnerById(Long id);

    Owner updateOwner(Long id, Owner owner);

    void deleteOwner(Long id);
}
