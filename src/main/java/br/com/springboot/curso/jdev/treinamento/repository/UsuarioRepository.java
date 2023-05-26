/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.springboot.curso.jdev.treinamento.repository;

import br.com.springboot.curso.jdev.treinamento.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cesarcosta
 */@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
     @Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
     List<Usuario>buscarPorNome(String name);
}
