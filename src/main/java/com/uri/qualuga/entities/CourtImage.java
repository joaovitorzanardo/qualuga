package com.uri.qualuga.entities;

import com.uri.qualuga.dtos.CourtImageDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "court_image")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CourtImage {

    @Id
    @Column(name = "court_image_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "court_image_sequence")
    @SequenceGenerator(name = "court_image_sequence",
            sequenceName = "court_image_sequence",
            initialValue = 1,
            allocationSize = 1)
    private Long courtImageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id", nullable = false)
    private Court court;

    @Column(name = "url", nullable = false)
    private String url;

    public CourtImageDTO toDTO() {
        return CourtImageDTO.builder()
                .courtImageId(courtImageId)
                .url(url).build();
    }

}
