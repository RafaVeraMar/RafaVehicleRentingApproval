package com.babel.vehiclerentingapproval.Security.Repository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findOneByEmail(String email);
}
