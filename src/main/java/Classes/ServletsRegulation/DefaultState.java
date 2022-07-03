package Classes.ServletsRegulation;

import Classes.FrontController.Actions;

public class DefaultState extends State {
    public DefaultState(Context context) {
        super(context);
    }

    @Override
    public void LoadLink() {
        Actions.delete();
        context.LoadDefaultLinks();
    }
}
