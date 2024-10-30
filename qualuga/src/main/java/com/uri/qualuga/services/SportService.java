package com.uri.qualuga.services;

import com.uri.qualuga.dtos.SportDTO;
import com.uri.qualuga.entities.Sport;
import com.uri.qualuga.exceptions.SportAlreadyExistsException;
import com.uri.qualuga.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepository;

    public Sport saveSport(Sport sport) {
        Optional<Sport> sportExists = sportRepository.findSportByNameIgnoreCase(sport.getName());

        if (sportExists.isPresent()) {
            throw new SportAlreadyExistsException();
        }

        return sportRepository.save(sport);
    }

    public List<SportDTO> getSports() {
        List<SportDTO> sportsDTOs = new ArrayList<>();
        List<Sport> sports = sportRepository.findAll();

        for (Sport sport : sports) {
            sportsDTOs.add(sport.toDTO());
        }

        return sportsDTOs;
    }

}
