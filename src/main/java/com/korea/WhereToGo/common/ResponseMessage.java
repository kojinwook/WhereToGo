package com.korea.WhereToGo.common;

public interface ResponseMessage {
    String SUCCESS = "SUCCESS";
    String VALIDATION_FAIL = "Validation_Fail";
    String DATABASE_ERROR = "Database_Error";
    String DUPLICATE_FESTIVAL = "Duplicate_Festival";
    String NOT_EXISTED_FESTIVAL = "Not_Existed_Festival";
    String NOT_EXISTED_QUESTION = "Not_Existed_Question";
    String DO_NOT_HAVE_PERMISSION = "Do_Not_Have_Permission";
    String NOT_EXISTED_ANSWER = "Not_Existed_Answer";
    String NOT_EXISTED_REVIEW = "Not_Existed_Review";
    String NOT_EXISTED_NOTICE = "Not_Existed_Notice";
    String CERTIFICATION_FAIL = "Certification_Fail";
    String DUPLICATE_ID = "Duplicate_Id";
    String DUPLICATE_EMAIL = "Duplicate_Email";
    String DUPLICATED_NICKNAME = "Duplicated_Nickname";
    String SIGN_IN_FAIL = "Sign_In_Fail";
    String MAIL_FAIL = "Mail_Fail";
    String NOT_EXISTED_USER = "Not_Existed_User";
    String WRONG_PASSWORD = "Wrong_Password";
    String NOT_EXISTED_CHAT_ROOM = "Not_Existed_Chat_Room";
    String NOT_EXISTED_CHAT_MESSAGE = "Not_Existed_Chat_Message";
    String ALREADY_EXIST_CHAT_ROOM = "Already_Exist_Chat_Room";
    String NOT_EXISTED_MEETING = "Not_Existed_Meeting";
    String NOT_EXISTED_FAVORITE = "Not_Existed_Favorite";
    String FALSE_AGREEMENT = "False_Agreement";
    String ALREADY_REQUESTED = "Already_Requested";
    String NOT_EXISTED_JOIN_REQUEST = "Not_Existed_Join_Request";
    String NOT_EXISTED_MEETING_BOARD = "Not_Existed_Meeting_Board";
    String NOT_EXISTED_BOARD_REPLY = "Not_Existed_Board_Reply";
    String NOT_EXISTED_REPLY_REPLY = "Not_Existed_Reply_Reply";
    String NOT_EXISTED_BOARD = "Not_Existed_Board";

    String CANNOT_CREATE_MEETING = "Cannot_Create_Meeting";
}
