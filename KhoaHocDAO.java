/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EduSys.Poly.Dao;

import EduSys.Poly.Entity.HocVien;
import EduSys.Poly.Entity.KhoaHoc;
import EduSys.Poly.Entity.NguoiHoc;
import EduSys.Poly.Utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhoaHocDAO extends EduSYsDAO<KhoaHoc, String>{
    //final String INSERT_SQL = "insert into KhoaHoc(MaKH,MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV,NgayTao) values (?,?,?,?,?,?,?,?)";
    final String INSERT_SQL = "INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?)";
    //final String UPDATE_SQL = "update KhoaHoc set MaCD = ?, HocPhi = ?, ThoiLuong = ?, NgayKG = ?, GhiChu = ?, MaNV = ?, NgayTao = ? where MaKH = ?";
    final String UPDATE_SQL = "update KhoaHoc set MaCD = ?, HocPhi = ?, ThoiLuong = ?, NgayKG = ?, GhiChu = ?, MaNV = ?, NgayTao = ? where MaKH = ?";
    final String DELETE_SQL = "delete from KhoaHoc where MaKH = ?";
    final String SELECT_ALL_SQL = "select * from KhoaHoc";
    final String SELECT_BY_ID_SQL = "select * from KhoaHoc where MaKH = ?";
    final String SELECT_BY_MA_CD_SQL = "SELECT * FROM KhoaHoc WHERE MaCD=?";

    @Override
    public void insert(KhoaHoc entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getMaKH());
    }

    @Override
    public void update(KhoaHoc entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao(), entity.getMaKH());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return selectbySql(SELECT_ALL_SQL);
    }

    @Override
    public KhoaHoc SelectbyID(String id) {
        List<KhoaHoc> list = selectbySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty())
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhoaHoc> selectbySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next())
            {
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaCD(rs.getString("MaCD"));
                entity.setHocPhi(rs.getFloat("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setNgayKG(rs.getDate("NgayKG"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                list.add(entity);               
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<KhoaHoc> selectKhoaHocByChuyenDe(String maCD) {
        return selectbySql(SELECT_BY_MA_CD_SQL, maCD);
    }
    
    public List<Integer> selectYears() {
        String sql = "SELECT DISTINCT year(NgayKG) Year FROM KhoaHoc ORDER BY Year DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    } 
}
