package com.Draw_App.controller;

import com.Draw_App.model.entity.Gift;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
        LOGGER.log(Level.INFO, "Выполняется чтение файла gifts.xlsx.");
        List<Gift> giftsList = new ArrayList<>();
        final String giftsExcelPath = "./gifts.xlsx";

        try (FileInputStream fis = new FileInputStream(new File(giftsExcelPath));
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
                        for (int i = 0; i < giftNumber; i++) {
                            giftsList.add(new Gift(cellGiftName.getStringCellValue(), (int) cellGiftPrice.getNumericCellValue()));
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
        LOGGER.log(Level.INFO, "Данные из файла gifts.xlsx прочитаны и отсортированы в порядке убывания стоимости список подарков. Количество подарков: " + giftsList.size() + ".");

        return giftsList;

    }
}
