package com.korea.WhereToGo.common;

public interface ResponseMessage {
    String SUCCESS = "SUCCESS";
    String VALIDATION_FAIL = "Validation_Fail";
    String DATABASE_ERROR = "Database_Error";
    String DUPLICATE_FESTIVAL = "Duplicate_Festival";
    String NOT_EXISTED_FESTIVAL = "Not_Existed_Festival";
    String DO_NOT_HAVE_PERMISSION = "Do_Not_Have_Permission";
    String CERTIFICATION_FAIL = "Certification_Fail";
    String DUPLICATE_ID = "Duplicate_Id";
    String DUPLICATE_EMAIL = "Duplicate_Email";
    String DUPLICATED_NICKNAME = "Duplicated_Nickname";
    String SIGN_IN_FAIL = "Sign_In_Fail";
    String MAIL_FAIL = "Mail_Fail";
    String NOT_EXISTED_USER = "Not_Existed_User";
    String WRONG_PASSWORD = "Wrong_Password";
}
