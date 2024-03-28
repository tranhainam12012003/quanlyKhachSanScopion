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
@Table(name = "loai_dich_vu")
public class LoaiDichVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loai_dich_vu", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Column(name = "ten_loai_dich_vu", nullable = false, length = 200)
    private String tenLoaiDichVu;

}