import javax.swing.UIManager;

import UI.MainForm;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException e) { System.exit(1); }
        catch (InstantiationException e) { System.exit(1); }
        catch (IllegalAccessException e) { System.exit(1); }

        MainForm f = new MainForm();
        f.setVisible(true);
    }
}
