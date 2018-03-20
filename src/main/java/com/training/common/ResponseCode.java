package com.training.common;

public enum ResponseCode {
    SUCCESS ("000", "Success"),
    FAILED ("100", "Failed"),

    LOGIN_SUCCESS ("000", "Login success"),
    LOGIN_REQUEST_EMPTY ("100", "username or password is empty"),
    USER_NOT_EXISTS ("100", "User not found"),
    PROFILE_ID_EMPTY ("100", "Profile Id is empty"),
    EDIT_PROFILE_SUCCESS ("000", "Edit profile success"),

    ADD_PRODUCT_SUCCESS ("000", "Add product success"),
    EDIT_PRODUCT_SUCCESS ("000", "Edit product success"),
    STOCK_EMPTY ("100", "Stock is empty"),
    PRODUCT_ID_EMPTY ("100", "Product Id is empty")
    ,
    ;

    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
