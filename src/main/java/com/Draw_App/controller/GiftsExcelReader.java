package com.Draw_App.controller;

import com.Draw_App.model.entity.Gift;
import com.Draw_App.service.util.GiftPriceDescComparator;
import com.Draw_App.service.util.PathFinder;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

public class GiftsExcelReader {
    GiftsExcelReader() {
    }

    public static ArrayList<Gift> read() throws IOException {
        ArrayList<Gift> giftsList = new ArrayList<>();
        FileInputStream fis = null;
        Workbook giftsWorkBook = null;
        try {
            final String giftsExcelPath = (new PathFinder()).getDirectoryJarPath() + "/gifts.xlsx";
            fis = new FileInputStream(giftsExcelPath);

            giftsWorkBook = WorkbookFactory.create(fis);
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
                    throw new RuntimeException("Неверный тип данных в таблице с подарками либо подарков ноль.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            fis.close();
            giftsWorkBook.close();
        }

        GiftPriceDescComparator comparator = new GiftPriceDescComparator();
        giftsList.sort(comparator);

        return giftsList;
    }
}
