package umc.umc.domain;

import jakarta.persistence.*;
import umc.domain.common.BaseEntity;

public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "VARCHAR(300)")
    private String originFileName;

    @Column(nullable = true, columnDefinition = "VARCHAR(300)")
    private String storeFileName;

    private Integer size;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "letter_id", nullable = false)
    private Letter letter;
}
