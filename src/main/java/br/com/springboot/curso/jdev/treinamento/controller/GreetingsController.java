/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.springboot.curso.jdev.treinamento.controller;
import br.com.springboot.curso.jdev.treinamento.model.Usuario;
import br.com.springboot.curso.jdev.treinamento.repository.UsuarioRepository;
import java.util.List;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class GreetingsController { 
@Autowired //injeçao de dependencia
private UsuarioRepository usuarioRepository;
    
    
@RequestMapping(value="/mostrarnome/{name}", method=RequestMethod.GET)
@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name){
		return "Curso Spring Boot API: "+ name + " ! "; 
		//passando a variável direto na url
	}
@RequestMapping(value="/olamundo/{nome}", method=RequestMethod.GET)
@ResponseStatus(HttpStatus.OK)
public String retornaOlaMundo(@PathVariable String nome) {
	
    Usuario usuario=new Usuario();
    usuario.setNome(nome);
    usuarioRepository.save(usuario);//grava no banco de dados
    return "Olá Mundo ! "+ nome;
} 
@GetMapping(value = "listartodos")//primeiro método de API
@ResponseBody//retorna os dados para o corpo da resposta
public ResponseEntity<List<Usuario>> listarUsuario(){
   
    List<Usuario> usuarios=usuarioRepository.findAll();
    //executa a consulta no banco de dados
    return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
//retorna a lista em JSON
} 

@PostMapping(value = "salvar")//mapeia a Url 
@ResponseBody//descrição da resposta 
public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario ){ 
    //requestbody recebe os dados para salvar
Usuario user=usuarioRepository.save(usuario);

return new ResponseEntity<Usuario>(user,HttpStatus.CREATED);
}


@DeleteMapping(value = "delete")//mapeia a Url 
@ResponseBody//descrição da resposta 
public ResponseEntity<String> delete(@RequestParam Long iduser ){ 
    //requestbody recebe os dados para salvar
usuarioRepository.deleteById(iduser);

return new ResponseEntity<String>(" User deletado com sucesso! ",HttpStatus.OK);
}

@GetMapping(value = "buscaruserid") 
@ResponseBody//descrição da resposta 
public ResponseEntity<Usuario> buscaruserid(@RequestParam(name="iduser") Long iduser ){ 
    //requestbody recebe os dados para salvar
Usuario usuario=usuarioRepository.findById(iduser).get();

return new ResponseEntity<Usuario>(usuario ,HttpStatus.OK);
}

@PutMapping(value = "atualizar")//mapeia a Url 
@ResponseBody//descrição da resposta 
public ResponseEntity<?> atualizar(@RequestBody Usuario usuario ){ 
    

    if(usuario.getId()==null){
    return new ResponseEntity<String>("Id não foi informado para a atualização! ",HttpStatus.OK);
    }
    
//requestbody recebe os dados para salvar
Usuario user=usuarioRepository.saveAndFlush(usuario);

return new ResponseEntity<Usuario>(user,HttpStatus.OK);
}

@GetMapping(value = "buscarPorNome") 
@ResponseBody//descrição da resposta 
public ResponseEntity<List<Usuario>> buscarPorNome( @RequestParam(name="name") String name ){ 
    //requestbody recebe os dados para salvar
List<Usuario> usuario=usuarioRepository.buscarPorNome(name.trim().toUpperCase());

return new ResponseEntity<List<Usuario>>(usuario ,HttpStatus.OK);
}


}
