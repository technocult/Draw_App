package com.Draw_App.controller;

import com.Draw_App.model.entity.Participant;
import com.Draw_App.service.util.PathFinder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ParticipantsExcelReader {
    ParticipantsExcelReader() {
    }

    //вопрос. я если оборачиваю в трай-кетч блоки он ругается т.к. они не в методе. норм ли что я создаю то что ниже не в методе и как в этом случае оборачивать в трай-кетч? использовать блоки инициализации?
    public static ArrayList<Participant> read() throws IOException {
        ArrayList<Participant> participantsList = new ArrayList<>();
        FileInputStream fis = null; //вопрос. если я хочу и надо закрывать fis в файналли блоке то надо обязательно выносить декларацию переменной из try блока?
        Workbook participantsWorkBook = null;
        try {
            final String participantsExcelPath = (new PathFinder()).getDirectoryJarPath() + "/participants.xlsx";
            fis = new FileInputStream(participantsExcelPath);
            /*вопрос. отпал, вроде заработало =) */

            participantsWorkBook = WorkbookFactory.create(fis);
            Sheet participantsSheet = participantsWorkBook.getSheetAt(0);
            for (Row row : participantsSheet) {
                Cell cell = row.getCell(0);
                switch (cell.getCellType()) {
                    case STRING:
                        participantsList.add(new Participant(cell.getStringCellValue()));
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            throw new RuntimeException("Неверный тип данных в таблице с участниками (дата).");
                        } else {
                            //спорненько. хотел чтобы целочисленные значения остались а дробь выкидывала исключение
                            if (cell.getNumericCellValue() == (int) cell.getNumericCellValue()) {
                                participantsList.add(new Participant(String.valueOf(cell.getNumericCellValue())));
                            } else {
                                throw new RuntimeException("Неверный тип данных в таблице с участниками (дробные числа).");
                            }
                        }
                        break;
                    default:
                        throw new RuntimeException("Неверный тип данных в таблице с участниками (должны быть текстовые или целочисленные значения).");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            fis.close();
            participantsWorkBook.close();
        }
        return participantsList;
    }
}
