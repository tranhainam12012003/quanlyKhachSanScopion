package vn.teca.scopio.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DTOThongTinLoaiPhong {
    private String loaiPhong;
    private Integer soLuong;
    private Timestamp thoiGianVao;
    private Timestamp thoiGianRa;
    private BigDecimal tienPhong;
    private String ten;
    private String sdt;
    private String diaChi;
    private String Email;
    private boolean gioiTinh;
    private String quocTich;
    private Date ngaySinh;
}
