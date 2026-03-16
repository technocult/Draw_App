package com.Draw_App;

import com.Draw_App.controller.GiftsExcelReader;
import com.Draw_App.controller.GiftsWinnersExcelWriter;
import com.Draw_App.controller.ParticipantsExcelReader;
import com.Draw_App.model.entity.Gift;
import com.Draw_App.model.entity.Participant;
import com.Draw_App.service.DrawService;
import com.Draw_App.service.DuplicationParticipantCheck;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.*;

public class MainApplication {
    public static Logger LOGGER;

    static {
        try (InputStream is = MainApplication.class.getClassLoader().getResourceAsStream("logging.properties")) {
            //try (FileInputStream fis = new FileInputStream(new File("src/main/resources/logging.properties"))) {
            LogManager.getLogManager().readConfiguration(is);
            //LogManager.getLogManager().readConfiguration(fis);
            LOGGER = Logger.getLogger(MainApplication.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        LOGGER.log(Level.INFO, "Программа запущена.");

        init();

        LOGGER.log(Level.INFO, "Программа завершена.");
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
