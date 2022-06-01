package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Owner;
import com.polytech.sportbook.domain.SportObject;
import com.polytech.sportbook.repo.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService{
    private final OwnerRepository ownerRepository;

    @Override
    public Owner saveOwner(Owner owner) {
        log.info("Saving owner {} to database", owner.getFirstName());
        return ownerRepository.save(owner);
    }

//    @Override
//    public Owner addObject(Long own_id, SportObject object) {
//        log.info("Adding object {} to owner with id {}", object.getName(), own_id);
//        Owner owner = ownerRepository.findById(own_id).orElse(null);
//        if (owner != null) {
//            owner.getObjects().add(object);
//        }
//
//        return ownerRepository.save(owner);
//    }

    @Override
    public List<Owner> owners() {
        log.info("Fetching all owners from database");
        return ownerRepository.findAll();
    }

    @Override
    public Owner getOwnerById(Long id) {
        log.info("Fetching owner with id {}", id);
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner updateOwner(Long id, Owner owner) {
        log.info("Updating owner with id {}", id);
        Owner updated = ownerRepository.findById(id).orElse(null);
        if(updated != null) {
            updated.setNickName(owner.getNickName());
            updated.setEmail(owner.getEmail());
            updated.setPhoneNumber(owner.getPhoneNumber());
            updated.setLastName(owner.getLastName());
            updated.setFirstName(owner.getFirstName());
            updated.setParentName(owner.getParentName());
        }
        return ownerRepository.save(updated);
    }

    @Override
    public void deleteOwner(Long id) {
        log.info("Deleting owner with id {}", id);
        ownerRepository.deleteById(id);
    }
}
