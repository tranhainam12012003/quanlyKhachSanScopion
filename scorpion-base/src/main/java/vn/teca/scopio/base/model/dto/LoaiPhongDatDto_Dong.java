package vn.teca.scopio.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class LoaiPhongDatDto_Dong {
    private String tenLoaiPhong;
    private Integer soLuong;
}
