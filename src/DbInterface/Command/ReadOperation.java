package DbInterface.command;

import DbInterface.DbConnection;

public class ReadOperation implements IDbOperation {

    private DbConnection conn = DbConnection.getInstance();
    private String sql;

    public ReadOperation(String sql) {
        this.sql = sql;
    }

    @Override
    public DbOperationResult execute() {

        DbOperationResult result = new DbOperationResult();
        //result.setType(0);
        result.setResultSet(conn.executeQuery(sql));

        return result;
    }
}
