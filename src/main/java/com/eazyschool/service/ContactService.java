package com.eazyschool.service;

import com.eazyschool.controller.ContactController;
import com.eazyschool.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@Service
// @RequestScope
// @SessionScope
public class ContactService {

    private int counter = 0;

    public ContactService() {
        System.out.println("Contact Service Bean initialized");
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

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
