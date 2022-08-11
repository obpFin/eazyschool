package com.eazyschool.service;

import com.eazyschool.constants.EazyschoolConstants;
import com.eazyschool.model.Contact;
import com.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean updateMsgStatus(int contactId) {
        boolean isUpdated = false;

        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EazyschoolConstants.CLOSE);
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if (updatedContact != null && updatedContact.getUpdatedBy() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
