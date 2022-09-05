package DbInterface.command;

import DbInterface.DbConnection;

public class WriteOperation implements IDbOperation{

    private DbConnection conn = DbConnection.getInstance();
    private String sql;

    public WriteOperation(String sql) {
        this.sql = sql;
    }

    @Override
    public DbOperationResult execute() {
        //operazioni di write
        DbOperationResult result = new DbOperationResult();

        //result.setType(1);
        result.setRowsAffected(conn.executeUpdate(sql));

        //sempre
        return result;
    }
}
