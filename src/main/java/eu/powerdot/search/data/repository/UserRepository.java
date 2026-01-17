
package eu.powerdot.search.data.repository;

import eu.powerdot.search.data.entity.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDbo, Integer> {

    @Query("SELECT u FROM UserDbo u WHERE u.name in :name")
    List<UserDbo> findByName(String... name);

}
