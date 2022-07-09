package Classes.ServletsRegulation;

public class Context {

    private State state;

    public Context(State state){
        this.state = state;
    }

    public void changeState(State state){
        this.state = state;
    }
    public void LoadLinks(){
        this.state.LoadLink();
    }
}
