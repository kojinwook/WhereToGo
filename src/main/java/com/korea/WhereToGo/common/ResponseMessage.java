package com.korea.WhereToGo.common;

public interface ResponseMessage {
    String SUCCESS = "SUCCESS";
    String VALIDATION_FAIL = "Validation_Fail";
    String DATABASE_ERROR = "Database_Error";
    String DUPLICATE_FESTIVAL = "Duplicate_Festival";
    String NOT_EXISTED_FESTIVAL = "Not_Existed_Festival";
    String DO_NOT_HAVE_PERMISSION = "Do_Not_Have_Permission";
    String NOT_EXISTED_REVIEW = "Not_Existed_Review";
}
