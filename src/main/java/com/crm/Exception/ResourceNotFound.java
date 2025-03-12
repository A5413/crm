package com.crm.Exception;


public class ResourceNotFound extends RuntimeException{
    //child class of Exception
    //super key -it calling runtime exception
    public ResourceNotFound(String message) {
        super(message);
    }
}
