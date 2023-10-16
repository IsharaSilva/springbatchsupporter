package com.example.springbatchsupporter.writer;

import java.util.Map;

import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

public class XmlDataWriter<T> extends StaxEventItemWriter<T> {
	
    public XmlDataWriter(@Value("${aliasesMap}") Map<String, String> aliasesMap,
                         @Value("${outputPath}") String outputPath) {
        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setAliases(aliasesMap);
        setMarshaller(marshaller);

        setResource(new FileSystemResource(outputPath));
        setOverwriteOutput(false);
    }
}