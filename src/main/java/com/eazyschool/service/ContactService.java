package com.eazyschool.service;

import com.eazyschool.constants.EazyschoolConstants;
import com.eazyschool.controller.ContactController;
import com.eazyschool.model.Contact;
import com.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactService() {
        System.out.println("Contact Service Bean initialized");
    }

  /*    Save contact details into DB
        @param contact
        @return boolean
  */
  public boolean saveMessageDetails(Contact contact) {
    boolean isSaved = true;
    contact.setStatus(EazyschoolConstants.OPEN);
    contact.setCreatedBy(EazyschoolConstants.ANYNOMOUS);
    contact.setCreatedAt(LocalDateTime.now());
    int result = contactRepository.saveContactMsg(contact);

    if (result > 0) {
        isSaved = true;
    }
    return isSaved;
  }
}
