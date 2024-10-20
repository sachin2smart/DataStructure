package in.sachinshinde.graph.dfs.visited_array;

import java.util.*;

//  https://leetcode.com/problems/employee-importance

/*
    You have a data structure of employee information, including the
        employee's unique ID, importance value, and direct subordinates' IDs.

    You are given an array of employees "employees" where:
        -   employees[i].id is the ID of the ith employee.
        -   employees[i].importance is the importance value of the ith employee.
        -   employees[i].subordinates is a list of the IDs of the direct subordinates of the ith employee.

    Given an integer id that represents an employee's ID,
        return the total importance value of this employee and all their direct and indirect subordinates.

    Example 1:
    ---------

                 (1,5)
                /     \
             (2,3)   (3,3)

    Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
    Output: 11
    Explanation: Employee 1 has an importance value of 5 and has two direct subordinates: employee 2 and employee 3.
    They both have an importance value of 3.
    Thus, the total importance value of employee 1 is 5 + 3 + 3 = 11.

    Example 2:
    ---------

                (1,2)
                /
             (5,-3)

    Input: employees = [[1,2,[5]],[5,-3,[]]], id = 5
    Output: -3
    Explanation: Employee 5 has an importance value of -3 and has no direct subordinates.
    Thus, the total importance value of employee 5 is -3.

    Constraints:
    -----------
        1 <= employees.length <= 2000
        1 <= employees[i].id <= 2000
        All employees[i].id are unique.
        -100 <= employees[i].importance <= 100
        One employee has at most one direct leader and may have several subordinates.
        The IDs in employees[i].subordinates are valid IDs.
 */

public class EmployeeImportance {

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> hm = new HashMap<>();
        for(Employee e: employees) {
            hm.put(e.id, e);
        }
        return getImp(hm, id);
    }

    private int getImp(Map<Integer, Employee> hm, int id) {
        Employee e = hm.get(id);
        int imp = e.importance;
        for(int subId: e.subordinates) {
            imp += getImp(hm, subId);
        }
        return imp;
    }

    public static void main(String[] args) {
        EmployeeImportance employeeImportance = new EmployeeImportance();
        Employee e1 = new Employee();
        e1.id = 1;
        e1.importance = 5;
        e1.subordinates = Arrays.asList(2,3);

        Employee e2 = new Employee();
        e2.id = 2;
        e2.importance = 3;
        e2.subordinates = Collections.emptyList();

        Employee e3 = new Employee();
        e3.id = 3;
        e3.importance = 3;
        e3.subordinates = Collections.emptyList();

        System.out.println(employeeImportance.getImportance(Arrays.asList(e1, e2, e3), 1));
        System.out.println(employeeImportance.getImportance2(Arrays.asList(e1, e2, e3), 1));
        System.out.println(employeeImportance.getImportance3(Arrays.asList(e1, e2, e3), 1));
        System.out.println(employeeImportance.getImportance4(Arrays.asList(e1, e2, e3), 1));
        System.out.println(employeeImportance.getImportanceBFS(Arrays.asList(e1, e2, e3), 1));
        System.out.println(employeeImportance.getImportance5(Arrays.asList(e1, e2, e3), 1));
    }

    public int getImportance2(List<Employee> employees, int id) {
        Employee[] emp = new Employee[2001];
        int[] value = new int[1];
        for(Employee e : employees) {
            emp[e.id] = e;
        }
        Set<Integer> visited = new HashSet<>();
        visited.add(id);
        dfs(emp, emp[id], value,visited);
        return value[0];
    }

    void dfs(Employee [] emp , Employee e, int[] value, Set<Integer> visited) {
        if(e == null || e.subordinates == null) {
            return;
        }
        value[0] += e.importance;
        for(int id : e.subordinates) {
            if(!visited.contains(id)) {
                dfs(emp, emp[id], value, visited);
            }
        }
    }

    int res = 0;
    public int getImportance3(List<Employee> employees, int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).id == id) {
                res += employees.get(i).importance;
                for (int j = 0; j < employees.get(i).subordinates.size(); j++) {
                    getImportance3(employees, employees.get(i).subordinates.get(j));
                }
            }
        }
        return res;
    }

    Map<Integer, Employee> idsMap;
    public int getImportance4(List<Employee> employees, final int id) {
        idsMap = new HashMap<>();

        for(Employee employee : employees) {
            idsMap.put(employee.id, employee);
        }

        return dfs4(idsMap.get(id));
    }

    private int dfs4(Employee e) {
        if(e == null) {
            return 0;
        }

        int sum = e.importance;

        for(Integer id : e.subordinates) {
            sum += dfs4(idsMap.get(id));
        }

        return sum;
    }

    public int getImportanceBFS(List<Employee> employees, int id) {
        HashMap<Integer,Employee> m = new HashMap<>();

        for(Employee e: employees){
            m.put(e.id, e);
        }

        int sum = 0;

        Queue<Employee> q = new ArrayDeque<>();
        q.add(m.get(id));

        while(q.size() != 0){
            Employee temp = q.peek();
            q.remove();

            sum += temp.importance;

            for(int it : temp.subordinates){
                q.add(m.get(it));
            }
        }

        return sum;
    }

    public int getImportance5(List<Employee> employees, int id) {
        Map<Integer, Integer> hm = new HashMap<>();
        for(int i = 0;i < employees.size(); i++) {
            hm.put(employees.get(i).id,i);
        }
        return dfs5(hm, id, employees);
    }

    private int dfs5(Map<Integer,Integer> adj, int id, List<Employee> employees) {
        int sum = 0;
        int index = adj.get(id);
        for(int child : employees.get(index).subordinates) {
            sum += dfs5(adj, child, employees);
        }
        sum += employees.get(index).importance;
        return sum;
    }
}
