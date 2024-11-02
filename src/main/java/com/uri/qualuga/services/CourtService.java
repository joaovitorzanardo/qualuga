package com.uri.qualuga.services;

import com.uri.qualuga.dtos.RegisterCourtDTO;
import com.uri.qualuga.dtos.CourtImageDTO;
import com.uri.qualuga.dtos.SportDTO;
import com.uri.qualuga.dtos.response.CourtResponse;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.entities.Court;
import com.uri.qualuga.entities.CourtImage;
import com.uri.qualuga.entities.Sport;
import com.uri.qualuga.exceptions.CompanyNotFoundException;
import com.uri.qualuga.exceptions.CourtNotFoundException;
import com.uri.qualuga.exceptions.CourtNumberAlreadyExistsException;
import com.uri.qualuga.exceptions.SportNotFoundException;
import com.uri.qualuga.repositories.CompanyRepository;
import com.uri.qualuga.repositories.CourtImageRepository;
import com.uri.qualuga.repositories.CourtRepository;
import com.uri.qualuga.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourtService {

    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CourtImageRepository courtImageRepository;

    public Court saveCourt(RegisterCourtDTO courtDTO) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyRepository.findById(Long.valueOf(jwt.getSubject()))
                .orElseThrow(CompanyNotFoundException::new);

        Optional<Court> courtNumber = courtRepository.findCourtByCompanyAndNumber(company, courtDTO.getNumber());

        if (courtNumber.isPresent()) {
            throw new CourtNumberAlreadyExistsException();
        }

        List<Sport> sports = new ArrayList<>();

        for (SportDTO sportDTO : courtDTO.getSports()) {
            Sport sport = sportRepository.findById(sportDTO.getSportId())
                    .orElseThrow(SportNotFoundException::new);

            sports.add(sport);
        }

        Court court = Court.builder()
                .number(courtDTO.getNumber())
                .company(company)
                .sports(sports).build();

        court =  courtRepository.save(court);

        for (CourtImageDTO courtImageDTO : courtDTO.getImages()) {
            CourtImage courtImage = CourtImage.builder()
                    .court(court)
                    .url(courtImageDTO.getUrl())
                    .build();

            courtImageRepository.save(courtImage);
        }

        return court;
    }

    public Court updateCourt(Long courtId, RegisterCourtDTO courtDTO) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyRepository.findById(Long.valueOf(jwt.getSubject()))
                .orElseThrow(CompanyNotFoundException::new);

        Court court = courtRepository.findById(courtId)
                .orElseThrow(CourtNotFoundException::new);

        if (court.getNumber().equals(courtDTO.getNumber())) {
            Optional<Court> courtNumber = courtRepository.findCourtByCompanyAndNumber(company, courtDTO.getNumber());

            if (courtNumber.isPresent()) {
                throw new CourtNumberAlreadyExistsException();
            }

            court.setNumber(courtDTO.getNumber());
        }

        List<Sport> sports = new ArrayList<>();
        List<CourtImage> images = new ArrayList<>();

        for(CourtImageDTO courtImageDTO : courtDTO.getImages()) {
            CourtImage courtImage = CourtImage.builder()
                    .court(court)
                    .url(courtImageDTO.getUrl())
                    .build();

            images.add(courtImage);
        }

        for (SportDTO sportDTO : courtDTO.getSports()) {
            Sport sport = sportRepository.findById(sportDTO.getSportId())
                    .orElseThrow(SportNotFoundException::new);
            sports.add(sport);
        }

        court.setSports(sports);
        court.setImages(images);

        return courtRepository.save(court);
    }

    public Court deleteCourt(Long courtId) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyRepository.findById(Long.valueOf(jwt.getSubject()))
                .orElseThrow(CompanyNotFoundException::new);

        Court court = courtRepository.findCourtByCompanyAndCourtId(company, courtId)
                .orElseThrow(CourtNotFoundException::new);

        courtRepository.delete(court);

        return court;
    }

    public CourtResponse getCompanyCourtById(Long companyId, Long courtId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);

        return courtRepository.findCourtByCompanyAndCourtId(company, courtId)
                .orElseThrow(CourtNotFoundException::new).toCourtResponse();
    }

    public List<CourtResponse> getCompanyCourts(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);

        return company.getCourts().stream()
                .map(Court::toCourtResponse).toList();
    }

}
