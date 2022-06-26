package com.hackerthon.common;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryUtil extends UtilConfig { //Query Util Class Extend

    public static String queryByID(String id) {//Static method queryByID
        NodeList nodeList;
        Element element = null;

        try {
            nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new File(PROPERTIES.getProperty(Constants.QUERY_XML)))
                    .getElementsByTagName(Constants.QUERY);

            for (int x = 0; x < nodeList.getLength(); x++) {
                element = (Element) nodeList.item(x);
                if (element.getAttribute(Constants.ID).equals(id))
                    break;
            }//End ForLoop
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(Constants.COMMON_LOGGER).log(Level.SEVERE, null, e); // Add Logger To Catch Exception
        }
        assert element != null;
        return element.getTextContent().trim();
    }//End Method
}//End Class


