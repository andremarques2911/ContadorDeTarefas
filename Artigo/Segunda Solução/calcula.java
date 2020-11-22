public void calcula(List<String> liberados) {
    this.processadores.sort((Task n1, Task n2) -> n1.time - n2.time);
    List<Task> listaProcessos = this.processadores
                                            .stream()
                                            .filter(p -> p.time > 0)
                                            .collect(Collectors.toList());
    if (listaProcessos == null || listaProcessos.size() == 0) return;
    int timeLess = listaProcessos.get(0).time;
    for (Task p : this.processadores) {
        if (p.time > 0) {
            int sub = p.time - timeLess;
            if (sub == 0) {
                if (!liberados.contains(p.taskCode)) {
                    liberados.add(p.taskCode);
                }
            }
            p.time = sub;
        }
    }
    this.soma += timeLess;
}