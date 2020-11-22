public class Task {

    public String taskCode;
    public int time;
    public String father;

    public Task(String taskCode, int time) {
        this.taskCode = taskCode;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Tarefa: " + this.taskCode + " Tempo: " + this.time + " Pai: " + this.father;
    }

//    @Override
//    public String toString() {
//        return this.taskCode;
//    }
}