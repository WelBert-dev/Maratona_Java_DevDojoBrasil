package ZA_estruturaDados.models;

import java.util.Objects;

public class PatientModel implements Comparable {

    private String nome;
    private int priority; // quantom mais baixo mais prioritário ou seja 1 é a maior prioridade.

    public PatientModel() {}

    public PatientModel(String nome, int priority) {
        this.nome = nome;
        this.priority = priority;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Object o) {
        // obj1 > obj2 return > 0; (1)
        // obj1 < obj2 return < 0; (-1)
        // obj1 == obj2 return 0;
        if (this.priority > ((PatientModel)o).getPriority()) {
            return 1;

        } else if (this.priority < ((PatientModel)o).getPriority()) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientModel that = (PatientModel) o;
        return priority == that.priority && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, priority);
    }

    @Override
    public String toString() {
        return "PatientModel{" +
                "nome='" + nome + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
