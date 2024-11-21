/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasipengelolaankontak;

/**
 *
 * @author User
 */
public class Kontak {
  private int id;
    private String nama;
    private String nomor_telepon;
    private String kategori;

    // Konstruktor
    public Kontak(int id, String nama, String nomorhp, String kategori) {
        this.id = id;
        this.nama = nama;
        this.nomor_telepon = nomorhp;
        this.kategori = kategori;
    }

    // Getter dan Setter
    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getNomorHp() { return nomor_telepon; }
    public String getKategori() { return kategori; } 
}
