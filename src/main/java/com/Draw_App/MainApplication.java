package com.Draw_App;

import com.Draw_App.controller.GiftsExcelReader;
import com.Draw_App.controller.GiftsWinnersExcelWriter;
import com.Draw_App.controller.ParticipantsExcelReader;
import com.Draw_App.model.entity.Gift;
import com.Draw_App.model.entity.Participant;
import com.Draw_App.service.DrawService;
import com.Draw_App.service.DuplicationParticipantCheck;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MainApplication {
    public static Logger LOGGER;

    static {
        try (FileInputStream fis = new FileInputStream("src/main/resources/logging.properties")) {
            LogManager.getLogManager().readConfiguration(fis);
            LOGGER = Logger.getLogger(MainApplication.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        LOGGER.log(Level.INFO, "Start.");

        init();

        LOGGER.log(Level.INFO, "End.");
    }

    private static void init() throws IOException {
        List<Gift> giftsList = GiftsExcelReader.read();

        List<Participant> participantsList = ParticipantsExcelReader.read();
        List<Participant> noneDuplicatedParticipantList = DuplicationParticipantCheck.runCheck(participantsList);

        DrawService drawService = new DrawService(noneDuplicatedParticipantList, giftsList);
        List<Gift> giftsListWithWinners = drawService.drawGifts();

        GiftsWinnersExcelWriter.write(giftsListWithWinners);
    }
}
