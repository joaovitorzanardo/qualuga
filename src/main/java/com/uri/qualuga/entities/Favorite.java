package com.uri.qualuga.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorite")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Favorite {

    @Id
    @Column(name = "favorite_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_sequence")
    @SequenceGenerator(name = "favorite_sequence",
            sequenceName = "favorite_sequence",
            initialValue = 1,
            allocationSize = 1)
    private Long favoriteId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

}
