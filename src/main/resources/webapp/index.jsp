<%@ page session="true"%>
<%
   Cookie[] cookies = request.getCookies();
   Cookie cookie = null;
   if( cookies != null ){
      for (int i = 0; i < cookies.length; i++){
         if(cookies[i].getName().equals("user")){
            cookie = cookies[i];
         }
      }
   }
%>

<html>

<body>

    <h1>Kennel</h1>

    <a href="logout" id="logout">Logout</a>

    <%
    if(cookie != null) {
        out.println("<p>Hello " + cookie.getValue() + "</p>");
    }

    %>

    <h2>Current state</h2>

    <%
    if(response.getHeader("kennel-report") != null){
        out.println("<p>" + response.getHeader("kennel-report") + "</p>");
    }
    %>

    <br/><br/><hr/>

    <h2>Book a dog into the kennel</h2>

    <%
    if(response.getHeader("bk-msg") != null){
        out.println("<p>" + response.getHeader("bk-msg") + "</p>");
    }

    if(response.getHeader("bk-name-error") != null){
        out.println("<p>" + response.getHeader("bk-name-error") + "</p>");
    }
    %>

    <form action="/" method="POST">
        <label for="book-dog-name">Name of dog</label><br/>
        <input type="text" id="book-dog-name" name="bookInDog" placeholder="Name of the dog"><br/><br/>

        <label for="dog-size-select">Dog Size</label><br/>
        <select name="dog-size" id="dog-size-select">
            <option value="small">Small</option>
            <option value="medium">Medium</option>
            <option value="large">Large</option>
        </select><br/><br/>

        <input type="submit" value="Book in dog"/>
    </form>

    <br/><br/>

    <h2>Checkout a dog from the kennel</h2>

    <%
    if(response.getHeader("co-msg") != null){
        out.println("<p>" + response.getHeader("co-msg") + "</p>");
    }

    if(response.getHeader("co-name-error") != null){
        out.println("<p>" + response.getHeader("co-name-error") + "</p>");
    }
    %>

    <form action="/" method="POST">

        <label for="checkout-dog-name">Name of dog</label><br/>
        <input type="text" id="checkout-dog-name" name="checkoutDog" placeholder="Name of the dog"><br/><br/>

        <input type="submit" value="Checkout dog"/>
    </form>

    <hr/>
    <br/>

    <p>
        Local time at Kennel:
        <%
        out.println(response.getHeader("Date"));
        %>
    </p>
</body>
</html>