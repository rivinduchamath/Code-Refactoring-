package com.hackerthon.main;

import com.hackerthon.common.UtilTransform;
import com.hackerthon.service.GetEmployeeService;

import java.io.IOException;
import java.util.logging.Level;

import static com.hackerthon.common.CommonServices.LOGGER;

public class ExecuteMain {//Execute Main Class

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) {//Main Method

        GetEmployeeService employeeService = new GetEmployeeService(); // Call GetEmployeeService Constructor
        // Create GetEmployeeService Object
        try {
            UtilTransform.requestTransform();
            System.out.println("------Employees Form XML------\n");// Call Read employee data Form XML Method
            employeeService.employeeFormXML();

            System.out.println("------Create Employees Table------\n");// Call Create Employee Table Method
            employeeService.employeeTableCreate();

            System.out.println("------Add All Employees------\n");// Call Add Method
            employeeService.employeesAdd();

            System.out.println("------Get Employee By Id------\n");
            employeeService.employeeGetById("EMP10001");// Call Employee Get By ID Method

            System.out.println("------Delete Employee By Id------\n");
            employeeService.employeeDelete("EMP10001"); // Call Delete Method

            System.out.println("------Display All Employees------\n");
            employeeService.employeeDisplay();  // Call Display Method

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage()); // Logger
        }

    }//End Main Method

}//End Class