package writerTest;

import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.example.springbatchsupporter.writer.DataItemCompositeWriter;

public class DataItemCompositeWriterTest {

	private static String FIELD1="Pdf";
	private static String FIELD2="Excel";
	
	@Mock
    private ItemWriter<String> writer1;

    @Mock
    private ItemWriter<String> writer2;
    
    @Test
    public void testDelegateItemWriters(){
            List<ItemWriter<? super String>> itemWriters = Arrays.asList(writer1, writer2);

            DataItemCompositeWriter<String> compositeWriter = new DataItemCompositeWriter<>(itemWriters);
            compositeWriter.setDelegates(itemWriters);

            List<String> items = Arrays.asList(FIELD1, FIELD2);
            Chunk<String> chunk = new Chunk<>(items);

            try {
                compositeWriter.write(chunk);

                verify(writer1).write(chunk);
                verify(writer2).write(chunk);
            } catch (Exception e) {
            }
        }
}
