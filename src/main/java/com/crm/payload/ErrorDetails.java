package com.crm.payload;

import javax.xml.crypto.Data;
import java.util.Date;

public class ErrorDetails {
    private Date date;
    private String Message;
    private String request;


    //Generate Constructor parameters
    public ErrorDetails(Date date, String message, String request) {
        this.date = date;
        Message = message;
        this.request = request;
    }
    //Generate Getters parameters
    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return Message;
    }

    public String getRequest() {
        return request;
    }
}
