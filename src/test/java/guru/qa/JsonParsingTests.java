package guru.qa;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.Student;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonParsingTests {

    @Test
    void jsonFileParsingTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        File file = new File("src/test/resources/test.json");
        Student student = mapper.readValue(file, Student.class);

        assertEquals("John", student.getFirstName());
        assertEquals(student.getCourses().get(0).getTitle(), "Introduction to Programming");
    }
}
