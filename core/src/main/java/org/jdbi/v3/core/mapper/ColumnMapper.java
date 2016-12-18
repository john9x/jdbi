/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jdbi.v3.core.mapper;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.core.config.ConfigRegistry;

/**
 * Maps result set columns to objects.
 * @see ConfigRegistry#findColumnMapperFor(Type)
 * @see StatementContext#findColumnMapperFor(Type)
 * @see ColumnMappers#findFor(java.lang.reflect.Type, ConfigRegistry)
 */
@FunctionalInterface
public interface ColumnMapper<T>
{
    /**
     * Map the given column of the current row of the result set to an Object. This method should not cause the result
     * set to advance, allow jDBI to do that, please.
     *
     * @param r the result set being iterated
     * @param columnNumber the column number to map (starts at 1)
     * @param ctx the statement context
     * @return the value to return for this column
     * @throws SQLException if anything goes wrong go ahead and let this percolate, jDBI will handle it
     */
    T map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException;

    /**
     * Map the given column of the current row of the result set to an Object. This method should not cause the result
     * set to advance, allow jDBI to do that, please.
     *
     * @param r the result set being iterated
     * @param columnLabel the column label to map
     * @param ctx the statement context
     * @return the value to return for this column
     * @throws SQLException if anything goes wrong go ahead and let this percolate, jDBI will handle it
     */
    default T map(ResultSet r, String columnLabel, StatementContext ctx) throws SQLException {
        return map(r, r.findColumn(columnLabel), ctx);
    }
}
