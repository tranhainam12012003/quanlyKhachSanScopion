package vn.teca.scopio.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Table(name = "phong")
public class Phong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phong", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "loai_phong_Id_loai_phong", nullable = false)
    private LoaiPhong loaiPhongIdLoaiPhong;

    @Size(max = 10)
    @NotNull
    @Nationalized
    @Column(name = "so_phong", nullable = false, length = 10)
    private String soPhong;

    @Size(max = 10)
    @NotNull
    @Nationalized
    @Column(name = "so_tang", nullable = false, length = 10)
    private String soTang;

    @NotNull
    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai = false;

//    @OneToMany(mappedBy = "phongIdPhong")
//    private Set<PhongDat> phongDats = new LinkedHashSet<>();

}