package com.example.demo.exception.custom;

public class ResourceNotFoundException extends  Exception{
   public ResourceNotFoundException(String error)
    {
        super(error);
    }
}
