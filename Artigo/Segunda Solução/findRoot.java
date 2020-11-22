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