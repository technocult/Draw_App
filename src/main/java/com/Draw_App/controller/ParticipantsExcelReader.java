package com.Draw_App.controller;

import com.Draw_App.model.entity.Participant;
import com.Draw_App.service.util.PathFinder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static com.Draw_App.MainApplication.LOGGER;

public class ParticipantsExcelReader {
    ParticipantsExcelReader() {
    }

    public static List<Participant> read() {
        LOGGER.log(Level.INFO, "Выполняется метод ParticipantsExcelReader.read()");
        List<Participant> participantsList = new ArrayList<>();
        final String participantsExcelPath = new PathFinder().getDirectoryJarPath() + "/participants.xlsx";

        try (FileInputStream fis = new FileInputStream(participantsExcelPath);
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
                            LOGGER.log(Level.WARNING, "Ошибка при чтении списка участников. Не верный формат данных (дата) в Exel файле.");
                            System.exit(2);
                        } else {
                            try {
                                int intValue = Integer.parseInt(cell.getStringCellValue());
                                participantsList.add(new Participant(String.valueOf(intValue)));
                            } catch (NumberFormatException e) {
                                LOGGER.log(Level.WARNING, "Ошибка при чтении списка участников. Не верный формат данных (дробные числа) в Exel файле.");
                                System.exit(2);
                            }
                        }
                        break;
                    default:
                        LOGGER.log(Level.WARNING, "Ошибка при чтении списка участников. Не верный формат данных (должны быть текстовые или целочисленные значения) в Exel файле.");
                        System.exit(2);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Не удалось создать FileInputStream или Workbook.");
            throw new RuntimeException(e);
        }
        return participantsList;
    }
}
