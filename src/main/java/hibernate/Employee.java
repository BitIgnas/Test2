package hibernate;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private int salary;

}
