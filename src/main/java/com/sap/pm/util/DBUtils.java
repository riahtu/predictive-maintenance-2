package com.sap.pm.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Contains re-usable DB methods
 * @author I322364
 *
 */
public class DBUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DBUtils.class);
	
	private static DataSource ds;
	private static Connection connection;

    static {
        try {
        	Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup("jdbc/DefaultDB");
        }
        catch (NamingException e) { 
        	LOGGER.error("Naming Exception occured in DBUtils");
        }
    }

    /**
     * Default constructor
     */
	private DBUtils() {
		throw new IllegalAccessError("Utility class");
	}	
	
	/**
	 * method to create a connection to DB
	 * @return DB connection
	 */
	public static Connection getDefaultDBConnection(){
		try{	
			if(connection == null || connection.isClosed())
				connection = ds.getConnection();
			LOGGER.debug("Connection : " + connection);	
		}catch(SQLException e){
			LOGGER.error("DBUtils: failed to get the DB connection ");
			connection = null;
		}
		return connection;
	}
	
	
	
	public static PreparedStatement createPreparedStatement(Connection connection, String query, String parameters, Integer top,
			Integer skip) {
		String dataQuery = query;
		if (top > 0 && skip >= 0) {
			dataQuery += " LIMIT ? OFFSET ?";
		}

		LOGGER.debug("Query : " + dataQuery);
		LOGGER.debug("Parameters : " + parameters);

		PreparedStatement ps = null;
		if (null != connection) {
			try {
				ps = connection.prepareStatement(dataQuery);
				int i = 0;
				if (null != parameters) {
					String params[] = parameters.split(",");
					for (i = 0; i < params.length; i++) {
						setStatementParameter(ps, i + 1, params[i]);
					}
				}
				if (top > 0 && skip >= 0) {
					setStatementParameter(ps, i + 1, top);
					setStatementParameter(ps, i + 2, skip);
				}
				return ps;
			} catch (SQLException e) {
				LOGGER.error("SQLException in createPreparedStatment ");
			}
		} else {
			LOGGER.error("Database Connection Failed in createPreparedStatement");
		}
		return null;
	}
	
	
	/**
	 * Prepares a statement with generic values
	 * @param statement
	 * @param index
	 * @param value
	 * @throws SQLException
	 */
	public static void setStatementParameter(final PreparedStatement statement, final int index, final Object value) throws SQLException {
		try {
			if (value instanceof String) {
				statement.setString(index, (String) value);
			} else if (value instanceof Integer) {
				statement.setInt(index, ((Integer) value).intValue());
			} else if (value instanceof Long) {
				statement.setLong(index, ((Long) value).longValue());
			} else if (value instanceof Float) {
				statement.setFloat(index, ((Float) value).floatValue());
			} else if (value instanceof Double) {
				statement.setDouble(index, ((Double) value).doubleValue());
			} else if (value instanceof java.sql.Date) {
				statement.setDate(index, (java.sql.Date) value);
			} else if (value instanceof Boolean) {
				statement.setBoolean(index, ((Boolean) value).booleanValue());
			} else {
				LOGGER.error("DBUtils: Unsupported parameter type: " + value.getClass());
			}
		} catch (final SQLException e) {
			LOGGER.error("DBUtils: failed to prepare the statement ");
		}
	}
	
	/**
	 * close only ResultSet
	 * @param rs
	 */
	public static void closeFinally(final ResultSet rs) {
		closeFinally(rs, null, null);
	}
	
	/**
	 * Close only Statement
	 * @param s
	 */
	public static void closeFinally(final Statement s) {
		closeFinally(null, s, null);
	}
	
	/**
	 * Close only Connection
	 * @param c
	 */
	public static void closeFinally(final Connection c) {
		closeFinally(null, null, c);
	}
	
	/**
	 * Close both ResultSet Statement
	 * @param rs
	 * @param s
	 */
	public static void closeFinally(final ResultSet rs, final Statement s) {
		closeFinally(rs, s, null);
	}
	
	/**
	 * Close both ResultSet Connection
	 * @param rs
	 * @param c
	 */
	public static void closeFinally(final ResultSet rs, final Connection c) {
		closeFinally(rs, null, c);
	}
	
	/**
	 * Close both Statement Connection
	 * @param s
	 * @param c
	 */
	public static void closeFinally(final Statement s, final Connection c) {
		closeFinally(null, s, c);
	}
	
	/**
	 * Close all ResultSet, Statement and Connection
	 * @param rs
	 * @param s
	 * @param c
	 */
	public static void closeFinally(final ResultSet rs, final Statement s, final Connection c) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				LOGGER.error("ResultSet close : failed ");
			}
		}
		if (s != null) {
			try {
				s.close();
			} catch (SQLException e) {
				LOGGER.error("Statement close : failed ");
			}
		}
		if (c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				LOGGER.error("Connection close : failed ");
			}
		}
	}
}
