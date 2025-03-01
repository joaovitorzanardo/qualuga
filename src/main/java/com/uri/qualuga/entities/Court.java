package com.uri.qualuga.entities;

import com.uri.qualuga.dtos.RegisterCourtDTO;
import com.uri.qualuga.dtos.CourtImageDTO;
import com.uri.qualuga.dtos.SportDTO;
import com.uri.qualuga.dtos.response.CourtResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "court")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Court {

    @Id
    @Column(name = "court_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "court_sequence")
    @SequenceGenerator(name = "court_sequence",
            sequenceName = "court_sequence",
            initialValue = 1,
            allocationSize = 1)
    private Long courtId;

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL)
    private List<CourtImage> images;

    @ManyToMany
    @JoinTable(
            name = "court_sport",
            joinColumns = @JoinColumn(name = "court_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id"))
    private List<Sport> sports;

    public CourtResponse toCourtResponse() {
        List<CourtImageDTO> courtImageDTOs = new ArrayList<>();
        List<SportDTO> sportDTOs = new ArrayList<>();

        for (CourtImage courtImage : images) {
            courtImageDTOs.add(courtImage.toDTO());
        }

        for (Sport sport : sports) {
            sportDTOs.add(sport.toDTO());
        }

        return CourtResponse.builder()
                .courtId(courtId)
                .number(number)
                .images(courtImageDTOs)
                .sports(sportDTOs).build();
    }


}
