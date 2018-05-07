package com.iafzal.challenge.samsung.movieapp.db.entity;

import android.arch.persistence.room.ColumnInfo;
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
        }
)

public class GenreEntity {
    @PrimaryKey
    @ColumnInfo(name = "genre_id")
    Integer id;
    String name;


    public Integer getGenre_id() {
        return id;
    }

    public void setGenre_id(Integer pGenre_id) {
        id = pGenre_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }
}
