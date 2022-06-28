package Classes.ServletRegulation;

public abstract class State {
    protected Context context;

    public State(Context context){
        this.context = context;
    }

    public abstract void LoadLink();
}
