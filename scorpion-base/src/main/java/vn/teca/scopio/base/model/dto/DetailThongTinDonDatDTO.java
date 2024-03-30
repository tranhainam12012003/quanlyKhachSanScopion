package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.teca.scopio.base.model.ThongTinKhachDat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailThongTinDonDatDTO {
    private String tenLoaiPhong;
    private int soLuongNguoiO;
    private BigDecimal tongTien;
    private String tenKhachDat;
    private Timestamp thoiGianVao;
    private Timestamp thoiGianRa;
}
