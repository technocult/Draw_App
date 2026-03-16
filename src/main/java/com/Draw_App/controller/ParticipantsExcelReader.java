package com.Draw_App.controller;

import com.Draw_App.model.entity.Participant;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static com.Draw_App.MainApplication.LOGGER;

public class ParticipantsExcelReader {
    ParticipantsExcelReader() {
    }

    public static List<Participant> read() {
        LOGGER.log(Level.INFO, "Выполняется чтение файла participants.xlsx");
        List<Participant> participantsList = new ArrayList<>();
        final String participantsExcelPath = "./participants.xlsx";

        try (FileInputStream fis = new FileInputStream(new File(participantsExcelPath));
             Workbook participantsWorkBook = WorkbookFactory.create(fis)) {

            Sheet participantsSheet = participantsWorkBook.getSheetAt(0);
            for (Row row : participantsSheet) {
                Cell cell = row.getCell(0);
                switch (cell.getCellType()) {
                    case STRING:
                        participantsList.add(new Participant(cell.getStringCellValue()));
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            LOGGER.log(Level.WARNING, "Ошибка при чтении списка участников. Неверный формат данных (дата) в Exel файле.");
                            System.exit(2);
                        } else {
                            try {
                                int intValue = (int)(cell.getNumericCellValue());
                                participantsList.add(new Participant(String.valueOf(intValue)));
                            } catch (NumberFormatException e) {
                                LOGGER.log(Level.WARNING, "Ошибка при чтении списка участников. Неверный формат данных (дробные числа) в Exel файле.");
                                System.exit(2);
                            }
                        }
                        break;
                    default:
                        LOGGER.log(Level.WARNING, "Ошибка при чтении списка участников. Неверный формат данных (должны быть текстовые или целочисленные значения) в Exel файле.");
                        System.exit(2);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Не удалось создать FileInputStream или Workbook.");
            throw new RuntimeException(e);
        }
        LOGGER.log(Level.INFO, "Данные из файла participants.xlsx прочитаны.");
        return participantsList;
    }
}
