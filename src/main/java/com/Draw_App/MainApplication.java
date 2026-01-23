package com.Draw_App;

import com.Draw_App.controller.GiftsExcelReader;
import com.Draw_App.controller.GiftsWinnersExcelWriter;
import com.Draw_App.controller.ParticipantsExcelReader;
import com.Draw_App.model.entity.Gift;
import com.Draw_App.model.entity.Participant;
import com.Draw_App.service.DrawService;
import com.Draw_App.service.DuplicationParticipantCheck;
import com.Draw_App.service.util.GiftPriceDescComparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class MainApplication {
    public static void main(String[] args) throws IOException {

        ArrayList<Gift> giftsList = GiftsExcelReader.read();

        ArrayList<Participant> participantsList = ParticipantsExcelReader.read();
        DuplicationParticipantCheck duplicationParticipantCheck = new DuplicationParticipantCheck(participantsList);
        ArrayList<Participant> noneDuplicatedParticipantList = duplicationParticipantCheck.run();

        DrawService drawService = new DrawService(noneDuplicatedParticipantList, giftsList);
        ArrayList<Gift> giftsListWithWinners = drawService.drawGifts();

        GiftsWinnersExcelWriter.write(giftsListWithWinners);

        System.out.println("The end! =)");

    }
}
