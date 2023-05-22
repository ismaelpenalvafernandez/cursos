    package com.softtek.cursos.controlador;

    import com.softtek.cursos.excepciones.ExcepcionNoEncontradoModelo;
    import com.softtek.cursos.modelo.Curso;
    import com.softtek.cursos.servicio.ICursoServicio;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.hateoas.EntityModel;
    import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

    import java.net.URI;
    import java.util.List;

    import static org.springframework.hateoas.server.core.WebHandler.linkTo;
    import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        @GetMapping("/hateos/{id}")
        public EntityModel<Curso> consultarUnoH(@PathVariable("id") int id) throws Exception {
            Curso resultadoBBDD = servicio.consultarUno(id);
            if (resultadoBBDD == null) {
                throw new ExcepcionNoEncontradoModelo("Id no encontrado" + id);
            }
            WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).consultarUno(id));
            return EntityModel.of(resultadoBBDD).add(link1.withRel("paciente-link"));
        }
        @PostMapping
        public ResponseEntity<Curso>crear(@RequestBody Curso curso)throws Exception{
            Curso obj = servicio.crear(curso);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCurso()).toUri();
            return ResponseEntity.created(location).build();
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
