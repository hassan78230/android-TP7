package com.example.tp7;

import java.io.Serializable;

public class Answer implements Serializable {


    private String actorName;

    private boolean isActive;

    public Answer(String actorName) {
        this.actorName = actorName;
    }

    public Answer(String actorName,  boolean isActive) {
        this.actorName = actorName;
        this.isActive = isActive;

    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Override
    public String toString() {
        return actorName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }




}
