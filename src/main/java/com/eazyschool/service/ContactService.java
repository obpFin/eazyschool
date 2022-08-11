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
import java.util.List;
import java.util.Optional;

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
    boolean isSaved = false;
    contact.setStatus(EazyschoolConstants.OPEN);
    contact.setCreatedBy(EazyschoolConstants.ANYNOMOUS);
    contact.setCreatedAt(LocalDateTime.now());
    Contact SavedContact = contactRepository.save(contact);

    if (SavedContact != null && SavedContact.getContactId() > 0) {
        isSaved = true;
    }
    return isSaved;
  }

  public List<Contact> findMsgsWithOpenStatus() {
      List<Contact> contactMsgs = contactRepository.findByStatus(EazyschoolConstants.OPEN);
      return contactMsgs;
  }

    public boolean updateMsgStatus(int contactId, String updatedBy) {
        boolean isUpdated = false;

        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EazyschoolConstants.CLOSE);
            contact1.setUpdatedBy(updatedBy);
            contact1.setUpdatedAt(LocalDateTime.now());
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if (updatedContact != null && updatedContact.getUpdatedBy() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
