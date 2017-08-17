/*
 * This file is part of SpringJdbcLib.
 *
 * Copyright (c) 2017 Jeremy Wood
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.dumptruckman.springjdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.File;

/**
 * This class can be used to initiate a connection to a database and provide {@link JdbcTemplate} objects.
 */
public class SpringJdbcAgent {

    /**
     * Initializes a SpringJdbcAgent, starting a connection to a database based on the given settings.
     *
     * @param connInfo the details of the db connection
     * @param databaseFolder A folder which will contain any embedded database.  This may or may not be used depending
     *                       on the given settings.
     * @param classLoader This is the ClassLoader that will be used to load the database Driver class.
     * @return a new SpringJdbcAgent.
     * @throws ClassNotFoundException if the driver specified in settings is not found.
     */
    public static SpringJdbcAgent createAgent(ConnectionInfo connInfo, File databaseFolder, ClassLoader classLoader)
            throws ClassNotFoundException {
        String dbType = connInfo.getDbType();
        String url = connInfo.getUrl();
        if (dbType.equalsIgnoreCase("H2")) {
            dbType = "org.h2.Driver";
            if (!url.startsWith("jdbc")) {
                url = "jdbc:h2:" + new File(databaseFolder, url).getPath();
            }
        } else if (dbType.equalsIgnoreCase("MySQL")) {
            dbType = "com.mysql.jdbc.Driver";
        } else if (dbType.equalsIgnoreCase("SQLite")) {
            dbType = "org.sqlite.JDBC";
            if (!url.startsWith("jdbc")) {
                url = "jdbc:sqlite:" + new File(databaseFolder, url).getPath();
            }
        }
        ClassLoader previousClassLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbType);
        dataSource.setUrl(url);
        dataSource.setUsername(connInfo.getUser());
        dataSource.setPassword(connInfo.getPass());
        Thread.currentThread().setContextClassLoader(previousClassLoader);
        return new SpringJdbcAgent(connInfo, dataSource);
    }

    private final ConnectionInfo connInfo;
    private final DriverManagerDataSource dataSource;

    private SpringJdbcAgent(ConnectionInfo connInfo, DriverManagerDataSource dataSource) {
        this.connInfo = connInfo;
        this.dataSource = dataSource;
    }

    /**
     * Returns the DataSource managed by this SpringJdbcAgent.
     * @return the DataSource managed by this SpringJdbcAgent.
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Returns the connection info used to create this SpringJdbcAgent.
     *
     * @return the connection info used to create this SpringJdbcAgent.
     */
    public ConnectionInfo getConnectionInfo() {
        return connInfo;
    }

    /**
     * A convenience method for creating JdbcTemplate which allows for very intuitive database access.
     *
     * @return A new {@link JdbcTemplate} build from this SpringJdbcAgent's DataSource.
     */
    public JdbcTemplate createJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}