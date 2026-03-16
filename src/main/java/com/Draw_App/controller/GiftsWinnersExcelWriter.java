package com.Draw_App.controller;

import com.Draw_App.model.entity.Gift;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import static com.Draw_App.MainApplication.LOGGER;


public class GiftsWinnersExcelWriter {
    GiftsWinnersExcelWriter() {
    }

    public static void write(List<Gift> giftsWithWinners) {
        LOGGER.log(Level.INFO, "Выполняется запись файла winners.xlsx");
        final String winnersExcelPath = "./winners.xlsx";

        try (FileOutputStream fos = new FileOutputStream(new File(winnersExcelPath));
             Workbook winnersWorkBook = new XSSFWorkbook()) {

            Sheet sheet = winnersWorkBook.createSheet("Winners");

            for (int i = 0; i < giftsWithWinners.size(); i++) {
                Row row = sheet.createRow(i);
                Gift gift = giftsWithWinners.get(i);
                row.createCell(0).setCellValue(gift.getWinnerParticipant().getId());
                row.createCell(1).setCellValue(gift.getGiftName());
            }
            winnersWorkBook.write(fos);
            LOGGER.log(Level.INFO, "Данные успешно записаны в файл winners.xlsx.");
        } catch (
                IOException e) {
            LOGGER.log(Level.WARNING, "Не удалось создать FileOutputStream или произвести запись в файл winners.xlsx.");
            throw new RuntimeException(e);
        }
    }
}
