package com.softtek.cursos.servicio;

import com.softtek.cursos.modelo.Curso;
import com.softtek.cursos.repositorio.ICursoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CursoServicio implements ICursoServicio{
    @Autowired
    private ICursoRepo repo;

    @Override
    public List<Curso> consultarTodos() {return repo.consultarTodos();}
    @Override
    public Curso consultarUno(int id) {return repo.consultarUno(id);}
    @Override
    public Curso crear(Curso curso) {return repo.crear(curso);}
    @Override
    public Curso modificar(Curso curso) {return repo.modificar(curso);}
    @Override
    public void eliminar(int id) {repo.eliminar(id);}

}
