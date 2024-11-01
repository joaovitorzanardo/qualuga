package com.uri.qualuga.services;

import com.uri.qualuga.dtos.CompanyDTO;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.entities.Favorite;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.exceptions.CompanyNotFoundException;
import com.uri.qualuga.exceptions.FavoriteAlreadyExistsException;
import com.uri.qualuga.exceptions.FavoriteNotFoundException;
import com.uri.qualuga.exceptions.UserNotFoundException;
import com.uri.qualuga.repositories.CompanyRepository;
import com.uri.qualuga.repositories.FavoriteRepository;
import com.uri.qualuga.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Favorite addCompanyToFavorites(Long companyId) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users loggedUser = usersRepository.findById(Long.valueOf(jwt.getSubject()))
                .orElseThrow(UserNotFoundException::new);

        Company company = companyRepository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);

        Optional<Favorite> favoriteOptional = favoriteRepository.findByUserAndCompany(loggedUser, company);

        if (favoriteOptional.isPresent()) {
            throw new FavoriteAlreadyExistsException();
        }

        Favorite favorite = Favorite.builder()
                .user(loggedUser)
                .company(company).build();

        return favoriteRepository.save(favorite);
    }

    public Favorite removeCompanyFromFavorites(Long companyId) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users loggedUser = usersRepository.findById(Long.valueOf(jwt.getSubject()))
                .orElseThrow(UserNotFoundException::new);

        Company company = companyRepository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);

        Favorite favorite = favoriteRepository.findByUserAndCompany(loggedUser, company)
                .orElseThrow(FavoriteNotFoundException::new);

        favoriteRepository.delete(favorite);

        return favorite;
    }

    public List<CompanyDTO> getFavoriteCompanies() {
        List<CompanyDTO> favoriteCompanies = new ArrayList<>();

        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users loggedUser = usersRepository.findById(Long.valueOf(jwt.getSubject()))
                .orElseThrow(UserNotFoundException::new);

        List<Favorite> favorites = favoriteRepository.findAllByUser(loggedUser);

        for (Favorite favorite : favorites) {
            favoriteCompanies.add(favorite.getCompany().toDTO());
        }

        return favoriteCompanies;
    }

}
