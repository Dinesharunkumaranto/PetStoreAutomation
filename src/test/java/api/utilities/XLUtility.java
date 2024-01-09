package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {

    private String filePath;
    private Workbook workbook;

    public XLUtility(String filePath) {
        this.filePath = filePath;
    }

    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            return sheet.getPhysicalNumberOfRows();
        }
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            return row.getPhysicalNumberOfCells();
        }
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            return cell.toString();
        }
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            Cell cell = row.createCell(colNum);
            cell.setCellValue(data);
            
            workbook.write(fos);
        }
    }
}

