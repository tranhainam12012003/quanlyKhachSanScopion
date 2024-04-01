package vn.teca.scopio.base.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PhongChuaGan_DTO_Dong {
    private Integer idDonDat;
    private int soLuongChuaGan;
    private List<PhongDaGanDTO_Dong> phongDaGanDTODongs;
}
