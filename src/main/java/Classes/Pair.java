package Classes;

public class Pair<L,R> {
    private final L Key;
    private final R Value;
    public Pair(L Key,R Value){
        assert Key != null;
        assert Value != null;

        this.Key = Key;
        this.Value = Value;
    }
    public L getKey(){return Key;}
    public R getValue(){return Value;}

}
