package Classes.ServletRegulation;

public class UserState extends State{
    public UserState(Context context) {
        super(context);
    }

    @Override
    public void LoadLink() {
        context.LoadUserLinks();
    }
}
