using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using System.Data.Sql;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void Button2_Click(object sender, EventArgs e)
    {
        Server.Transfer("set_package_details.aspx");
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        Response.Redirect("upate_details.aspx");
    }
    protected void Button3_Click(object sender, EventArgs e)
    {
        Response.Redirect("booked_package.aspx");    
    }
    protected void Button4_Click(object sender, EventArgs e)
    {
        Response.Redirect("profile.aspx");

    }
}