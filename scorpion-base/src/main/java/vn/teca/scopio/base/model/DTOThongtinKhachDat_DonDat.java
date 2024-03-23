package vn.teca.scopio.base.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class DTOThongtinKhachDat_DonDat {
    private Integer id;
    private String ten;
    private String sdt;
    private String trangThai;

    public DTOThongtinKhachDat_DonDat(DonDat donDat) {
        this.id = donDat.getId() ;
        this.ten = donDat.getThongTinKhachDatIdKhachDat() != null ? donDat.getThongTinKhachDatIdKhachDat().getHoTen() : null;
        this.sdt = donDat.getThongTinKhachDatIdKhachDat() != null ? donDat.getThongTinKhachDatIdKhachDat().getSoDienThoai() : null;
        this.trangThai = donDat.getTrangThai();
    }

}
