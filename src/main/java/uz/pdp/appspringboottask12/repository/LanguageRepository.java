package uz.pdp.appspringboottask12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appspringboottask12.entity.Language;

import java.util.List;
import java.util.Set;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

   List<Language> findAllByIdIn(Set<Integer> ids);
}
