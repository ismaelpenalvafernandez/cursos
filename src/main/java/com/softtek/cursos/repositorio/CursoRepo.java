package com.softtek.cursos.repositorio;

import com.softtek.cursos.modelo.Curso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class CursoRepo implements ICursoRepo {
    public List<Curso> cursos = new ArrayList<>();
    @Override
    public List<Curso> consultarTodos() {
        return cursos;
    }

    @Override
    public Curso consultarUno(int id) {
        Curso curso = this.cursos.stream()
                .filter(c -> c.getIdCurso() == id)
                .findFirst()
                .orElse(null);
    return curso;
    }

    @Override
    public Curso crear(Curso curso) {
        cursos.add(curso);
        return curso;
    }

    @Override

    public Curso modificar(Curso curso) {
        Curso cursoAux = null;
        for (Curso c : this.cursos){
            if (c.getIdCurso() == curso.getIdCurso()){
                cursoAux = c;
            }
        }
        this.cursos.set(this.cursos.indexOf(cursoAux),curso);
        return curso;
    }
    @Override
    public void eliminar(int id){ cursos.removeIf(c -> c.getIdCurso() == id);}
}
