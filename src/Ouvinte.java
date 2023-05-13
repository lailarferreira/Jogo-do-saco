import java.util.ArrayList;
import java.util.List;
public class Ouvinte {
    private int id;
    private String nome;
    private int idade;
    private int jogadas;
    private int vitorias;

    public Ouvinte() {
        id =0;
        nome ="";
        idade = 0;
        jogadas=0;
        vitorias =0;

    }

    public Ouvinte(int id, String nome, int idade, int jogadas, int vitorias) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.jogadas = jogadas;
        this.vitorias = vitorias;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getJogadas() {
        return jogadas;
    }

    public void setJogadas(int jogadas) {
        this.jogadas = jogadas;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Ouvinte" + this.id + "\tNome" + this.nome + "\tIdade" + this.idade;
    }
}