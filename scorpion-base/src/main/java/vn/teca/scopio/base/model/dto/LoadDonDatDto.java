package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Null;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@JsonIgnoreProperties(ignoreUnknown = true)
public class LoadDonDatDto {
    private Integer idDonDat;
    private Integer idLoaiPhong;
    private String tenLoaiPhong;
    private Integer idPhongDat;

    private Integer idPhong;
    private String tenPhong;
    private String trangThai;


}
