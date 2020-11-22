import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Processadores {

    public int quantidade;
    public List<Task> processadores;
    public int soma;

    public Processadores(int quantidade) {
        this.processadores = new ArrayList<>();
        this.quantidade = quantidade;
        this.soma = 0;
    }

    public void add(Task task) {
        if (this.processadores.size() == this.quantidade) {
            for (int i = 0; i < this.quantidade; i++) {
                if (this.processadores.get(i).time == 0) {
                    this.processadores.set(i, task);
                    return;
                }
            }
        } else {
            this.processadores.add(task);
        }
    }

    public int numeroProcessadoresDisponiveis() {
        return this.quantidade - this.processadores.stream().filter(p -> p.time > 0).collect(Collectors.toList()).size();
    }

    public void calcula(List<String> liberados) {
        this.processadores.sort((Task n1, Task n2) -> n1.time - n2.time);
        List<Task> listaProcessos = this.processadores.stream().filter(p -> p.time > 0).collect(Collectors.toList());
        if (listaProcessos == null || listaProcessos.size() == 0) return;
        int timeLess = listaProcessos.get(0).time;
        for (Task p : this.processadores) {
            if (p.time > 0) {
                int sub = p.time - timeLess;
                if (sub == 0) {

                }
                p.time = sub;
            }
        }
        this.soma += timeLess;
        if (!liberados.contains(listaProcessos.get(0).taskCode)) {
            liberados.add(listaProcessos.get(0).taskCode);
        }
    }
}
