package com.ngelmat.simpletodo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class TodoItem implements Parcelable {

    private UUID id;
    private String item;

    public TodoItem(String item) {
        this.item = item;
    }

    public TodoItem(UUID id, String item) {
        this.id = id;
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.id);
        dest.writeString(this.item);
    }

    protected TodoItem(Parcel in) {
        this.id = (UUID) in.readSerializable();
        this.item = in.readString();
    }

    public static final Parcelable.Creator<TodoItem> CREATOR = new Parcelable.Creator<TodoItem>() {
        @Override
        public TodoItem createFromParcel(Parcel source) {
            return new TodoItem(source);
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };
}
