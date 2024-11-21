/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasipengelolaankontak;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DatabaseHelper {
    // Metode untuk koneksi ke database SQLite
    public static Connection koneksidb() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:E:\\NETBEANS PROJECT\\AplikasiPengelolaanKontak\\kontak.db"); // Perbaiki URL koneksi
            //JOptionPane.showMessageDialog(null, "Terhubung");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    // Menambahkan kontak baru ke database
    public static void addKontak(String nama, String nomorhp, String kategori) throws SQLException {
        String sql = "INSERT INTO kontak(nama, nomorhp, kategori) VALUES(?, ?, ?)";
        try (Connection conn = koneksidb(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nama);
            pstmt.setString(2, nomorhp);
            pstmt.setString(3, kategori);
            pstmt.executeUpdate();
        }
    }

    // Mendapatkan semua kontak dari database
    public static List<Kontak> getKontak() throws SQLException {
        List<Kontak> kontak = new ArrayList<>();
        String sql = "SELECT * FROM contacts";
        try (Connection conn = koneksidb(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Kontak kontakk = new Kontak(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nomorhp"),
                    rs.getString("kategori")
                );
                kontak.add(kontakk);
            }
        }
        return kontak;
    }

    // Mengupdate data kontak
    public static void updateKontak(int id, String nama, String nomorhp, String kategori) throws SQLException {
        String sql = "UPDATE kontak SET nama = ?, nomorhp = ?, kategori = ? WHERE id = ?";
        try (Connection conn = koneksidb(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nama);
            pstmt.setString(2, nomorhp);
            pstmt.setString(3, kategori);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        }
    }

    // Menghapus kontak dari database
    public static void deleteKontak(int id) throws SQLException {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (Connection conn = koneksidb(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Mencari kontak berdasarkan nama atau nomor telepon
    public static List<Kontak> cariKontak(String keyword) throws SQLException {
        List<Kontak> kontak = new ArrayList<>();
        String sql = "SELECT * FROM kontak WHERE name LIKE ? OR phone LIKE ?";
        try (Connection conn = koneksidb(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Kontak kontakk = new Kontak(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("category")
                );
                kontak.add(kontakk);
            }
        }
        return kontak;
    }
}
