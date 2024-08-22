package com.example.user_microservice.exception;

public class DataNotFoundException extends RuntimeException{

//    private Class clazz;
//    private Object value;
//    private Object attribute;

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(Class clazz, Object value, Object attribute) {
        super(
                String.format(
                        "Запись не найдена: %s по %s='%s'",
                        clazz.getSimpleName(), value.toString(), attribute.toString()
                )
        );
//        this.clazz = clazz;
//        this.value = value;
//        this.attribute = attribute;
    }
}
