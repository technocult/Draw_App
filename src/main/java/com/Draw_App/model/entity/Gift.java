package com.Draw_App.model.entity;

import java.util.Objects;

public class Gift {
    private String giftName;
    private int giftPrice;
    private Participant winnerParticipant;

    public Gift() {
    }

    public Gift(String giftName, int giftPrice, Participant winnerParticipant) {
        this.giftName = giftName;
        this.giftPrice = giftPrice;
        this.winnerParticipant = winnerParticipant;
    }

    public Gift(String giftName, int giftPrice) {
        this(giftName, giftPrice, null);
    }

    @Override
    public String toString() {
        return "Gift{" +
                "giftName='" + giftName + '\'' +
                ", giftPrice=" + giftPrice +
                ", winnerUser=" + winnerParticipant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Gift gift = (Gift) o;
        return giftPrice == gift.giftPrice && Objects.equals(giftName, gift.giftName) && Objects.equals(winnerParticipant, gift.winnerParticipant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giftName, giftPrice, winnerParticipant);
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public int getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(int giftPrice) {
        this.giftPrice = giftPrice;
    }

    public Participant getWinnerParticipant() {
        return winnerParticipant;
    }

    public void setWinnerParticipant(Participant winnerParticipant) {
        this.winnerParticipant = winnerParticipant;
    }
}
