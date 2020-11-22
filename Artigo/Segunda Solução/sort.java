boolean trocou = false;
do {
    trocou = false;
    for (int i = 0; i < listaSaida.size() - 1; i++) {
        if (listaSaida.get(i).time > listaSaida.get(i + 1).time) {
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