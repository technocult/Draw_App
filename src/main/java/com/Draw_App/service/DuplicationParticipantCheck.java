package com.Draw_App.service;

import com.Draw_App.model.entity.Participant;

import java.util.*;
import java.util.logging.Level;

import static com.Draw_App.MainApplication.LOGGER;

public class DuplicationParticipantCheck {

    public DuplicationParticipantCheck() {
    }

    public static List<Participant> runCheck(List<Participant> listFromExcel) {
        LOGGER.log(Level.INFO, "Выполняется метод DuplicationParticipantCheck.runCheck()");
        Set<Participant> setFromExcel = new HashSet<>(listFromExcel);
        List<Participant> result = new ArrayList<>(setFromExcel);
        Collections.shuffle(result);

        LOGGER.log(Level.INFO, "В исходном листе участников найдено" + (listFromExcel.size() - setFromExcel.size()) + " дубликатов.");

        return result;
    }

}

