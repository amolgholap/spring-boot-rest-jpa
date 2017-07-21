package src.main.java.com.ag.restboot.dao.copymanager;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CopyManagerImpl {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
    public String importCSVToTable(InputStream inputStream, String fileName, String userSSO) {
                    String response ="Success";
                    Connection con = null;
                    try {
                                    con = DriverManager.getConnection("Connection Url","UserName","Password");//
                                    con = jdbcTemplate.getDataSource().getConnection();//
                                    CopyManager copyManager = new CopyManager((BaseConnection) con);
                                    StringBuilder sql = new StringBuilder();
                                    sql.append("COPY ");
                                    sql.append("fms_import_table(concatenate,line_id,p_flow_or_no_flow)");
                                    sql.append(" FROM STDIN WITH (");
                                    sql.append(" FORMAT CSV ");
                                    sql.append(", DELIMITER ','");
                                    sql.append(", NULL ''");
                                    sql.append(", HEADER TRUE");
                                    sql.append(", QUOTE '\"'");
                                    sql.append(", ESCAPE '\"' ");
                                    sql.append(", ENCODING 'UTF8'");                
                                    sql.append(")");
                                    
                                    copyManager.copyIn(sql.toString(), inputStream );
                                    
                    }catch(Exception e){
                                    response = "Fail";
                    } finally{
                                    if (con != null) {
                                                    try {
                                                                    con.close();
                                                    } catch (Exception e) {
                                                    }
                                    }
                    }
                    return response;
    }

}
