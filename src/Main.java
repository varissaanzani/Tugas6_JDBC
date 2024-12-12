import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SupermarketCRUD crud = new SupermarketCRUD();

        System.out.println("Selamat Datang di SAS Market");
        System.out.println("Pilih Menu:");
        System.out.println("1. Tambah Transaksi");
        System.out.println("2. Tampilkan Semua Transaksi");
        System.out.println("3. Perbarui Transaksi");
        System.out.println("4. Hapus Transaksi");
        System.out.println("5. Keluar");

        boolean running = true;

        while (running) {
            System.out.print("\nPilihan Anda: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Tambah Transaksi
                    System.out.print("No. Faktur: ");
                    String noFaktur = scanner.nextLine();
                    System.out.print("Kode Barang: ");
                    String kodeBarang = scanner.nextLine();
                    System.out.print("Nama Barang: ");
                    String namaBarang = scanner.nextLine();
                    System.out.print("Harga Barang: ");
                    double hargaBarang = scanner.nextDouble();
                    System.out.print("Jumlah Beli: ");
                    int jumlahBeli = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    double total = hargaBarang * jumlahBeli;
                    System.out.print("Kasir: ");
                    String kasir = scanner.nextLine();

                    crud.insertTransaction(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli, total, kasir);
                    break;

                case 2: // Tampilkan Semua Transaksi
                    crud.getAllTransactions();
                    break;

                case 3: // Perbarui Transaksi
                    System.out.print("ID Transaksi yang akan diperbarui: ");
                    int idUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Kode Barang Baru: ");
                    String kodeBarangUpdate = scanner.nextLine();
                    System.out.print("Nama Barang Baru: ");
                    String namaBarangUpdate = scanner.nextLine();
                    System.out.print("Harga Barang Baru: ");
                    double hargaBarangUpdate = scanner.nextDouble();
                    System.out.print("Jumlah Beli Baru: ");
                    int jumlahBeliUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    double totalUpdate = hargaBarangUpdate * jumlahBeliUpdate;

                    crud.updateTransaction(idUpdate, kodeBarangUpdate, namaBarangUpdate, hargaBarangUpdate, jumlahBeliUpdate, totalUpdate);
                    break;

                case 4: // Hapus Transaksi
                    System.out.print("ID Transaksi yang akan dihapus: ");
                    int idDelete = scanner.nextInt();
                    crud.deleteTransaction(idDelete);
                    break;

                case 5: // Keluar
                    System.out.println("Terima kasih telah menggunakan sistem kami.");
                    running = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        scanner.close();
    }
}
