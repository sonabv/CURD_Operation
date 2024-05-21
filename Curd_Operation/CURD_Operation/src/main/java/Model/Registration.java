package Model;

import java.sql.*;

import java.util.ArrayList;

import jakarta.servlet.http.HttpSession;

public class Registration {

    private Connection con;
    HttpSession se;

    public Registration(HttpSession session) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver"); // load the drivers
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc","root", "tiger");
            // connection with data base
            se = session;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String Registration(String name, String phone, String email, String pw) {
        PreparedStatement ps;
        String status = "";
        try {
            Statement st = null;
            ResultSet rs = null;
            st = con.createStatement();
            rs = st.executeQuery("select * from sookshmas where phone='" + phone + "' or email='" + email + "';");
            boolean b = rs.next();
            if (b) {
                status = "existed";
            } else {
                ps = (PreparedStatement) con.prepareStatement("insert into sookshmas values(0,?,?,?,?,now())");
                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, email);
                ps.setString(4, pw);
                int a = ps.executeUpdate();
                if (a > 0) {
                    status = "success";
                } else {
                    status = "failure";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public String login(String email, String pass) {
        String status1 = "", id = "";
        String name = "", emails = "";

        try {
            Statement st = null;
            ResultSet rs = null;
            st = con.createStatement();

            rs = st.executeQuery("select * from sookshmas where email='" + email + "' and password='" + pass + "';");
            boolean b = rs.next();
            if (b == true) {
                id = rs.getString("slno");
                name = rs.getString("name");
                emails = rs.getString("email");
                se.setAttribute("uname", name);
                se.setAttribute("email", emails);
                se.setAttribute("id", id);
                status1 = "success";
            } else {
                status1 = "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status1;
    }
    public ArrayList<Student> getUserinfo(String id) {
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Student> al = new ArrayList<Student>();
        try {
            st = con.createStatement();
            String qry = "select * from sookshmas where slno = '" + id + "';";
            rs = st.executeQuery(qry);
            while (rs.next()) {
                Student p = new Student();
                p.setId(rs.getString("slno"));
                p.setName(rs.getString("name"));
                p.setEmail(rs.getString("email"));
                p.setPhone(rs.getString("phone"));
                p.setDate(rs.getString("date"));
                al.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return al;
    }
    public Student getInfo() {
        Statement st = null;
        ResultSet rs = null;
        Student s = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from sookshmas where slno= '" + se.getAttribute("id") + "'");
            boolean b = rs.next();
            if (b == true) {
                s = new Student();
                s.setName(rs.getString("name"));
                s.setPhone(rs.getString("phone"));
                s.setEmail(rs.getString("email"));
            } else {
                s = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }
    public String update(String name, String pno, String email) {
        String status = "";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            st.executeUpdate("update sookshmas set name='" + name + "',phone='" + pno + "',email='" + email + "' where slno= '" + se.getAttribute("id") + "' ");
            se.setAttribute("uname", name);
            status = "success";
        } catch (Exception e) {
            status = "failure";
            e.printStackTrace();
        }

        return status;
    }
    public String delete(int id) {
    	Statement ps=null;
    	int a;
    	String status="";
    	try {
    	ps=con.createStatement();
    	String sql="DELETE FROM sookshmas WHERE slno='"+id+"';";
    	a=ps.executeUpdate(sql);
    	if(a>0) {
    	status="success";
    	}
    	else {
    	status="failure";
    	}
    	}catch(Exception e) {
    	e.printStackTrace();
    	}
    	return status;
    	}
    	public ArrayList<Student> getUserDetails() {
    	Statement st=null;
    	ResultSet rs=null;
    	ArrayList<Student> al = new ArrayList<Student>();
    	try {
    	st = con.createStatement();
    	String qry = "select * from mvc.sookshmas;";
    	rs = st.executeQuery(qry);
    	while (rs.next()) {
    	Student p = new Student();
    	p.setId(rs.getString("slno"));
    	p.setName(rs.getString("name"));
    	p.setEmail(rs.getString("email"));
    	p.setPhone(rs.getString("phone"));
    	p.setDate(rs.getString("date"));
    	al.add(p);
    	}
    	} catch (Exception e) {
    	e.printStackTrace();
    	}
    	return al;
    	}
    	public boolean updatePassword(String email, String password) {
    		boolean check = false;
    		Statement st = null;
    		try {
    			st = con.createStatement();
    		int a = st.executeUpdate("update mvc.sookshmas set password='" + password + "'where email='" + email + "' ");
    		if (a > 0) {
    			check = true;
    		} else {
    			System.out.println("Update password failed ");
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}

    			return check;
    		}



}
