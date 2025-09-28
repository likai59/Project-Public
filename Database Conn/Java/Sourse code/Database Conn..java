import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/gtalent2"; // "jdbc:mysql://localhost:3306/[testdb]"
        String user = "root";
        String password = "123456";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            // 1. 載入 JDBC 驅動
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 建立連線
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("=== connected success ===");

            // 3. 建立狀態
            stmt = conn.createStatement();

            // 4. 執行查詢
            rs = stmt.executeQuery(
                    "SELECT * FROM employees " +  // 字串拼接記得最後留空白
                        "ORDER BY hire_date ASC "
                    );

            // 5. 顯示結果
            System.out.println("User : " + user);
            while (rs.next()){
                System.out.printf("ID : %-3d - %-10s, hire_date : %-10s, job : %-10s, salary : %-5d\n",
                        rs.getInt("employee_id") ,
                        rs.getString("first_name"),
                        rs.getDate("hire_date"),
                        rs.getString("job_id"),
                        rs.getInt("salary"));
            }
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if(rs != null){
                try{
                    rs.close();
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

        }
    }
}