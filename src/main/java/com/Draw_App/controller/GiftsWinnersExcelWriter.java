package com.Draw_App.controller;

import com.Draw_App.model.entity.Gift;
import com.Draw_App.service.util.PathFinder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

public class GiftsWinnersExcelWriter {
    GiftsWinnersExcelWriter() {
    }

    public static void write(ArrayList<Gift> giftsWithWinners) throws IOException {

        FileOutputStream fos = null;
        Workbook winnersWorkBook = new XSSFWorkbook();
        try {
            final String winnersExcelPath = (new PathFinder()).getDirectoryJarPath() + "/winners.xlsx";
            fos = new FileOutputStream(winnersExcelPath);

            Sheet sheet = winnersWorkBook.createSheet("Winners");

            for (int i = 0; i < giftsWithWinners.size(); i++) {
                Row row = sheet.createRow(i);
                Gift gift = giftsWithWinners.get(i);
                row.createCell(0).setCellValue(gift.getWinnerParticipant().getId());
                row.createCell(1).setCellValue(gift.getGiftName());
            }
            winnersWorkBook.write(fos);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        } finally {
            fos.close();
            winnersWorkBook.close();
        }

    }
}
