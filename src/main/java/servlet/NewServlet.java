/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Nook
 */

public class NewServlet extends HttpServlet {

    @Resource(lookup = "jdbc/oracle") 
    private DataSource datasource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Drug> drugs = new ArrayList<>();
        response.setContentType("text/html;charset=UTF-8");
        try (Connection connection = datasource.getConnection()) {
            Statement statement = connection.createStatement();
            String keyword = request.getParameter("drugcode");
            String sql = "Select * From DO_ITEM Where DRUGCODE LIKE '"+keyword+"%'";
            ResultSet resultQuery = statement.executeQuery(sql);
//            ResultSet resultQuery = statement.executeQuery("Select * From DO_ITEM");
            while (resultQuery.next()) {
                Drug drug = new Drug();
                drug.setDrugname(resultQuery.getString("NAME"));
                drug.setDrugCode(resultQuery.getString("DRUGCODE"));
                drug.setDrugId(resultQuery.getInt("ID"));
                drugs.add(drug);
            }
//            request.;

        } catch (SQLException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("drug", drugs);
        request.getRequestDispatcher("showdata.jsp").forward(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Drug> drugs = new ArrayList<>();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter(); Connection connection = datasource.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultQuery = statement.executeQuery("Select * From DO_ITEM");
            while (resultQuery.next()) {
                Drug drug = new Drug();
                drug.setDrugname(resultQuery.getString("NAME"));
                drug.setDrugCode(resultQuery.getString("DRUGCODE"));
                drug.setDrugId(resultQuery.getInt("ID"));
                System.out.println(drug.getDrugname());
                drugs.add(drug);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("showdata.jsp").forward(request, response);
    }

}
