package com.eazyschool.controller;

import com.eazyschool.model.Contact;
import com.eazyschool.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/contact")
    public String displayHContactPage(Model model) {
        return "contact.html";
    }

  /*    @PostMapping(value = "/saveMsg")
  public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
                                  @RequestParam String email, @RequestParam String subject, @RequestParam String message) {
      log.info("Name: " + name);
      log.info("Mobile: " + mobileNum);
      log.info("Email: " + email);
      log.info("Subject: " + subject);
      log.info("Message: " + message);
      return new ModelAndView("redirect:/contact");
  }*/
      @PostMapping(value = "/saveMsg")
      public ModelAndView saveMessage(Contact contact) {
          contactService.saveMessageDetails(contact);
          return new ModelAndView("redirect:/contact");
      }
}
