package com.example.Bai6.Cart;

import com.example.Bai6.model.ItemOrder;
import com.example.Bai6.model.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;

@WebServlet("/cart")
public class Cart extends HttpServlet {
    private String itemID;
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        itemID = request.getParameter("itemID");
        ShoppingCart cart;
        synchronized(session) {
            cart = (ShoppingCart)session.getAttribute("shoppingCart");
            // New visitors get a fresh shopping cart.
            // Previous visitors keep using their existing cart.
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("shoppingCart", cart);
            }
            if (itemID != null) {
                String numItemsString =
                        request.getParameter("numItems");
                if (numItemsString == null) {
                    // If request specified an ID but no number,
                    // then customers came here via an "Add Item to Cart"
                    // button on a catalog page.
                    cart.addItem(itemID);
                } else {
                    // If request specified an ID and number, then
                    // customers came here via an "Update Order" button
                    // after changing the number of items in order.
                    // Note that specifying a number of 0 results
                    // in item being deleted from cart.
                    int numItems;
                    try {
                        numItems = Integer.parseInt(numItemsString);
                    } catch(NumberFormatException nfe) {
                        numItems = 1;
                    }
                    cart.setNumOrdered(itemID, numItems);
                }
            }
        }
        // Whether or not the customer changed the order, show
        // order status.
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Status of Your Order";
        String docType =
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                        "Transitional//EN\">\n";
        out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=\"CENTER\">" + title + "</H1>");
        synchronized(session) {
            List itemsOrdered = cart.getItemsOrdered();
            if (itemsOrdered.size() == 0) {
                out.println("<H2><I>No items in your cart...</I></H2>");
            } else {
                // If there is at least one item in cart, show table
                // of items ordered.

                String itemID = request.getParameter("itemID");
                out.println ("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
                        "<TR BGCOLOR=\"#FFAD00\">\n" +
                        "  <TH>Item ID<TH>Description\n" +
                        "  <TH>Unit Cost<TH>Number<TH>Total Cost");
                ItemOrder order;
                // Rounds to two decimal places, inserts dollar
                // sign (or other currency symbol), etc., as
                // appropriate in current Locale.
                NumberFormat formatter =
                        NumberFormat.getCurrencyInstance();
                // For each entry in shopping cart, make
                // table row showing ID, description, per-item
                // cost, number ordered, and total cost.
                // Put number ordered in textfield that user
                // can change, with "Update Order" button next
                // to it, which resubmits to this same page
                // but specifying a different number of items.
                for(int i=0; i<itemsOrdered.size(); i++) {
                    order = (ItemOrder)itemsOrdered.get(i);
                    out.println
                            ("<TR>\n" +
                                    "  <TD>" + order.getItemId() + "\n" +
                                    "  <TD>" + order.getShortDescription() + "\n" +
                                    "  <TD>" +
                                    formatter.format(order.getUnitCost()) + "\n" +
                                    "  <TD>" +
                                    "<FORM>\n" +  // Submit to current URL
                                    "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\"\n" +
                                    "       VALUE=\"" + order.getItemId() + "\">\n" +
                                    "<INPUT TYPE=\"TEXT\" NAME=\"numItems\"\n" +
                                    "       SIZE=3 VALUE=\"" +
                                    order.getNumberBook() + "\">\n" +
                                    "<SMALL>\n" +
                                    "<INPUT TYPE=\"SUBMIT\"\n "+
                                    "       VALUE=\"Update Order\">\n" +
                                    "</SMALL>\n" +
                                    "</FORM>\n" +
                                    "  <TD>" +
                                    formatter.format(order.getTotalCost()));
                }
                String checkoutURL =
                        response.encodeURL("view-books");
                // "Proceed to Checkout" button below table
                out.println
                        ("</TABLE>\n" +
                                "<FORM ACTION=\"" + checkoutURL + "\">\n" +
                                "<BIG><CENTER>\n" +
                                "<INPUT TYPE=\"SUBMIT\"\n" +
                                "       VALUE=\"Continue shopping\">\n" +
                                "</CENTER></BIG></FORM>");
            }
            out.println("</BODY></HTML>");
        }
    }
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
//        String itemId = request.getParameter("itemID");
//
//    }
}
