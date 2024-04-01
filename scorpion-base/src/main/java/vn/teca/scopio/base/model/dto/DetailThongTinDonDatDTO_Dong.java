package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailThongTinDonDatDTO_Dong {
    private Integer idPhongDat;
    private String tenLoaiPhong;
    private int soLuongNguoiO;
    private BigDecimal tongTien;
    private List<KhachoDTO_Dong> khachO;
    private Timestamp thoiGianVao;
    private Timestamp thoiGianRa;
    private String soPhong;
}
