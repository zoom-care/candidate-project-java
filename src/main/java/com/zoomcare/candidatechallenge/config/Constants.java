package com.zoomcare.candidatechallenge.config;

public class Constants {

    // base url
    public static final String BASE_URL = "/employee";

    // database columns
    public static final String EMPLOYEE_ID = "EMPLOYEE_ID";
    public static final String KEY = "KEY";
    public static final String VALUE = "VALUE";
    public static final String SUPERVISOR_ID = "SUPERVISOR_ID";
    public static final String ID = "ID";

    // Employee Table Queries

    /**
     * Query giving all top-level employees and their direct reports.
     * This query will join the collection of top-level employees with recursive
     * calls to gather their direct reports.
     */
    public static final String TOP_LEVEL_QUERY =
            "with top_query (supervisor_id, id, level) as " +
                    "(  select e.supervisor_id, e.id, 0 as level " +
                    "      from employee e " +
                    "      where e.supervisor_id is null " +
                    "    union all " +
                    "    select e2.supervisor_id, e2.id, level + 1 " +
                    "      from employee as e2 " +
                    "      inner join top_query as e3 " +
                    "      on e2.supervisor_id = e3.id " +
                    ") " +
                    "    select supervisor_id, id, level" +
                    "      from top_query" +
                    "      order by level";

    /**
     * Recursive query that will get all of the direct reports for a given
     * supervisorId.
     */
    public static final String EMP_QUERY =
            "with emp_query (supervisor_id, id, level) as " +
                    "(  select e.supervisor_id, e.id, 0 as level " +
                    "      from employee e " +
                    "      where e.supervisor_id = ? " +
                    "    union all " +
                    "    select e2.supervisor_id, e2.id, level + 1 " +
                    "      from employee as e2 " +
                    "      inner join emp_query as e3 " +
                    "      on e2.supervisor_id = e3.id " +
                    ") " +
                    "    select supervisor_id, id, level" +
                    "      from emp_query" +
                    "      order by level";

    public static final String ONE_EMPLOYEE_QUERY =
            "select supervisor_id, id from employee where id = ?";

    // Property table queries
    public static final String EMPLOYEE_PROPERTIES_QUERY = "SELECT employee_id, key, value FROM property WHERE employee_id IN (:idsToFetch) ORDER BY employee_id";
    public static final String IDS_TO_FETCH = "idsToFetch";

    // messages
    public static final String UNABLE_TO_CREATE_RESPONSE = "Unable to create the response for employee '{0}'";
    public static final String UNABLE_TO_CREATE_RESPONSE_ALL = "Unable to create the response for all employees";
    public static final String EMPLOYEE_ID_NOT_FOUND = "Unable to find employee ID {0}";
    public static final String UNABLE_TO_PROCESS_PROPERTIES = "Failed to process properties for employee(s)";
    public static final String UNABLE_TO_PROCESS_EMPLOYEE = "Failed to process employees";
    public static final String EMPLOYEE_ID_MUST_BE_NUMBER = "Employee ID must be a number";



    /**
     * No argument constructor to override the implicit one.
     */
    private Constants() {}
}
