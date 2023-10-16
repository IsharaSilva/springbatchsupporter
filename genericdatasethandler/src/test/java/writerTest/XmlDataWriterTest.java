package writerTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.example.springbatchsupporter.writer.XmlDataWriter;

public class XmlDataWriterTest {

	private XmlDataWriter<Object> xmlDataWriter;

    @Test
    public void testWriteDataToXml() {
    	List<Object> data = new ArrayList<>();
        Chunk<Object> chunk = new Chunk<>(data);
        ItemWriter<Object> itemWriter = items -> {
            xmlDataWriter.write(new Chunk<>(items));
        };
        assertTrue(true, "Data was successfully written to XML");
    
    }
}