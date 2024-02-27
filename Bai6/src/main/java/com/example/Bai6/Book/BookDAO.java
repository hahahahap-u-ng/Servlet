package com.example.Bai6.Book;

import com.example.Bai6.context.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = new DBContext().getConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
        return  con;
    }

    public static int save(Book book) {
        int status = 0;
        try  {

            Connection con = BookDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into book(id,longDescription,shortDescription,cost) values (?,?,?,?)");
            ps.setString(1, book.getID());
            ps.setString(2, book.getLongDescription());
            ps.setString(3, book.getShortDescription());
            ps.setInt(4, book.getCost());
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return  status;
    }

    public  static int update(Book book) {
        int status=0;
        try{
            Connection con=BookDAO.getConnection();
            PreparedStatement ps=con.prepareStatement("update book set id=?,longDescription=?,shortDescription=?,cost=? where id=?");
            ps.setString(1,book.getID());
            ps.setString(2,book.getLongDescription());
            ps.setString(3,book.getShortDescription());
            ps.setInt(4,book.getCost());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }

    public static int delete(String id){
        int status=0;
        try{
            Connection con=BookDAO.getConnection();
            PreparedStatement ps=con.prepareStatement("delete from book where id=?");
            ps.setString(1,id);
            status=ps.executeUpdate();

            con.close();
        }catch(Exception e){e.printStackTrace();}

        return status;
    }

    public static List<Book> getAllBooks() {
        List<Book> list = new ArrayList<Book>();

        try {
            Connection con = BookDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from book");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book e = new Book();
                e.setID(rs.getString(1));
                e.setLongDescription(rs.getString(2));
                e.setShortDescription(rs.getString(3));
                e.setCost(rs.getInt(4));
                list.add(e);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static Book getBookByID(String id){
         Book e=new Book();

        try{
            Connection con=BookDAO.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from book where id=?");
            ps.setString(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                e.setID(rs.getString(1));
                e.setLongDescription(rs.getString(2));
                e.setShortDescription(rs.getString(3));
                e.setCost(rs.getInt(4));
            }
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return e;
    }

}
