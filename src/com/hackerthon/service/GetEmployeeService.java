package com.hackerthon.service;

import com.hackerthon.common.Constants;
import com.hackerthon.common.QueryUtil;
import com.hackerthon.common.UtilConfig;
import com.hackerthon.common.UtilTransform;
import com.hackerthon.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.hackerthon.common.CommonServices.LOGGER;


public class GetEmployeeService extends UtilConfig { //GetEmployeeService Class

    private static Connection connection; //connection Reference Variable from Connection Interface
    private final ArrayList<Employee> employeeList = new ArrayList<Employee>(); //ArrayList to store Employee
    private PreparedStatement preparedStatement; //preparedStatement Reference Variable from PreparedStatement Interface


    public GetEmployeeService() { // Constructor
        try {
            Class.forName(PROPERTIES.getProperty(Constants.DRIVER_NAME));// DRIVER_NAME com.mysql.cj.jdbc.Driver

            /**
             * Singleton Design Pattern If Connection Null Or Closed. Create New Connection
             */
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        PROPERTIES.getProperty(Constants.HOST) + ":" +
                                PROPERTIES.getProperty(Constants.PORT_NUMBER) + "/" +
                                PROPERTIES.getProperty(Constants.DATABASE) + "?" +
                                PROPERTIES.getProperty(Constants.CREATE_DATABASE_IF_NOT_Exist),
                        PROPERTIES.getProperty(Constants.USERNAME),
                        PROPERTIES.getProperty(Constants.PASSWORD));
            }
        } catch (ClassNotFoundException | SQLException exception) {
            LOGGER.log(Level.SEVERE, exception.getMessage());  // Logger
        }
    }//End Constructor

    public void employeeFormXML() { //Method to REad XML File Employee Data
        try {
            int size = UtilTransform.XMLXPATHS().size();
            for (int i = 0; i < size; i++) { // Add All data in XML
                Map<String, String> data = UtilTransform.XMLXPATHS().get(i);

                Employee employee = new Employee();// Create New Employee Object
                //Create Constants Variables
                employee.setEmployeeId(data.get(Constants.XPATH_EMPLOYEE_ID));
                employee.setNameFull(data.get(Constants.XPATH_EMPLOYEE_NAME));
                employee.setAddress(data.get(Constants.XPATH_EMPLOYEE_ADDRESS));
                employee.setFacultyName(data.get(Constants.XPATH_FACULTY_NAME));
                employee.setDepartment(data.get(Constants.XPATH_DEPARTMENT));
                employee.setDesignation(data.get(Constants.XPATH_DESIGNATION));

                employeeList.add(employee); // Add  Employee Object to Constant Array List
                System.out.println(employee.toString() + "\n");
            }
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, exception.getMessage());// Logger
        }
    }// employeeFormXML Method

    public void employeeTableCreate() {//Employee Database Table Creation Method
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(QueryUtil.queryByID(Constants.DROP_TABLE_EMPLOYEE)); // DROP TABLE IF EXISTS employees
            statement.executeUpdate(QueryUtil.queryByID(Constants.CREATE_TABLE_EMPLOYEE)); //Create New Employee Table
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());// Logger
        }
    }//End Method

    public void employeesAdd() {//Employee Add Method
        try {
            //Call INSERT_EMPLOYEE Query Through Constants Class id
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.INSERT_INTO_EMPLOYEE));
            connection.setAutoCommit(false);
            /*
             *   Enhanced For Loop to add each Employee in ArrayList
             * */
            for (Employee employee : employeeList) {
                preparedStatement.setString(1, employee.getEmployeeId());
                preparedStatement.setString(2, employee.getNameFull());
                preparedStatement.setString(3, employee.getAddress());
                preparedStatement.setString(4, employee.getFacultyName());
                preparedStatement.setString(5, employee.getDepartment());
                preparedStatement.setString(6, employee.getDesignation());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());// Logger
        }
    }//End Method

    public void employeeGetById(String employeeID) {//Employee Get By Id Method

        Employee employee = new Employee();
        try {
            //Call SELECT EMPLOYEE Query in Employee Query XML Through Constants Class id
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.SELECT_EMPLOYEE_WHERE_ID));
            preparedStatement.setString(1, employeeID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // While resultSet.next() = true
                employee.setEmployeeId(resultSet.getString(1));
                employee.setNameFull(resultSet.getString(2));
                employee.setAddress(resultSet.getString(3));
                employee.setFacultyName(resultSet.getString(4));
                employee.setDepartment(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
            }
            ArrayList<Employee> employeeList = new ArrayList<Employee>();
            employeeList.add(employee);
            employeeOutPut(employeeList);// CAll employeeOutPut Method to Print Employee
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());// Logger
        }//End Catch
    }//End Method

    public void employeeDelete(String employeeId) { //Employee Delete By Id Method

        try {
            //Call Delete EMPLOYEE Query in Employee Query XML Through Constants Class id DELETE_EMPLOYEE_BY_ID
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.DELETE_EMPLOYEE_BY_ID));
            preparedStatement.setString(1, employeeId);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            Logger.getLogger("../src/com/hackerthon/service").log(Level.SEVERE, exception.getMessage(), exception); // Logger
        }
    }//End Method


    public void employeeDisplay() { //Employee Display Method

        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        try {
            //Call select EMPLOYEE Query in Employee Query XML Through Constants Class id SELECT_EMPLOYEE
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.SELECT_ALL_EMPLOYEE));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getString(1));
                employee.setNameFull(resultSet.getString(2));
                employee.setAddress(resultSet.getString(3));
                employee.setFacultyName(resultSet.getString(4));
                employee.setDepartment(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
                employeeList.add(employee);
            }
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, exception.getMessage());// Logger
            exception.printStackTrace();
        }
        employeeOutPut(employeeList);//Send ArrayList to employeeOutPut Method
    }//End Employee Display Method


    public void employeeOutPut(ArrayList<Employee> employeeList) {  //Print Data
        System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
                + "Department" + "\t\t" + "Designation" + "\n");
        System.out.println(
                "================================================================================================================");

        for (Employee employee : employeeList) { // Enhanced For Loop

            System.out.println(employee.getEmployeeId() + "\t" + employee.getNameFull() + "\t\t" + employee.getAddress()
                    + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t"
                    + employee.getDesignation() + "\n");
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------");
        }
    }//End employeeOutPut Method

}//End Get Employee Service Class