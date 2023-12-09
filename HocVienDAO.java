/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EduSys.Poly.Dao;

import EduSys.Poly.Entity.HocVien;
import EduSys.Poly.Entity.KhoaHoc;
import EduSys.Poly.Utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HocVienDAO extends EduSYsDAO<HocVien, String>{
    final String INSERT_SQL = "insert into HocVien (MaKH, MaNH, Diem) values (?,?,?)";
    final String UPDATE_SQL = "update HocVien set MaKH = ?, MaNH = ?, Diem = ? where MaHV = ?";
    final String DELETE_SQL = "delete from HocVien where MaHV = ?";
    final String SELECT_ALL_SQL = "select * from HocVien";
    final String SELECT_BY_ID_SQL = "select * from HocVien where MaHV = ?";
    final String SELECT_BY_KHOA_HOC_SQL = "select * from HocVien where MaKH = ?";

    @Override
    public void insert(HocVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaKH(), entity.getMaNH(), entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaKH(), entity.getMaNH(), entity.getDiem(), entity.getMaHV());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HocVien> selectAll() {
        return selectbySql(SELECT_ALL_SQL);
    }

    @Override
    public HocVien SelectbyID(String id) {
        List<HocVien> list = selectbySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty())
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HocVien> selectbySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next())
            {
                HocVien entity = new HocVien();
                entity.setMaHV(rs.getInt("MaHV"));
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaNH(rs.getString("MaNH"));
                entity.setDiem(rs.getDouble("Diem"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<HocVien> selectByKhoaHoc(int makh){
        return selectbySql(SELECT_BY_KHOA_HOC_SQL, makh);
    }
}
