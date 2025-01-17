package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.teca.scopio.base.model.PhongDat;

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
    private Integer idDonDat;
    private Integer idPhong;
    private Integer idLoaiPhongDat;
    private BigDecimal soTienPhong;
    private Timestamp thoiGianVao;
    private Timestamp thoiGianRa;
    private String trangThai;
    private BigDecimal tienLoaiPhong;
    private String tenLoaiPhong;
    private String tenPhong;
    private Integer idLoaiPhong;
   private List<KhachoDTO_Dong>khachO;

}
