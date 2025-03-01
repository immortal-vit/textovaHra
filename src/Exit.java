public class Exit extends Command{

    @Override
    public String execute() {
        return "program se ukoncil";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
