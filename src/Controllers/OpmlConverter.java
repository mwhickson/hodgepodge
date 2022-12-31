package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class OpmlConverter {
    
    public static String ReadOpmlFileAsString(Path p, Charset c) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(p, c)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException ex) {
            System.err.format("IOException: %s%n", ex);
        }

        return sb.toString();
    }

    public static boolean WriteOpmlStringToFile(String s, Path p, Charset c, boolean shouldOverwrite) {
        boolean success = false;

        try (BufferedWriter writer = Files.newBufferedWriter(p, c)) {
            writer.write(s);
            success = true;
        } catch (IOException ex) {
            System.err.format("IOException: %s%n", ex);
        }

        return success;
    }

    // YUCK!
    // FIX: make this less like garbage...
    public static Object BuildSubscriptionListFromOpmlString(String s) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        XPathFactory factoryx = XPathFactory.newInstance();

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            try {
                InputSource is = new InputSource(new StringReader(s));
                Document doc = builder.parse(is);

                XPath xpath = factoryx.newXPath();
                try {
                    XPathExpression expr = xpath.compile("/opml/body/outline[@text='feeds']/outline");

                    NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                    for (int i = 0; i <= list.getLength(); i++) {
                        Node n = list.item(i);
                        
                        if (n != null) {
                            if (n.hasAttributes()) {
                                NamedNodeMap m = n.getAttributes();
    
                                Node an1 = m.getNamedItem("text");
                                Node an2 = m.getNamedItem("xmlUrl");
                                Node an3 = m.getNamedItem("type");
    
                                boolean printedSomething = false;
    
                                if (an1 != null) {
                                    System.out.print(an1.getTextContent());
                                    printedSomething = true;
                                }
    
                                if (an2 != null) {
                                    if (printedSomething) {
                                        System.out.print(" : ");
                                    }
                                    System.out.print(an2.getTextContent());
                                    printedSomething = true;
                                }
    
                                if (an3 != null) {
                                    if (printedSomething) {
                                        System.out.print(" : ");
                                    }
                                    System.out.print(an3.getTextContent());
                                    printedSomething = true;
                                }
    
                                if (printedSomething) {
                                    System.out.println();
                                }
                            }
                        }
                    }

                    // TODO: create actual subscriptions from NodeList...
                    return list;
                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
