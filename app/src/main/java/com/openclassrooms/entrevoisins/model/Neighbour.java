package com.openclassrooms.entrevoisins.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS_ADD;

/**
 * Model object representing a Neighbour
 */
public class Neighbour implements Parcelable {

    /**
     * Identifier
     */
    private Integer id;

    /**
     * Full name
     */
    private String name;

    /**
     * Avatar
     */
    private String avatarUrl;

    /**
     * Favorite or not
     */
    private boolean isFavorite;


    public Neighbour(Integer id, String name, String avatarUrl, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.isFavorite = isFavorite;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }


    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        this.isFavorite = favorite;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public static final Parcelable.Creator<Neighbour> CREATOR = new Parcelable.Creator<Neighbour>() {
        public Neighbour createFromParcel(Parcel in) {
            return new Neighbour(in);
        }

        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }
    };

    private Neighbour(Parcel in) {
        id = in.readInt();
        name = in.readString();
        isFavorite = in.readByte() != 0;
        avatarUrl = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeByte((byte) (isFavorite ? 1 : 0));
        parcel.writeString(avatarUrl);
    }

}

