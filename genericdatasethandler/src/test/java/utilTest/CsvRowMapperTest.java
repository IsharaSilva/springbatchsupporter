package utilTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.AbstractLineTokenizer;

import com.example.springbatchsupporter.utils.CsvRowMapper;
import java.lang.reflect.Field;

public class CsvRowMapperTest {

	private static String DELIMITEDTOKENIZER="delimitedLineTokenizer";
	private static String FIELDMAPPER="fieldSetMapper";
	
	@Test
    public void testCsvRowMapper() throws NoSuchFieldException, IllegalAccessException {
        AbstractLineTokenizer lineTokenizer = mock(AbstractLineTokenizer.class);
        FieldSetMapper<Object> fieldSetMapper = mock(FieldSetMapper.class);

        CsvRowMapper<Object> csvRowMapper = new CsvRowMapper<>(lineTokenizer, fieldSetMapper);
        Field lineTokenizerField = CsvRowMapper.class.getDeclaredField(DELIMITEDTOKENIZER);
        Field fieldSetMapperField = CsvRowMapper.class.getDeclaredField(FIELDMAPPER);

        lineTokenizerField.setAccessible(true);
        fieldSetMapperField.setAccessible(true);

        assertEquals(lineTokenizer, lineTokenizerField.get(csvRowMapper));
        assertEquals(fieldSetMapper, fieldSetMapperField.get(csvRowMapper));
    }
}