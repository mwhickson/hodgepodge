import javax.swing.UIManager;
import java.nio.charset.Charset;
import java.nio.file.Paths;

import Controllers.OpmlConverter;
import Views.MainForm;

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

        // String opml = OpmlConverter.ReadOpmlFileAsString(Paths.get("{pathtoopml}"), Charset.forName("UTF-8"));
        // System.out.println(opml);

        // Object x = OpmlConverter.BuildSubscriptionListFromOpmlString(opml);

        // OpmlConverter.WriteOpmlStringToFile(opml, Paths.get("{pathtoopml}"), Charset.forName("UTF-8"), false);
    }
}
