package body;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class Database {

	private static String mJdbcUrl = "jdbc:oracle:thin:@camburi.pucrs.br:1521:facin11g";
	private static String mUser = "be180123";
	private static String mPassword = "be180123";
	private static OracleDataSource mDataSource = null;

	public static Connection getConnection() throws SQLException {
		if (mDataSource == null) {
			initialize();
		}
		return mDataSource.getConnection(mUser, mPassword);
	}

	public static void initialize() throws SQLException {
		mDataSource = new OracleDataSource();
		mDataSource.setURL(mJdbcUrl);
	}
}
