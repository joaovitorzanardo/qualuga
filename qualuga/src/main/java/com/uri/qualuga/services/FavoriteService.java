package com.uri.qualuga.services;

import com.uri.qualuga.entities.Favorite;
import com.uri.qualuga.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

//    public Favorite addCompanyToFavorites(Long companyId) {
//
//    }


}
