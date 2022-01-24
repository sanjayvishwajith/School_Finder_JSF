import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

@ManagedBean(name="obj")
@SessionScoped
public class product {
    private String name;
    private String area;
    private  int num;
    private  String find;
    public List<product> searchList;

    public String getFind() {
        return find;
    }
    public void setFind(String find) {
        this.find = find;
    }
    public product(){
        
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
  
     public List<product> seeAll() {
        List<product> rsList = new ArrayList<product>();
    
        try{  
            
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/school","root","");  
       
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("SELECT * FROM school");  
            
            while(rs.next()) {
                product scl = new product();
                
                scl.setName(rs.getString(1));
                scl.setArea(rs.getString(2));
                scl.setNum(rs.getInt(3));  
                rsList.add(scl);
            }  
            con.close();  
        }catch(Exception e){ System.out.println(e);}  
        return rsList;
    }
    public String add() {
        try{  
            
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/school","root","");  
            Statement stmt = con.createStatement();  
            int rslt = stmt.executeUpdate("INSERT INTO "+ "school" +" VALUES ('"+ name +"', '"+ area +"', "+ num +");");
            
            if (rslt == 1) {
                System.out.println("Inserted Successfully");
                return "success";
            }
            else {
                System.out.println("Insertion Failed");
            }
            
            con.close();  
        }catch(Exception e){ System.out.println(e);}  
        
        
        return "fail";
    }
    public String search() {
        searchList = new ArrayList<product>();
        try{  
            
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/school","root","");  
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("SELECT * FROM school where area="+"'"+find+"'");  
            while(rs.next()) {
                product scl = new product();
                scl.setName(rs.getString(1));
                scl.setArea(rs.getString(2));
                scl.setNum(rs.getInt(3));  
                searchList.add(scl);
            }  
            con.close();  
        }catch(Exception e){ System.out.println(e);}   
        return  "display";
    }
    public List<product> show(){
       return searchList;
    }
    
}
