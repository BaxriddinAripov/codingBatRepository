package uz.pdp.appspringboottask12.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String text;

    @OneToOne
    private Task task;

    @OneToOne
    private User user;

    @Column(nullable = false)
    private boolean isCorrect;
}
