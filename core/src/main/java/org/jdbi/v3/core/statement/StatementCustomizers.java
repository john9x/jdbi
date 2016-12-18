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
package org.jdbi.v3.core.statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class StatementCustomizers
{
    private StatementCustomizers()
    {
    }

    /**
     * Sets the fetch direction on a query. Can be used as a Statement customizer or a SqlStatementCustomizer.
     */
    public static class FetchDirectionStatementCustomizer implements StatementCustomizer
    {
        private final Integer direction;

        public FetchDirectionStatementCustomizer(final Integer direction)
        {
            this.direction = direction;
        }

        @Override
        public void beforeExecution(final PreparedStatement stmt, final StatementContext ctx) throws SQLException
        {
            stmt.setFetchDirection(direction);
        }

        public void apply(SqlStatement<?> q) throws SQLException
        {
            q.setFetchDirection(direction);
        }
    }

    public static final class QueryTimeoutCustomizer implements StatementCustomizer
    {
        private final int seconds;

        public QueryTimeoutCustomizer(final int seconds)
        {
            this.seconds = seconds;
        }

        @Override
        public void beforeExecution(final PreparedStatement stmt, final StatementContext ctx) throws SQLException
        {
            stmt.setQueryTimeout(seconds);
        }
    }

    public static final class FetchSizeCustomizer implements StatementCustomizer
    {
        private final int fetchSize;

        public FetchSizeCustomizer(final int fetchSize)
        {
            this.fetchSize = fetchSize;
        }

        @Override
        public void beforeExecution(final PreparedStatement stmt, final StatementContext ctx) throws SQLException
        {
            stmt.setFetchSize(fetchSize);
        }
    }

    public static final class MaxRowsCustomizer implements StatementCustomizer
    {
        private final int maxRows;

        public MaxRowsCustomizer(final int maxRows)
        {
            this.maxRows = maxRows;
        }

        @Override
        public void beforeExecution(final PreparedStatement stmt, final StatementContext ctx) throws SQLException
        {
            stmt.setMaxRows(maxRows);
        }
    }

    public static final class MaxFieldSizeCustomizer implements StatementCustomizer
    {
        private final int maxFieldSize;

        public MaxFieldSizeCustomizer(final int maxFieldSize)
        {
            this.maxFieldSize = maxFieldSize;
        }

        @Override
        public void beforeExecution(final PreparedStatement stmt, final StatementContext ctx) throws SQLException
        {
            stmt.setMaxFieldSize(maxFieldSize);
        }
    }
}
