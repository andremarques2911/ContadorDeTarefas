import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) {
        try {
            String path = "../casos/caso100.txt";
            FileReader arq = new FileReader(path);
            BufferedReader br = new BufferedReader(arq);

            int qtdProcessadores = Integer.parseInt(br.readLine().split(" ")[2]);
            Processadores proc = new Processadores(qtdProcessadores);

            List<Task> listaEntrada = new ArrayList<>();
            List<Task> listaSaida = new ArrayList<>();

            String linha = null;
            while ((linha = br.readLine()) != null) {
                if(!linha.equals("")) {
                    String[] tasks = linha.split("->");
                    String[] taskIn = tasks[0].split("_");
                    Task t1 = new Task(tasks[0].trim(), Integer.parseInt(taskIn[1].trim()));
                    listaEntrada.add(t1);
                    String[] taskOut = tasks[1].split("_");
                    Task t2 = new Task(tasks[1].trim(), Integer.parseInt(taskOut[1].trim()));
                    t2.father = t1.taskCode;
                    listaSaida.add(t2);
                }
            }
            long timeIn = System.currentTimeMillis();
            calculate(proc, listaEntrada, listaSaida, true);
            long timeOut = System.currentTimeMillis();
            System.out.println("Tempo de execucaoo: " + (timeOut - timeIn) + " milissegundos.");
            System.out.println(proc.soma);
            arq.close();
            br.close();
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }
    }

    private static void calculate(Processadores proc, List<Task> listaEntrada, List<Task> listaSaida, boolean isMin) {
        boolean trocou = false;
        do {
            trocou = false;
            for (int i = 0; i < listaSaida.size() - 1; i++) {
                boolean verificacao = isMin ? listaSaida.get(i).time > listaSaida.get(i + 1).time : listaSaida.get(i).time < listaSaida.get(i + 1).time ;
                if (verificacao) {
                    Task auxEntrada = listaEntrada.get(i);
                    listaEntrada.set(i, listaEntrada.get(i + 1));
                    listaEntrada.set(i + 1, auxEntrada);
                    Task auxSaida = listaSaida.get(i);
                    listaSaida.set(i, listaSaida.get(i + 1));
                    listaSaida.set(i + 1, auxSaida);
                    trocou = true;
                }
            }
        } while (trocou);

        Task root = null;
        for (Task tIn : listaEntrada) {
            boolean achou = false;
            for (Task tOut : listaSaida) {
                achou = false;
                if (tIn.taskCode.equals(tOut.taskCode)) {
                    achou = true;
                    break;
                }
            }
            if (!achou) {
                root = tIn;
                break;
            }
        }

        List<String> liberados = new ArrayList<>();
        proc.add(root);
        proc.calcula(liberados);
        while(!listaEntrada.isEmpty()) {
            for (int j = 0; j < proc.processadores.size(); j++) {
                if (proc.numeroProcessadoresDisponiveis() > 0) {
                    for (int i = 0; i < listaEntrada.size(); i++) {
                        if (proc.numeroProcessadoresDisponiveis() > 0 && liberados.contains(listaEntrada.get(i).taskCode)) {
                            proc.add(listaSaida.get(i));
                            listaEntrada.remove(listaEntrada.get(i));
                            listaSaida.remove(listaSaida.get(i));
                        }
                    }
                }
            }
            proc.calcula(liberados);
        }
        while (proc.numeroProcessadoresDisponiveis() < proc.processadores.size()) {
            proc.calcula(liberados);
        }
    }
}