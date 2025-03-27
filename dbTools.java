import java.math.BigDecimal;
import java.sql.*;

/**
 * @author chake
 */
public class dbTools {
    private static final String URL = ConfigLoader.getDbUrl();
    private static final String USER = ConfigLoader.getDbUser();
    private static final String PASSWORD = ConfigLoader.getDbPassword();
    public static void selectDetail() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM detail")) {

            while (rs.next()) {
                System.out.println(
                                "\t收支:" + rs.getString("income_or_expenditures") +
                                "\t账户金额:" + rs.getBigDecimal("amount") +
                                "\t说明:" + rs.getString("comment")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertDetail(String incomeOrExpenditure, BigDecimal amount, String comment) {
        String sql = "INSERT INTO detail (income_or_expenditures, amount, comment) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, incomeOrExpenditure);
            pstmt.setBigDecimal(2, amount);
            pstmt.setString(3, comment);

            int affectedRows = pstmt.executeUpdate();
            System.out.println("成功插入 " + affectedRows + " 条记录");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        selectDetail();
        insertDetail("收入", BigDecimal.valueOf(5000.00), "工资到账");

    }
}