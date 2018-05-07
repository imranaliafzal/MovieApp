package com.iafzal.challenge.samsung.movieapp.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * MovieApp
 * <p>
 * Created by iafzal on 5/6/18.
 * Copyright © 2018 Spendlabs Inc. All rights reserved.
 */
@Entity(tableName = "genre",
        indices = {@Index(value = "genre_id")
        })
public class GenreEntity {
    @PrimaryKey
    Integer genre_id;

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer pGenre_id) {
        genre_id = pGenre_id;
    }
}
