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
    int result = contactRepository.saveContactMsg(contact);

    if (result > 0) {
        isSaved = true;
    }
    return isSaved;
  }

  public List<Contact> findMsgsWithOpenStatus() {
      List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(EazyschoolConstants.OPEN);
      return contactMsgs;
  }

    public boolean updateMsgStatus(int contactId, String updatedBy) {
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId, EazyschoolConstants.CLOSE, updatedBy);
        if (result > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
