<%-- 
    Document   : showdata
    Created on : Apr 22, 2014, 1:23:46 PM
    Author     : Nook
--%>

<%@page import="servlet.Drug"%>
<%@page import="java.util.Locale.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>JSP Page </h1>
        <table>
        <%
            ArrayList<Drug> list = (ArrayList<Drug>) request.getAttribute("drug");
            for (Drug drug : list) {
                out.println("<tr>");
                out.println("<td>DrugId : "+drug.getDrugId()+"</td>");
                out.println("<td>DrugCode : "+drug.getDrugCode()+"</td>");
                out.println("<td>DrugName : "+drug.getDrugname()+"</td>");         
                out.println("<tr>");
            }
        %>
        </table>
    </body>
</html>
