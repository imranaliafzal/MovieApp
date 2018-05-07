package com.iafzal.challenge.samsung.movieapp.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.util.List;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/6/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
@Entity(tableName = "movie")
public class MovieEntity{
    Double popularity;
    Integer vote_count;
    String poset_path;
    @PrimaryKey
    Integer id;
    Boolean adult;
    String backdrop_path;
    String original_language;
    String original_title;
    @Ignore
    List<Integer> genre_ids;
    String title;
    Double vote_average;
    String overview;
    Date release_date;

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double pPopularity) {
        popularity = pPopularity;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer pVote_count) {
        vote_count = pVote_count;
    }

    public String getPoset_path() {
        return poset_path;
    }

    public void setPoset_path(String pPoset_path) {
        poset_path = pPoset_path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer pId) {
        id = pId;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean pAdult) {
        adult = pAdult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String pBackdrop_path) {
        backdrop_path = pBackdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String pOriginal_language) {
        original_language = pOriginal_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String pOriginal_title) {
        original_title = pOriginal_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> pGenre_ids) {
        genre_ids = pGenre_ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double pVote_average) {
        vote_average = pVote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String pOverview) {
        overview = pOverview;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date pRelease_date) {
        release_date = pRelease_date;
    }

}
