package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class SelenideFilesTest {

    static {
        Configuration.fileDownload = FileDownloadMode.PROXY;
    }

    @Test
    void downloadFileTest() throws IOException {
        open();
        File downloaded = $("").download();

        try(InputStream file = new FileInputStream(downloaded)) {
            byte[] data = file.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);
            assertTrue(dataAsString.contains("Some text"));
        }
    }

    @Test
    void uploadFileTest() {
        open();
        $("input[type='file']").uploadFromClasspath();
    }


}
