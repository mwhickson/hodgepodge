import javax.swing.UIManager;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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

        // ArrayList<HashMap> subs = OpmlConverter.BuildSubscriptionPropertyListFromOpmlString(opml);
        // Iterator<HashMap> it = subs.iterator();

        // while (it.hasNext()) {
        //     HashMap sub = it.next();
        //     System.out.format("%s%n> %s: %s%n%n", sub.get("text"), sub.get("type"), sub.get("xmlUrl"));
        // }

        // OpmlConverter.WriteOpmlStringToFile(opml, Paths.get("{pathtoopml}"), Charset.forName("UTF-8"), false);
    }
}
