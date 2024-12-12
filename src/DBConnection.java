import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Kelas DBConnection untuk menghubungkan aplikasi ke database MySQL.
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_supermarket"; // URL database MySQL
    private static final String USER = "root"; // Username default MySQL
    private static final String PASSWORD = ""; // Password default MySQL (kosong jika tidak diatur)

    /**
     * Membuat koneksi ke database MySQL.
     * @return Connection objek koneksi.
     * @throws SQLException jika koneksi gagal.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
