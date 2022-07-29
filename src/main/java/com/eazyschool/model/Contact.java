package com.eazyschool.model;

import lombok.Data;

@Data
public class Contact {
    // Note: field names should match with frontend inputs!
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
}
