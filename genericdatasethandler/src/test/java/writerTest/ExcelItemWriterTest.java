package writerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.springbatchsupporter.writer.ExcelItemWriter;

public class ExcelItemWriterTest {

	private static String OUTPUTPATH="output.xlsx";
	private static String TAB="\t";
	private static String FIELD1="Id";
	private static String FIELD2="Name";
	private ExcelItemWriter<String> excelItemWriter;

    @BeforeEach
    public void setUp() {
        excelItemWriter = new ExcelItemWriter<>(OUTPUTPATH, TAB, new String[]{FIELD1, FIELD2});
    }

    @Test
    public void testDelimiterAndFieldNames() {
        assertEquals(OUTPUTPATH, excelItemWriter.getOutputPath());
        assertEquals(TAB, excelItemWriter.getDelimiter());
        String[] expectedFieldNames = new String[]{FIELD1, FIELD2};
        assertArrayEquals(expectedFieldNames, excelItemWriter.getFieldNames());
    }
}