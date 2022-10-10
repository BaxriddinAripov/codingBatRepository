package uz.pdp.appspringboottask12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appspringboottask12.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
