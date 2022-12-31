package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

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

    public static String DEFAULT_OPML_PATH_EXPRESSION = "/opml/body/outline[@text='feeds']/outline";
    public static String[] DEFAULT_PROPERTIES_LIST = {"text", "xmlUrl", "type"};

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

    public static ArrayList<HashMap> BuildSubscriptionPropertyListFromOpmlString(String s) {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        XPathFactory fx = XPathFactory.newInstance();

        try {
            DocumentBuilder builder = f.newDocumentBuilder();

            try {
                Document doc = builder.parse(new InputSource(new StringReader(s)));

                XPath xpath = fx.newXPath();

                try {
                    ArrayList<HashMap> subPropertyList = new ArrayList<HashMap>();
                    XPathExpression xpe = xpath.compile(OpmlConverter.DEFAULT_OPML_PATH_EXPRESSION);

                    NodeList subList = (NodeList) xpe.evaluate(doc, XPathConstants.NODESET);

                    for (int i = 0; i < subList.getLength(); i++) {
                        Node sub = subList.item(i);

                        HashMap<String, String> subProperties = new HashMap<String, String>();
                        Node p = null;
                        
                        if (sub != null) {
                            if (sub.hasAttributes()) {
                                NamedNodeMap m = sub.getAttributes();

                                for (int j = 0; j < OpmlConverter.DEFAULT_PROPERTIES_LIST.length; j++) {
                                    p = m.getNamedItem(OpmlConverter.DEFAULT_PROPERTIES_LIST[j]);

                                    if (p != null) {
                                        subProperties.put(OpmlConverter.DEFAULT_PROPERTIES_LIST[j], p.getTextContent());
                                    } else {
                                        subProperties.put(OpmlConverter.DEFAULT_PROPERTIES_LIST[j], "");
                                    }
                                }

                                subPropertyList.add(subProperties);
                            }
                        }
                    }

                    return subPropertyList;

                } catch (XPathExpressionException ex) {
                    System.err.format("XPathExpressionException: %s%n", ex);
                }

            } catch (SAXException ex) {
                System.err.format("SAXException: %s%n", ex);
            } catch (IOException ex) {
                System.err.format("IOException: %s%n", ex);
            }

        } catch (ParserConfigurationException ex) {
            System.err.format("ParserConfigurationException: %s%n", ex);
        }
        
        return null;
    }

}
