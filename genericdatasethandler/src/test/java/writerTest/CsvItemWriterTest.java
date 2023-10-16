package writerTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;

import com.example.springbatchsupporter.writer.CsvItemWriter;

public class CsvItemWriterTest {

	private static String OUTPUTPATH="output.csv";
	private static String DELIMITER=",";
	private static String DELIMITERNAME="delimiter";
	private static String FIELDNAMES="fieldNames";
	private static String FIELD1="Id";
	private static String FIELD2="Name";
    private CsvItemWriter<String> csvItemWriter;

    @BeforeEach
    public void setUp() {
        csvItemWriter = new CsvItemWriter<>();
        csvItemWriter.setOutputPath(OUTPUTPATH);
        csvItemWriter.setDelimiter(DELIMITER);
        csvItemWriter.setFieldNames(new String[]{FIELD1, FIELD2}); 
    }

    @Test
    public void testDelimiterAndFieldNames() throws Exception {
        Chunk<String> items = new Chunk<>(); 
        Field delimiterField = CsvItemWriter.class.getDeclaredField(DELIMITERNAME);
        delimiterField.setAccessible(true);
        Field fieldNamesField = CsvItemWriter.class.getDeclaredField(FIELDNAMES);
        fieldNamesField.setAccessible(true);
        csvItemWriter.write(items);

        assertEquals(DELIMITER, csvItemWriter.getDelimiter());
        String[] expectedFieldNames = new String[]{FIELD1, FIELD2};
        assertArrayEquals(expectedFieldNames, csvItemWriter.getFieldNames());
    }
}