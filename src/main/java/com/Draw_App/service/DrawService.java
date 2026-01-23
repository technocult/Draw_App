package com.Draw_App.service;

import com.Draw_App.model.entity.Gift;
import com.Draw_App.model.entity.Participant;
import com.Draw_App.service.util.GiftPriceDescComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class DrawService {

    ArrayList<Participant> participants;
    ArrayList<Gift> gifts;

    public DrawService(ArrayList<Participant> participants, ArrayList<Gift> gifts) {
        this.participants = participants;
        this.gifts = gifts;
    }

    public ArrayList<Gift> drawGifts() {
        Random random = new Random();
        ArrayList<Gift> giftsWithWinners = new ArrayList<>(gifts);
        for (Gift gift : giftsWithWinners) {
            int winner = random.nextInt(participants.size());
            Participant winnerParticipant = participants.remove(winner);
            gift.setWinnerParticipant(winnerParticipant);
        }
        return giftsWithWinners;
    }

}
