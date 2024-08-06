package com.korea.WhereToGo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "meeting_request")
@Table(name = "meeting_request")
public class MeetingRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "meeting_id")
    private MeetingEntity meeting;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    private LocalDateTime requestDate;
    private String status; // "PENDING", "ACCEPTED", "REJECTED"
}
