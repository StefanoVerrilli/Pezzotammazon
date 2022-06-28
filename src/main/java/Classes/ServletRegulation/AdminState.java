package Classes.ServletRegulation;

public class AdminState extends State{
    public AdminState(Context context) {
        super(context);
    }

    @Override
    public void LoadLink() {
        context.LoadAdminLinks();
    }
}
