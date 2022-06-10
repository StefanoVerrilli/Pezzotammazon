package Classes;

public class Pair<L,R> {
    private L Key;
    private R Value;
    public Pair(L Key,R Value){
        assert Key != null;
        assert Value != null;

        this.Key = Key;
        this.Value = Value;
    }
    public L getKey(){return Key;}
    public R getValue(){return Value;}

    public void setValue(R Value){this.Value = Value;}
    public void setKey(L key){this.Key = key;}
}
