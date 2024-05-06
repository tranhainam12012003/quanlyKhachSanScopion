package vn.teca.scopio.base.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dich_vu_dat")
public class DichVuDat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dich_vu_dat", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dich_vu_id_dich_vu", nullable = false)
    private DichVu dichVuIdDichVu;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "phong_dat_id_phong_dat", nullable = false)
    private PhongDat phongDatIdPhongDat;

    @NotNull
    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @NotNull
    @Column(name = "so_tien", nullable = false, precision = 30, scale = 2)
    private BigDecimal soTien;

}