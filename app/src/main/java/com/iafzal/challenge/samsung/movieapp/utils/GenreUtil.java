package com.iafzal.challenge.samsung.movieapp.utils;

import java.util.List;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/7/18.
 * Copyright © 2018 Imran Afzal. All rights reserved.
 */
public class GenreUtil {
    public static String genreNameFromId(Integer id){
        String result;

        switch (id){
            case 28: result = "Action"; break;
            case 12: result = "Adventure"; break;
            case 16: result = "Animation"; break;
            case 35: result = "Comedy"; break;
            case 80: result = "Crime"; break;
            case 99: result = "Documentary"; break;
            case 18: result = "Drama"; break;
            case 10751: result = "Family"; break;
            case 14: result = "Fantasy"; break;
            case 36: result = "History"; break;
            case 27: result = "Horror"; break;
            case 10402: result = "Music"; break;
            case 9648: result = "Mystery"; break;
            case 10749: result = "Romance"; break;
            case 878: result = "Science Fiction"; break;
            case 10770: result = "TV Movie"; break;
            case 53: result = "Thriller"; break;
            case 10752: result = "War"; break;
            case 37: result = "Western"; break;
            default: result = "Unknown";
        }

        return result;
    }

    public static String genreNamesFromIdList(List<Integer> genreIdList){
        String result = "";

        for (int i=0; i < genreIdList.size(); i++){
            result += genreNameFromId(genreIdList.get(i));
            result +=  (i == genreIdList.size()-1)?"":"/";
        }

        return result;
    }
}
