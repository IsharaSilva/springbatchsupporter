package springbatchsupporter.readerTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.example.springbatchsupporter.reader.CsvReader;

public class CsvReaderTest {

	private static String HEADER="Id,Name\n";
	private static String VALUE="1,John\n";
	private static String CSVFILE="tempCsvFile";
	private static String CSV=".csv";
	private static int LINETOSKIP=1;
	
	private Resource resource;
    @Mock
    private LineMapper<Object> lineMapper;

    private CsvReader<Object> csvReader;

    @BeforeEach
    public void setUp() throws IOException {
        File tempCsvFile = File.createTempFile(CSVFILE, CSV);
        try (FileWriter writer = new FileWriter(tempCsvFile)) {
            writer.write(HEADER);
            writer.write(VALUE);
        }

        resource = new FileSystemResource(tempCsvFile);
        csvReader = Mockito.spy(new CsvReader<>(resource, lineMapper));
        csvReader.setLineMapper(lineMapper);
        csvReader.setLinesToSkip(LINETOSKIP);
        ExecutionContext executionContext = new ExecutionContext();
        csvReader.open(executionContext);
    }

    @Test
    public void testCsvReaderInitialization() {
        Mockito.verify(csvReader).open(Mockito.any(ExecutionContext.class));
        Mockito.verify(csvReader, Mockito.times(0)).setResource(Mockito.any(Resource.class)); 
        Mockito.verify(csvReader).setLineMapper(lineMapper);
        Mockito.verify(csvReader).setLinesToSkip(LINETOSKIP);
    }
}