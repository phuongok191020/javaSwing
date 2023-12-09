/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EduSys.Poly.Dao;

import EduSys.Poly.Entity.NhanVien;
import EduSys.Poly.Utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienDAO extends EduSYsDAO<NhanVien, String>{
    final String INSERT_SQL = "insert into NhanVien(MaNV,MatKhau,HoTen,VaiTro) values (?,?,?,?)";
    final String UPDATE_SQL = "update NhanVien set MatKhau = ?, HoTen = ?, VaiTro = ? where MaNV = ?";
    final String DELETE_SQL = "delete from NhanVien where MaNV = ?";
    final String SELECT_ALL_SQL = "select * from NhanVien";
    final String SELECT_BY_ID_SQL = "select * from NhanVien where MaNV = ?";
    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNV(), entity.getMaKhau(), entity.getHoTen(), entity.isVaiTro());
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaKhau(), entity.getHoTen(), entity.isVaiTro(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectbySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien SelectbyID(String id) {
        List<NhanVien> list = selectbySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty())
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectbySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next())
            {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setMaKhau(rs.getString("MatKhau"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
