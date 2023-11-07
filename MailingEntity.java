package com.example.skyeng.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "mailing")
public class MailingEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "type")
    String typeEnum;

    @Column(name = "recipient_index")
    String recipientIndex;

    @Column(name = "recipient_address")
    String recipientAddress;

    @Column(name = "recipient_name")
    String recipientName;

    @ManyToOne
    @JoinColumn(name = "post_office")
    PostOfficeEntity postOfficeEntity;

    @Column(name = "received")
    @NotNull
    Boolean is_received;

    @ManyToMany
    @JoinTable(
            name = "mail_movement",
            joinColumns = @JoinColumn(name = "mailing_id"),
            inverseJoinColumns = @JoinColumn(name = "post_office_id")
    )
    private List<PostOfficeEntity> movementHistory = new ArrayList<>();
}
