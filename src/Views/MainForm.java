package Views;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

public class MainForm extends JFrame {
    
    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem settingsMenuItem = new JMenuItem("Settings");
        settingsMenuItem.setMnemonic(KeyEvent.VK_S);
        settingsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, KeyEvent.CTRL_DOWN_MASK));
        // settingsMenuItem.addActionListener(new ActionListener() { 
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // TODO: 
        //     }
        // });

        fileMenu.add(settingsMenuItem);

        fileMenu.add(new JSeparator());

        JMenuItem importOpmlMenuItem = new JMenuItem("Import OPML");
        importOpmlMenuItem.setMnemonic(KeyEvent.VK_I);
        // importOpmlMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK));
        // importOpmlMenuItem.addActionListener(new ActionListener() { 
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // TODO: 
        //     }
        // });

        fileMenu.add(importOpmlMenuItem);

        JMenuItem exportOpmlMenuItem = new JMenuItem("Export OPML");
        exportOpmlMenuItem.setMnemonic(KeyEvent.VK_E);
        // exportOpmlMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK));
        // exportOpmlMenuItem.addActionListener(new ActionListener() { 
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // TODO: 
        //     }
        // });

        fileMenu.add(exportOpmlMenuItem);

        fileMenu.add(new JSeparator());

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK));
        exitMenuItem.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(exitMenuItem);

        return fileMenu;
    }

    private JMenu createPodcastsMenu() {
        JMenu podcastsMenu = new JMenu("Podcasts");
        podcastsMenu.setMnemonic(KeyEvent.VK_P);

        JMenuItem subscriptionsMenuItem = new JMenuItem("Subscriptions");
        subscriptionsMenuItem.setMnemonic(KeyEvent.VK_S);
        subscriptionsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        // subscriptionsMenuItem.addActionListener(new ActionListener() { 
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // TODO: 
        //     }
        // });
        
        podcastsMenu.add(subscriptionsMenuItem);

        podcastsMenu.add(new JSeparator());

        JMenuItem nowPlayingMenuItem = new JMenuItem("Now Playing");
        nowPlayingMenuItem.setMnemonic(KeyEvent.VK_P);
        nowPlayingMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        // nowPlayingMenuItem.addActionListener(new ActionListener() { 
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // TODO: 
        //     }
        // });

        podcastsMenu.add(nowPlayingMenuItem);

        return podcastsMenu;
    }

    private JMenu createSearchMenu() {
        JMenu searchMenu = new JMenu("Search");
        searchMenu.setMnemonic(KeyEvent.VK_S);

        JMenuItem iTunesSearchMenuItem = new JMenuItem("iTunes Store");
        iTunesSearchMenuItem.setMnemonic(KeyEvent.VK_I);
        iTunesSearchMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        // iTunesSearchMenuItem.addActionListener(new ActionListener() { 
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // TODO: 
        //     }
        // });

        searchMenu.add(iTunesSearchMenuItem);

        return searchMenu;
    }

    private JMenu createHelpMenu() {
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke("F1"));
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Hodgepodge\na podcast player\nby Matthew Hickson", 
                    "About", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        helpMenu.add(aboutMenuItem);

        return helpMenu;
    }

    private JMenuBar createAppMenu() {
        JMenuBar mainMenu = new JMenuBar();

        mainMenu.add(createFileMenu());
        mainMenu.add(createPodcastsMenu());
        mainMenu.add(createSearchMenu());
        mainMenu.add(createHelpMenu());

        return mainMenu;
    }

    private JPanel createInterface() {
        JPanel layout = new JPanel(new CardLayout());

        JPanel settingsPanel = new JPanel();
        JPanel playerPanel = new JPanel();
        JPanel subscriptionPanel = new JPanel();
        JPanel searchPanel = new JPanel();

        JLabel settingsLabel = new JLabel("Settings");
        settingsPanel.add(settingsLabel);

        JLabel playerLabel = new JLabel("Player");
        playerPanel.add(playerLabel);

        JLabel subscriptionLabel = new JLabel("Subscriptions");
        subscriptionPanel.add(subscriptionLabel);

        JLabel searchLabel = new JLabel("Search");
        searchPanel.add(searchLabel);

        layout.add(settingsPanel);
        layout.add(playerPanel);
        layout.add(subscriptionPanel);
        layout.add(searchPanel);

        return layout;
    }

    /**
     * initialize form components
     */
    private void initComponents() {
        setJMenuBar(createAppMenu());
        add(createInterface());
    }

    /**
     * default constructor
     */
    public MainForm() {
        initComponents();

        setTitle("Hodgepodge");
        setSize(640, 480);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
