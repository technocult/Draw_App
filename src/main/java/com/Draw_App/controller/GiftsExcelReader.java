package com.Draw_App.controller;

import com.Draw_App.model.entity.Gift;
//import com.Draw_App.service.util.PathFinder;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static com.Draw_App.MainApplication.LOGGER;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

public class GiftsExcelReader {
    GiftsExcelReader() {
    }

    public static List<Gift> read() {
        LOGGER.log(Level.INFO, "Выполняется метод GiftsExcelReader.read()");
        List<Gift> giftsList = new ArrayList<>();
//        final String giftsExcelPath = Paths.get(new PathFinder().getDirectoryJarPath(), "/gifts.xlsx").toString();
//        final String giftsExcelPath = "C:/Users/Aleksandr/IdeaProjects/Draw_App/target/gifts.xlsx";
        final String giftsExcelPath = "./gifts.xlsx";

        File file = new File(giftsExcelPath);
        try (FileInputStream fis = new FileInputStream(file);
             Workbook giftsWorkBook = WorkbookFactory.create(fis)) {

            Sheet giftsSheet = giftsWorkBook.getSheetAt(0);
            for (Row row : giftsSheet) {

                Cell cellGiftName = row.getCell(0);
                Cell cellGiftNumber = row.getCell(1);
                Cell cellGiftPrice = row.getCell(2);
                if (cellGiftName.getCellType() == STRING &&
                        cellGiftNumber.getCellType() == NUMERIC &&
                        cellGiftPrice.getCellType() == NUMERIC) {

                    int giftNumber = (int) cellGiftNumber.getNumericCellValue();
                    if (giftNumber > 0) {
                        Gift gift = new Gift();
                        gift.setGiftName(cellGiftName.getStringCellValue());
                        gift.setGiftPrice((int) cellGiftPrice.getNumericCellValue());
                        for (int i = 0; i < giftNumber; i++) {
                            giftsList.add(gift);
                        }
                    }
                } else {
                    LOGGER.log(Level.INFO, "Неверный тип данных в таблице с подарками либо подарков ноль.");
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Не удалось создать FileInputStream или Workbook.");
            throw new RuntimeException(e);
        }

        giftsList.sort((g1, g2) -> g2.getGiftPrice() - g1.getGiftPrice());
        LOGGER.log(Level.INFO, "Прочитан и отсортирован в порядке убывания список подарков. Количество подарков: " + giftsList.size() + ".");

        return giftsList;
    }
}
