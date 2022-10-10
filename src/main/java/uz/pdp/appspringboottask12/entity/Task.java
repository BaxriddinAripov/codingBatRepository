package uz.pdp.appspringboottask12.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String solution;

    @Column(nullable = false)
    private String hint;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private boolean answer;

    @Column(nullable = false)
    private boolean hasStar;

    @ManyToOne
    private Language languageId;
}
