package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;


public class FilesParsingTests {

    private ClassLoader cl = FilesParsingTests.class.getClassLoader();

    @Test
    @DisplayName("Проверка содержимого PDF-файла")
    void pdfFileParsingTest() throws Exception {
        boolean isFilePresent = false;

        try(ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("test-files.zip"))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();

                if (fileName.endsWith(".pdf")) {
                    isFilePresent = true;

                    PDF pdf = new PDF(zis);
                    assertTrue((pdf.text).contains("This is a test PDF document"));
                    break;
                }
            }

            assertTrue(isFilePresent, "Файл не найден");
        }
    }

    @Test
    @DisplayName("Проверка содержимого XLS-файла")
    void xlsFileParsingTest() throws Exception {
        boolean isFilePresent = false;

        try(ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("test-files.zip"))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();

                if (fileName.endsWith(".xls")) {
                    isFilePresent = true;

                    XLS xls = new XLS(zis);

                    String excelValue = xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue();
                    System.out.println(excelValue);

                    assertEquals(excelValue, "First Name");
                    break;
                }
            }

            assertTrue(isFilePresent, "Файл не найден");
        }
    }

    @Test
    @DisplayName("Проверка содержимого CSV-файла")
    void csvFileParsingTest() throws Exception {
        boolean isFilePresent = false;

        try(ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("test-files.zip"))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();

                if (fileName.endsWith(".csv")) {
                    isFilePresent = true;

                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));

                    List<String[]> data = csvReader.readAll();

                    assertEquals(3, data.size());
                    assertArrayEquals(
                            new String[]{"name", "age", "city"},
                            data.get(0));
                    assertArrayEquals(
                            new String[]{"Alice", "30", "New York"},
                            data.get(1));
                    assertArrayEquals(
                            new String[]{"Bob", "25", "London"},
                            data.get(2));
                    break;
                }
            }

            assertTrue(isFilePresent, "Файл не найден");
        }
    }
}
