package org.project.stockqueen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String menuName;

    @Column
    private Integer price;

    @Column
    private Integer remain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuGroup_id")
    private MenuGroup menuGroup;
}
