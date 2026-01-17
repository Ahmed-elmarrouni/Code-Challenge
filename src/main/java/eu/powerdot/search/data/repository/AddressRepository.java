
package eu.powerdot.search.data.repository;

import eu.powerdot.search.data.entity.AddressDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressDbo, Integer> {

}
