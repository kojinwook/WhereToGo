package com.korea.WhereToGo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat_message")
public class ChatMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private String messageKey;
    private Long roomId;
    private String sender;
    private String message;
    private LocalDateTime timestamp;
    private boolean readByReceiver;
}
