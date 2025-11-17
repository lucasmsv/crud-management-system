package model;

import java.time.LocalDate;

public class Usuario {
    private int matricula;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    public Usuario() {
        this.matricula = 0;
        this.nome = "";
        this.cpf = "";
        this.dataNascimento = null;
    }

    public Usuario(int _matricula, String _nome, String _cpf, LocalDate _dataNascimento) {
        this.matricula = _matricula;
        this.nome = _nome;
        this.cpf = _cpf;
        this.dataNascimento = _dataNascimento;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Matrícula: " + this.nome +
               "Nome.....: " + this.nome +
               "CPF......: " + this.cpf +
               "Data Nasc: " + this.dataNascimento;
    }
}
