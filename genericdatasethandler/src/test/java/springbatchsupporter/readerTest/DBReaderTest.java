package springbatchsupporter.readerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.springbatchsupporter.reader.DBReader;

@SpringJUnitConfig
public class DBReaderTest {

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String ID1 = "1";
	public static final String FIRST_NAME1 = "John";
	public static final String EMAIL1 = "john@example.com";
	public static final String SQLQUERY = "SELECT * FROM customer";
	public static final int MAXROWS = 1000;
	public static final int FETCHSIZE = 100;
    private DBReader<Customer> dbReader;
    
    @BeforeEach
    public void setUp() {
        DataSource dataSource = createTestDataSource();
        RowMapper<Customer> rowMapper = new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Customer customer = new Customer();
                customer.setId(resultSet.getString(ID));
                customer.setName(resultSet.getString(NAME));
                customer.setEmail(resultSet.getString(EMAIL));
                return customer;
            }
        };
        dbReader = DBReader.create(dataSource, Customer.class, SQLQUERY, rowMapper);
        dbReader.setFetchSize(FETCHSIZE);
        dbReader.setMaxRows(MAXROWS);
    }
    
    @Test
    public void testReadData() throws Exception {
    	dbReader.open(new ExecutionContext());
        Customer customer = dbReader.read(); 
        assertNotNull(customer);

        assertEquals(ID1, customer.getId()); 
        assertEquals(FIRST_NAME1, customer.getName());
        assertEquals(EMAIL1, customer.getEmail());

        dbReader.close();
    }
    
    private DataSource createTestDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.H2);
        DataSource dataSource = builder.build();
        try (java.sql.Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE customer (id INT AUTO_INCREMENT, name VARCHAR(255), email VARCHAR(255))");
            statement.execute("INSERT INTO customer (name, email) VALUES ('John', 'john@example.com')");
            statement.execute("INSERT INTO customer (name, email) VALUES ('Alice', 'alice@example.com')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSource;
    }
    
    public class Customer {
        private String id;
        private String name;
        private String email;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}             
}
}