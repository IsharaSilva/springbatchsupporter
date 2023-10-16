package com.example.springbatchsupporter.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;

public class DBReader<T> extends JdbcCursorItemReader<T> {
	
	public DBReader(DataSource dataSource, Class<T> entityType, String sqlQuery, RowMapper<T> rowMapper) {
        super();
        setDataSource(dataSource);
        setSql(sqlQuery);
        setRowMapper(rowMapper);
        setVerifyCursorPosition(false);
    }

    public static <T> DBReader<T> create(DataSource dataSource, Class<T> entityType, String sqlQuery, RowMapper<T> rowMapper) {
        return new DBReader<>(dataSource,entityType, sqlQuery, rowMapper);
    }
}