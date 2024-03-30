package vn.teca.scopio.base.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "thong_tin_khach_o")
public class ThongTinKhachO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_khach_o")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phong_dat_id_phong_dat")
    private PhongDat phongDatIdPhongDat;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "ho_ten")
    private String hoTen;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "quoc_tich")
    private String quocTich;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "ten_giay_to")
    private String tenGiayTo;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "so_giay_to")
    private String soGiayTo;

}