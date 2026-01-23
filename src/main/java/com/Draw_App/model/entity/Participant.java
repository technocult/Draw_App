package com.Draw_App.model.entity;

import java.util.Objects;

public class Participant {

    private String id;

    public Participant() {
    }
    public Participant(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participant participant = (Participant) o;
        return Objects.equals(id, participant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
