package vn.teca.scopio.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "loai_phong_dat")
public class LoaiPhongDat {
    @Id
    @Column(name = "id_loai_phong_dat", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "don_dat_id_don_dat", nullable = false)
    private DonDat donDatIdDonDat;

    @NotNull
    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

}