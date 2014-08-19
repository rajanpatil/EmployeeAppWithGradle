package com.employee.repository;

import com.employee.EmployeePOJO;
import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by rajan on 13/8/14.
 */
public class EmployeeDAO {

    private DB db = null;

   public EmployeeDAO() {
        try{
            db = new MongoClient( "localhost" , 27017 ).getDB( "EmployeeAppDB" );


        }catch(Exception e){

        }
   }

    public void addEmployee(EmployeePOJO emp){
        DBCollection employee = db.getCollection("com/employee");
        BasicDBObject doc = new BasicDBObject("id", emp.getId()).append("name", emp.getName()).append("role", emp.getRole());
        employee.insert(doc);
    }

    public List<EmployeePOJO> getAllEmployee(){
        List<EmployeePOJO> empList = new ArrayList<EmployeePOJO>();
        DBCollection employee = db.getCollection("com/employee");
        DBCursor cursor = employee.find();
        try {
            while(cursor.hasNext()) {
                DBObject object = cursor.next();
                EmployeePOJO emp = new EmployeePOJO();
                emp.setId(object.get("id").toString());
                emp.setName(object.get("name").toString());
                emp.setRole(object.get("role").toString());
                empList.add(emp);
            }
        } finally {
            cursor.close();
        }
        return empList;
    }
}
