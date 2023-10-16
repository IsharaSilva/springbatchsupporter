package processorTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.springbatchsupporter.processor.DataItemProcessor;

public class DataItemProcessorTest {
	
	private static Integer INPUT=42;
	private static String PROCESSEDDATA="Processed: ";
	private static String EXPECTEDPROCESSEDDATA="Processed: 42";

    @Test
    public void testProcess() throws Exception {
        DataItemProcessor<Integer, String> itemProcessor = new DataItemProcessor<>(input -> PROCESSEDDATA + input);
        String processedData = itemProcessor.process(INPUT);
        assertEquals(EXPECTEDPROCESSEDDATA, processedData);
    }
}