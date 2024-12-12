import java.sql.*;

/**
 * Kelas SupermarketCRUD untuk melakukan operasi CRUD pada database MySQL.
 */
public class SupermarketCRUD {

    /**
     * CREATE - Menyimpan transaksi ke database.
     */
    public void insertTransaction(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli, double total, String kasir) {
        String getMaxIdQuery = "SELECT MAX(id) AS max_id FROM transaksi";
        String insertQuery = "INSERT INTO transaksi (id, no_faktur, kode_barang, nama_barang, harga_barang, jumlah_beli, total, kasir) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
    
            // Ambil nilai maksimum dari kolom id
            ResultSet rs = stmt.executeQuery(getMaxIdQuery);
            int newId = 1; // Default ID jika tabel kosong
            if (rs.next() && rs.getInt("max_id") != 0) {
                newId = rs.getInt("max_id") + 1;
            }
    
            // Masukkan data baru dengan ID yang di-generate
            insertStmt.setInt(1, newId);
            insertStmt.setString(2, noFaktur);
            insertStmt.setString(3, kodeBarang);
            insertStmt.setString(4, namaBarang);
            insertStmt.setDouble(5, hargaBarang);
            insertStmt.setInt(6, jumlahBeli);
            insertStmt.setDouble(7, total);
            insertStmt.setString(8, kasir);
    
            insertStmt.executeUpdate();
            System.out.println("Transaksi berhasil disimpan dengan ID: " + newId);
        } catch (SQLException e) {
            System.out.println("Error saat menyimpan transaksi: " + e.getMessage());
        }
    }
    

    /**
     * READ - Menampilkan semua transaksi dari database.
     */
    public void getAllTransactions() {
        String query = "SELECT * FROM transaksi";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Daftar Transaksi:");
            while (rs.next()) {
                // Menampilkan data transaksi.
                System.out.println("ID: " + rs.getInt("id") + ", Faktur: " + rs.getString("no_faktur")
                        + ", Kode Barang: " + rs.getString("kode_barang") + ", Nama Barang: " + rs.getString("nama_barang")
                        + ", Harga: " + rs.getDouble("harga_barang") + ", Jumlah: " + rs.getInt("jumlah_beli")
                        + ", Total: " + rs.getDouble("total") + ", Kasir: " + rs.getString("kasir"));
            }
        } catch (SQLException e) {
            System.out.println("Error saat membaca data: " + e.getMessage());
        }
    }

    /**
     * UPDATE - Memperbarui data transaksi berdasarkan ID.
     */
    public void updateTransaction(int id, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli, double total) {
        String query = "UPDATE transaksi SET kode_barang = ?, nama_barang = ?, harga_barang = ?, jumlah_beli = ?, total = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Mengatur nilai parameter dalam query.
            stmt.setString(1, kodeBarang);
            stmt.setString(2, namaBarang);
            stmt.setDouble(3, hargaBarang);
            stmt.setInt(4, jumlahBeli);
            stmt.setDouble(5, total);
            stmt.setInt(6, id);

            // Menjalankan query.
            stmt.executeUpdate();
            System.out.println("Transaksi berhasil diperbarui.");
        } catch (SQLException e) {
            System.out.println("Error saat memperbarui data: " + e.getMessage());
        }
    }

    /**
     * DELETE - Menghapus transaksi berdasarkan ID.
     */
    public void deleteTransaction(int id) {
        String query = "DELETE FROM transaksi WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Mengatur nilai parameter dalam query.
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Transaksi berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Error saat menghapus data: " + e.getMessage());
        }
    }
}
