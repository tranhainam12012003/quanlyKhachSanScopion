package vn.teca.scopio.base.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DTOThongTinLoaiPhong {
    private String loaiPhong;
    private Integer soLuong;
    private Instant thoiGianVao;
    private Instant thoiGianRa;
    private BigDecimal tienPhong;
    private String ten;
    private String sdt;
    private String diaChi;
    private String Email;
    private boolean gioiTinh;
    private String quocTich;

   public DTOThongTinLoaiPhong(LoaiPhongDat loaiPhongDat,DonDat donDat,ThongTinKhachDat thongTinKhachDat) {
      this.loaiPhong=loaiPhongDat.getLoaiPhong().getTenLoaiPhong();
      this.soLuong=loaiPhongDat.getSoLuong();
      this.thoiGianVao=donDat.getThoiGianVao();
      this.thoiGianRa=donDat.getThoiGianRa();
      this.tienPhong=donDat.getTongTien();
      this.ten=thongTinKhachDat.getHoTen();
      this.sdt=thongTinKhachDat.getSoDienThoai();
      this.diaChi=thongTinKhachDat.getDiaChi();
      this.Email=thongTinKhachDat.getEmail();
      this.gioiTinh=thongTinKhachDat.getGioiTinh();
      this.quocTich=thongTinKhachDat.getQuocTich();
   }
}
