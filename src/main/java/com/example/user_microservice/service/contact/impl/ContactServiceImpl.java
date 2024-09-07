package com.example.user_microservice.service.contact.impl;

import com.example.user_microservice.model.contact.Contact;
import com.example.user_microservice.repository.contact.ContactRepository;
import com.example.user_microservice.service.contact.ContactService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContactServiceImpl implements ContactService {

    ContactRepository contactRepository;

    @Override
    @Transactional
    public Contact saveOrUpdate(Contact contact) {
        return contactRepository.save(contact);
    }
}
