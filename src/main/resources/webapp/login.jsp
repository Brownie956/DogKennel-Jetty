<html>

    <body>
        <h1>Login</h1>

        <%
        if(response.getHeader("message") != null){
            out.println("<p>" + response.getHeader("message") + "</p>");
        }
        %>

        <form action="login" method="POST">

            <%
                if(response.getHeader("user-error") != null){
                    out.println("<p>" + response.getHeader("user-error") + "</p>");
                }
            %>
            <label for="username">Username</label><br/>
            <input id="username" type="text" name="username" placeholder="username"><br/></br/>

            <%
                if(response.getHeader("pass-error") != null){
                    out.println("<p>" + response.getHeader("pass-error") + "</p>");
                }
            %>
            <label for="password">Password</label><br/>
            <input id="password" type="password" name="password" placeholder="password"><br/>

            <br/>
            <input type="submit" value="Login"/>
        </form>

    </body>

</html>