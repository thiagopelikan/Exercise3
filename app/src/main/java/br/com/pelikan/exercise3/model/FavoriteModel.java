package br.com.pelikan.exercise3.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteModel implements Parcelable {

    private final String url;
    private final String name;

    public FavoriteModel(String url, String name) {
        this.url = url;
        this.name = name;
    }

    protected FavoriteModel(Parcel in) {
        url = in.readString();
        name = in.readString();
    }

    public static final Creator<FavoriteModel> CREATOR = new Creator<FavoriteModel>() {
        @Override
        public FavoriteModel createFromParcel(Parcel in) {
            return new FavoriteModel(in);
        }

        @Override
        public FavoriteModel[] newArray(int size) {
            return new FavoriteModel[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(name);
    }
}