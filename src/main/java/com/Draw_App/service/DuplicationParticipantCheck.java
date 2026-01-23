package com.Draw_App.service;

import com.Draw_App.model.entity.Participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class DuplicationParticipantCheck {

    public DuplicationParticipantCheck(ArrayList<Participant> listFromExcel) {
        this.listFromExcel = listFromExcel;
    }

    private ArrayList<Participant> listFromExcel;

    public ArrayList<Participant> run() {

        HashSet<Participant> setFromExcel = new HashSet<>(listFromExcel);
        ArrayList<Participant> result = new ArrayList<>(setFromExcel);
        Collections.shuffle(result);

        if (listFromExcel.size() > setFromExcel.size()) {
            System.out.println("В исходном листе участников найдено" + (listFromExcel.size() - setFromExcel.size()) + " дубликатов.");
            //тут должно было быть логирование в файл
        }

        return result;
    }

}

