package com.eazyschool.controller;

import com.eazyschool.model.Contact;
import com.eazyschool.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;

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
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

  @PostMapping(value = "/saveMsg")
  public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
    if (errors.hasErrors()) {
      log.error("Contact form validation failed due to: " + errors.toString());
      return "contact.html";
    }

    contactService.saveMessageDetails(contact);

    return "redirect:/contact";
  }
}
