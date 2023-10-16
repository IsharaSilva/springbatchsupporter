package writerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;

import com.example.springbatchsupporter.writer.DataItemWriter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.mockito.Mockito.*;

public class DataItemWriterTest {

	private DataItemWriter<Customer, Order> dataItemWriter;
    private Function<Customer, Order> itemWritingMechanism;

    @BeforeEach
    public void setUp() {
        itemWritingMechanism = mock(Function.class);
        dataItemWriter = new DataItemWriter<>(itemWritingMechanism);
    }

    @Test
    public void testWrite() throws Exception {
        Customer customerA = new Customer("John", "Doe");
        Customer customerB = new Customer("Alice", "Smith");
        Customer customerC = new Customer("Bob", "Johnson");

        List<Customer> testData = Arrays.asList(customerA, customerB, customerC);

        Chunk<Customer> chunk = new Chunk<>(testData);
        Order orderA = new Order(1, 100.0);
        Order orderB = new Order(2, 200.0);
        Order orderC = new Order(3, 300.0);

        when(itemWritingMechanism.apply(customerA)).thenReturn(orderA);
        when(itemWritingMechanism.apply(customerB)).thenReturn(orderB);
        when(itemWritingMechanism.apply(customerC)).thenReturn(orderC);
        dataItemWriter.write(chunk);
        verify(itemWritingMechanism).apply(customerA);
        verify(itemWritingMechanism).apply(customerB);
        verify(itemWritingMechanism).apply(customerC);
    }

    static class Customer {
        private String firstName;
        private String lastName;

        public Customer(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    static class Order {
        private int orderId;
        private double totalAmount;

        public Order(int orderId, double totalAmount) {
            this.orderId = orderId;
            this.totalAmount = totalAmount;
        }
    }  
}