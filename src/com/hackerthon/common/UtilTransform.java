package com.hackerthon.common;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UtilTransform extends UtilConfig { //Start UtilTransform Class

    private static final ArrayList<Map<String, String>> ARRAY_LIST = new ArrayList<Map<String, String>>();

    private static Map<String, String> stringMap = null; //Map Create

    public static void requestTransform() throws Exception {
        //Request XML
        Source source = new StreamSource(new File(PROPERTIES.getProperty(Constants.REQUEST_XML)));
        //XML modify
        Source source2 = new StreamSource(new File(PROPERTIES.getProperty(Constants.MODIFIED_XML)));
        //XML output
        Result result = new StreamResult(new File(PROPERTIES.getProperty(Constants.RESPONSE_XML)));
        TransformerFactory.newInstance().newTransformer(source2).transform(source, result);
    }//End requestTransform Method


    public static ArrayList<Map<String, String>> XMLXPATHS() throws Exception {

        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(PROPERTIES.getProperty(Constants.RESPONSE_XML)); //src/com/hackerthon/config/EmployeeResponse.xml through Constants Class

        XPath x = XPathFactory.newInstance().newXPath();
        int n = Integer.parseInt((String) x.compile("count(//Employees/Employee)").evaluate(d, XPathConstants.STRING));
        for (int i = 1; i <= n; i++) {
            //Add Data to Hashmap
        	stringMap = new HashMap<String, String>();
        	stringMap.put(Constants.XPATH_EMPLOYEE_ID, (String) x.compile("//Employees/Employee[" + i + "]/EmployeeID/text()")
                    .evaluate(d, XPathConstants.STRING));
        	stringMap.put(Constants.XPATH_EMPLOYEE_NAME, (String) x.compile("//Employees/Employee[" + i + "]/EmployeeFullName/text()")
                    .evaluate(d, XPathConstants.STRING));
        	stringMap.put(Constants.XPATH_EMPLOYEE_ADDRESS,
                    (String) x.compile("//Employees/Employee[" + i + "]/EmployeeFullAddress/text()").evaluate(d,
                            XPathConstants.STRING));
        	stringMap.put(Constants.XPATH_FACULTY_NAME, (String) x.compile("//Employees/Employee[" + i + "]/FacultyName/text()")
                    .evaluate(d, XPathConstants.STRING));
        	stringMap.put(Constants.XPATH_DEPARTMENT, (String) x.compile("//Employees/Employee[" + i + "]/Department/text()")
                    .evaluate(d, XPathConstants.STRING));
        	stringMap.put(Constants.XPATH_DESIGNATION, (String) x.compile("//Employees/Employee[" + i + "]/Designation/text()")
                    .evaluate(d, XPathConstants.STRING));
            ARRAY_LIST.add(stringMap); //Save Hash Map IN ArrayList
        }//End For Loop
        return ARRAY_LIST; // Return Constant ArrayList
    }

}//End Class UtilTransform
