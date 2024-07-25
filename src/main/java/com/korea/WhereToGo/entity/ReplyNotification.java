package com.korea.WhereToGo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyNotification {
    private Long postId; // 게시물 ID
    private Long authorId; // 게시물 작성자 ID
    private String replySender; // 답글 발신자
    private String replyContent; // 답글 내용
}
