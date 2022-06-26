package com.hackerthon.common;

public class Constants {// class Constants for store Constants

    //Database Configurations Access (configaration.properties)
    public static final String DRIVER_NAME = "driver";
    public static final String HOST = "host";
    public static final String PORT_NUMBER = "port";
    public static final String DATABASE = "database";
    public static final String CREATE_DATABASE_IF_NOT_Exist = "create_database_if_not_exist";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";

    //Data QueryUtil
    public static final String ID = "id";
    public static final String QUERY = "query";

    // xml access attributes
    public static final String XPATH_EMPLOYEE_ID = "XpathEmployeeIDKey";
    public static final String XPATH_EMPLOYEE_NAME = "XpathEmployeeNameKey";
    public static final String XPATH_EMPLOYEE_ADDRESS = "XpathEmployeeAddressKey";
    public static final String XPATH_FACULTY_NAME = "XpathFacultyNameKey";
    public static final String XPATH_DEPARTMENT = "XpathDepartmentKey";
    public static final String XPATH_DESIGNATION = "XpathDesignationKey";

    // Database Query Access Id (EmployeeQuery.xml)
    public static final String CREATE_TABLE_EMPLOYEE = "create_table_employees";
    public static final String DROP_TABLE_EMPLOYEE = "drop_table_employees";
    public static final String INSERT_INTO_EMPLOYEE = "insert_into_employees";
    public static final String SELECT_EMPLOYEE_WHERE_ID = "select_employee_where_id";
    public static final String SELECT_ALL_EMPLOYEE = "select_all_employee";
    public static final String DELETE_EMPLOYEE_BY_ID = "delete_employee_by_id";

    // XML and XSL Files Access
    public static final String RESPONSE_XML = "responseXmlPath";
    public static final String REQUEST_XML = "requestXmlPath";
    public static final String MODIFIED_XML = "modifiedXmlPath";
    public static final String QUERY_XML = "queryXmlPath";
    public static final String PROPERTY_FILE = "../config/configaration.properties";

    //Logging
    public static final String COMMON_LOGGER = "com.hakerthon.common";


}//End Class
