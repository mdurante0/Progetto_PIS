package DbInterface.command;

import DbInterface.DbConnection;

import java.io.File;

public class WriteOperation implements IDbOperation{

    private DbConnection conn = DbConnection.getInstance();
    private String sql;
    private File file; //per le immagini
    public WriteOperation(String sql) {
        this.sql = sql;
    }
    public WriteOperation(File file, String sql) {
        this.file = file;
        this.sql = sql;
    }

    @Override
    public DbOperationResult execute() {
        //operazioni di write
        DbOperationResult result = new DbOperationResult();

        //result.setType(1);
        if(file == null)
            result.setRowsAffected(conn.executeUpdate(sql));
        else
            result.setRowsAffected(conn.addFoto(file,sql));

        //sempre
        return result;
    }
}
