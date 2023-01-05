import javax.swing.UIManager;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Controllers.OpmlConverter;
import Controllers.PodcastRetriever;
import Models.Subscription;
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

        // String userHome = System.getProperty("user.home");
        // String dataPath = userHome + "/projects/data/hodgepodge/podbay.opml";

        // String opml = OpmlConverter.ReadOpmlFileAsString(Paths.get(dataPath), Charset.forName("UTF-8"));

        // ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();
        // ArrayList<HashMap> subscriptionData = OpmlConverter.BuildSubscriptionPropertyListFromOpmlString(opml);

        // Iterator<HashMap> itd = subscriptionData.iterator();
        // while (itd.hasNext()) {
        //     HashMap data = itd.next();
        //     subscriptions.add(new Subscription(data));
        // }

        // Iterator<Subscription> its = subscriptions.iterator();
        // while (its.hasNext()) {
        //     Subscription s = its.next();
        //     // System.out.format("%s%n", s.toString());
        //     System.out.format("%s%n", s.toJSON());
        // }

        // PodcastRetriever.GetPodcastFeedByUrl("https://www.cbc.ca/podcasting/includes/radio2/canada_live/enhanced/index.xml");
    }
}
