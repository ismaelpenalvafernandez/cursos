package com.softtek.cursos.repositorio;

import com.softtek.cursos.modelo.Curso;
import java.util.List;
public interface ICursoRepo {
    List<Curso> consultarTodos();

    Curso consultarUno(int id);

    Curso crear(Curso curso);

    Curso modificar(Curso curso);

    void eliminar(int id);
}
