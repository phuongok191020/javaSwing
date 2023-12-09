/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EduSys.Poly.Entity;

/**
 *
 * @author Admin
 */
public class NhanVien {
    private String maNV;
    private String maKhau;
    private String hoTen;
    private boolean vaiTro;

    public NhanVien() {
    }

    public NhanVien(String maNV, String maKhau, String hoTen, boolean vaiTro) {
        this.maNV = maNV;
        this.maKhau = maKhau;
        this.hoTen = hoTen;
        this.vaiTro = vaiTro;
    }
    
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKhau() {
        return maKhau;
    }

    public void setMaKhau(String maKhau) {
        this.maKhau = maKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }
    
}
