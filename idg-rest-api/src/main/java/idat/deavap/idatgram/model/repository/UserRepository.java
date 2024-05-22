package idat.deavap.idatgram.model.repository;

import idat.deavap.idatgram.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
    
    @Query(value = "{call usp_users_get_all}", nativeQuery = true)
    Optional<List<User>> getAll();
    
    @Query(value = "{call usp_users_get_by_id(:id)}", nativeQuery = true)
    Optional<User> getById(@Param("id") int id);
    
    @Query(value = "{call usp_users_get_by_email(:email)}", nativeQuery = true)
    Optional<User> getByEmail(@Param("email") String email);
    
    @Transactional
    @Procedure("usp_users_add")
    void add(
        @Param("email") String email, 
        @Param("name") String name, 
        @Param("last_name") String last_name
    );
    
    @Transactional
    @Procedure("usp_users_upd")
    void upd(
        @Param("id") int id,
        @Param("email") String email, 
        @Param("name") String name, 
        @Param("last_name") String last_name
    );
    
    @Transactional
    @Procedure("usp_users_del")
    void del(@Param("id") int id);
}
