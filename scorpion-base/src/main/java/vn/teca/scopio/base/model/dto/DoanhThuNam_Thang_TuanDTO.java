package vn.teca.scopio.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoanhThuNam_Thang_TuanDTO {
    private Integer nam;
    private Integer thang;
    private Integer tuan;
    private List<DoanhThuDto_dong>doanhThu;
}
