package com.silva.anna.Controle;

import com.silva.anna.Model.Aluno;
import com.silva.anna.Servico.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoControle {
    private final AlunoService service;

    public AlunoControle(AlunoService service) {
        this.service = service;
    }
    @GetMapping
    public List<Aluno> buscarAlunos(){
        return service.listaDeAluno();
    }

    @PostMapping
    public Aluno salvarAluno(@RequestBody Aluno novoAluno){
        return service.criarAluno(novoAluno);
    }

    @DeleteMapping("/{id}")
    public void excluirAluno(@PathVariable long id){
        service.deletarAluno(id);
    }

    @PutMapping("/{id}")
    public Aluno buscaAlunoPOrID(@PathVariable Long id,@RequestBody Aluno alteraAluno){
        Aluno atualizado = service.buscarAlunoId(id);
        if (atualizado == null) return null;

        atualizado.setNome(alteraAluno.getNome());
        atualizado.setEmail(alteraAluno.getEmail());
        atualizado.setTelefone(alteraAluno.getTelefone());

     return service.criarAluno(alteraAluno);
    }
}
