package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import vn.teca.scopio.base.model.LoaiPhong;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoaiPhongDatDto implements Serializable {
    private LoaiPhong loaiPhongIdLoaiPhong;
    private Integer soLuong;

}
