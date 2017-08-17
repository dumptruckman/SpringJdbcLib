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

/**
 * Connection information for a JDBC connection.
 */
public class ConnectionInfo {

    private final String dbType;
    private final String user;
    private final String pass;
    private final String url;

    /**
     * Creates a new ConnectionInfo object with the given properties.
     *
     * @param dbType the database type. Should be "MySQL", "SQLite", or "H2".
     * @param user the database username.
     * @param pass the database password.
     * @param url the database connection url.
     */
    public ConnectionInfo(String dbType, String user, String pass, String url) {
        this.dbType = dbType;
        this.user = user;
        this.pass = pass;
        this.url = url;
    }

    public String getDbType() {
        return dbType;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getUrl() {
        return url;
    }
}