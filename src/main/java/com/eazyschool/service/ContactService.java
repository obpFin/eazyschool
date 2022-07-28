package com.eazyschool.service;

import com.eazyschool.controller.ContactController;
import com.eazyschool.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    /*    Save contact details into DB
          @param contact
          @return boolean
    */
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        // TODO: need to persist the data into the DB table
        log.info(contact.toString());
        return  isSaved;
    }

}