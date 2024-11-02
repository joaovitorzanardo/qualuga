package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.request.SearchRequest;
import com.uri.qualuga.dtos.response.SearchResponse;
import com.uri.qualuga.services.SearchService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SearchResponse> search(@Valid @RequestBody SearchRequest searchRequest) {
        SearchResponse searchResponse = searchService.search(searchRequest);
        return ResponseEntity.ok(searchResponse);
    }


}
