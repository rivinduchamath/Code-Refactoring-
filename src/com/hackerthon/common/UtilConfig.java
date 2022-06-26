package com.hackerthon.common;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilConfig { //Class UtilConfig

    public static Properties PROPERTIES = null;

    static {
        try {
            PROPERTIES = new Properties();
            PROPERTIES.load(QueryUtil.class.getResourceAsStream(Constants.PROPERTY_FILE));

        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger("com.hakerthon.common").log(Level.SEVERE, null, e); // Add Logger To Catch Exception
        }
    }//End Static
}//End Class


