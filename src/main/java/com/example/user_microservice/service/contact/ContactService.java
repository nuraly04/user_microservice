package com.example.user_microservice.service.contact;

import com.example.user_microservice.model.contact.Contact;

public interface ContactService {

    Contact saveOrUpdate(Contact contact);
}
