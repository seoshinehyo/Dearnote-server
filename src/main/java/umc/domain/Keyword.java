package umc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import umc.domain.common.BaseEntity;

public class Keyword extends BaseEntity {
    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String keyword;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "letter_id", nullable = false)
    private Letter letter;
}
