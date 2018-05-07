package com.iafzal.challenge.samsung.movieapp.model;

import com.iafzal.challenge.samsung.movieapp.db.entity.MovieEntity;

import java.util.List;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/6/18.
 * Copyright © 2018 Imran Afzal. All rights reserved.
 */
public class DiscoverResponse {
    Integer page;
    Integer total_results;
    Integer total_pages;
    List<MovieEntity> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer pPage) {
        page = pPage;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer pTotal_results) {
        total_results = pTotal_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer pTotal_pages) {
        total_pages = pTotal_pages;
    }

    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> pResults) {
        results = pResults;
    }
}
