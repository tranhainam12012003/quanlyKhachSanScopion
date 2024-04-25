package vn.teca.scopio.base.model.dto;

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
public class DetailThongTinPhongDatDTO_Dong {
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
    private List<KhachoDTO_Dong> khachO;
    private List<DichVuDatDTO_dong>dichVuDat;
    private BigDecimal tongTienDichVu;
}
