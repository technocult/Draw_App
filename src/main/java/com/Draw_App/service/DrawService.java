package com.Draw_App.service;

import com.Draw_App.model.entity.Gift;
import com.Draw_App.model.entity.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import static com.Draw_App.MainApplication.LOGGER;

public class DrawService {

    private List<Participant> participants;
    private List<Gift> gifts;

    public DrawService(List<Participant> participants, List<Gift> gifts) {
        this.participants = participants;
        this.gifts = gifts;
    }

    public List<Gift> drawGifts() {
        LOGGER.log(Level.INFO, "Начат розыгрыш подарков среди участников.");
        Random random = new Random();
        List<Gift> giftsWithWinners = new ArrayList<>(gifts);
        for (Gift gift : giftsWithWinners) {
            int winner = random.nextInt(participants.size());
            Participant winnerParticipant = participants.remove(winner);
            gift.setWinnerParticipant(winnerParticipant);
        }
        LOGGER.log(Level.INFO, "Розыгрыш подарков проведён, победители определены!");
        return giftsWithWinners;
    }

}
