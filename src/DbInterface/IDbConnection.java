package DbInterface;

import java.io.File;
import java.sql.ResultSet;

public interface IDbConnection {
    ResultSet executeQuery(String sqlStatement);
    int executeUpdate(String sqlStatement);

    int addFoto(File file, String sql);

    void close();
}
