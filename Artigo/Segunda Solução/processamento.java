List<String> liberados = new ArrayList<>();
proc.add(root);
proc.calcula(liberados);
while(!listaEntrada.isEmpty()) {
    for (int j = 0; j < proc.processadores.size(); j++) {
        if (proc.numeroProcessadoresDisponiveis() > 0) {
            for (int i = 0; i < listaEntrada.size(); i++) {
                if (proc.numeroProcessadoresDisponiveis() > 0 
                    && liberados.contains(listaEntrada.get(i).taskCode)) {
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