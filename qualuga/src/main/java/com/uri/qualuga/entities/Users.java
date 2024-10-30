package com.uri.qualuga.entities;

import com.uri.qualuga.dtos.UserAccountDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Users implements Account {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence",
            sequenceName = "user_sequence",
            initialValue = 1,
            allocationSize = 1)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    public UserAccountDTO toDTO() {
        return UserAccountDTO.builder()
                .userId(userId)
                .name(name)
                .email(email).build();
    }


}
