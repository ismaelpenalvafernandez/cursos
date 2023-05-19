package com.softtek.cursos.controlador;

import com.softtek.cursos.excepciones.ExcepcionNoEncontradoModelo;
import com.softtek.cursos.modelo.Curso;
import com.softtek.cursos.servicio.ICursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cursos")
public class ControladorCursos {
    @Autowired
    private ICursoServicio servicio;
    @GetMapping
    public ResponseEntity<List<Curso>> consultarTodos(){
        return new ResponseEntity<>(servicio.consultarTodos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Curso> consultarUno(@PathVariable("id")int id ){
        Curso resultadoBBDD = servicio.consultarUno(id);
        if(resultadoBBDD == null){
            throw new ExcepcionNoEncontradoModelo("ID no encontrado" + id);
        }
        return new ResponseEntity(servicio.consultarUno(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Curso>crear(@RequestBody Curso curso){
        System.out.println(curso.toString());
        return new ResponseEntity<>(servicio.crear(curso), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Curso>modificar(@RequestBody Curso curso){
        Curso resultadoBBDD = servicio.consultarUno(curso.getIdCurso());
        if(resultadoBBDD == null){
            throw new ExcepcionNoEncontradoModelo("ID no encontrado" + curso.getIdCurso());
        }
        return new ResponseEntity<>(servicio.modificar(curso), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id){
        Curso resultadoBBDD = servicio.consultarUno(id);
        if(resultadoBBDD == null){
            throw new ExcepcionNoEncontradoModelo("ID no encontrado" + id);
        }
        servicio.eliminar(id);
    }
}
