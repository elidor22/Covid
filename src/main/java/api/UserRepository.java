package api;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Result, Long> {

}