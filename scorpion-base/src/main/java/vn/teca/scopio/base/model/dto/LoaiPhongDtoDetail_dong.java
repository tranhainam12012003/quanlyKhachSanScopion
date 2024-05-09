package vn.teca.scopio.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.teca.scopio.base.model.Phong;
import vn.teca.scopio.base.model.TienIch;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoaiPhongDtoDetail_dong {
    private Integer idLoaiPhong;
    private String tenLoaiPhong;
    private String huongNhin;
    private Integer soNguoi;
    private String dienTich;
    private BigDecimal giaTien;
    private String moTa;
    private List<Phong> phongIdPhong;
    private List<TienIch>tienichidtienich;
}
