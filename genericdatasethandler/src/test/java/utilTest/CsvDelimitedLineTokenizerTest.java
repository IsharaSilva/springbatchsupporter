package utilTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.item.file.transform.FieldSet;

import com.example.springbatchsupporter.utils.CsvDelimitedLineTokenizer;

public class CsvDelimitedLineTokenizerTest {

	private static String HEADER1="Id";
	private static String HEADER2="Name";

    @Test
    public void testCsvDelimitedLineTokenizer() {
    	String[] csvHeaders = {HEADER1, HEADER2};
        CsvDelimitedLineTokenizer tokenizer = new CsvDelimitedLineTokenizer(csvHeaders);
        String sampleCsvLine = "1,John";
        FieldSet fieldSet = tokenizer.tokenize(sampleCsvLine);

        assertEquals("1", fieldSet.readString(HEADER1));
        assertEquals("John", fieldSet.readString(HEADER2));
}
}