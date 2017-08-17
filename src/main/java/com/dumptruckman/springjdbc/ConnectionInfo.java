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

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * Connection information for a JDBC connection.
 */
public class ConnectionInfo implements ConfigurationSerializable {

    private final String dbType;
    private final String user;
    private final String pass;
    private final String url;

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new HashMap<>();
        result.put("type", dbType);
        result.put("username", user);
        result.put("password", pass);
        result.put("url", url);
        return result;
    }

    public static ConnectionInfo deserialize(Map<String, Object> serialized) {
        return new ConnectionInfo(serialized.getOrDefault("type", "H2").toString(),
                serialized.getOrDefault("username", "root").toString(),
                serialized.getOrDefault("password", "").toString(),
                serialized.getOrDefault("url", "jdbc:h2:file:h2.db").toString());
    }

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