/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EduSys.Poly.Dao;

import EduSys.Poly.Entity.NguoiHoc;
import EduSys.Poly.Entity.NhanVien;
import EduSys.Poly.Utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NguoiHocDAO extends EduSYsDAO<NguoiHoc, String>{
    final String INSERT_SQL = "insert into NguoiHoc(MaNH,HoTen,NgaySinh,GioiTinh,DienThoai,Email,GhiChu,MaNV,NgayDK) values (?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "update NguoiHoc set HoTen = ?, NgaySinh = ?, GioiTinh = ?, DienThoai = ?, Email = ?, GhiChu = ?, MaNV = ? where MaNH = ?";
    final String DELETE_SQL = "delete from NguoiHoc where MaNH = ?";
    final String SELECT_ALL_SQL = "select * from NguoiHoc";
    final String SELECT_BY_ID_SQL = "select * from NguoiHoc where MaNH = ?";
    final String SELECT_NOT_IN_COURSE_SQL = "select *from NguoiHoc where HoTen like ? and MaNH not in (select MaNH from HocVien where MaKH = ?)";
    final String SELECT_BY_KEY_WORD_SQL = "select *from NguoiHoc where HoTen like ?";

    @Override
    public void insert(NguoiHoc entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNH(), entity.getHoTen(), entity.getNgaySinh(), entity.isGioiTinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayDK());
    }

    @Override
    public void update(NguoiHoc entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getHoTen(), entity.getNgaySinh(), entity.isGioiTinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getMaNH());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return selectbySql(SELECT_ALL_SQL);
    }

    @Override
    public NguoiHoc SelectbyID(String id) {
        List<NguoiHoc> list = selectbySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty())
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiHoc> selectbySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next())
            {
                NguoiHoc entity = new NguoiHoc();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMaNH(rs.getString("MaNH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setDienThoai(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayDK(rs.getDate("NgayDK"));
                list.add(entity);               
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<NguoiHoc> selectNotInCourse(int makh, String keyword){
        return selectbySql(SELECT_NOT_IN_COURSE_SQL, "%"+ keyword +"%", makh);   
    }
    
    public List<NguoiHoc> selectByKeyword(String keyWord){
        return selectbySql(SELECT_BY_KEY_WORD_SQL, "%" + keyWord + "%");
    }
}
