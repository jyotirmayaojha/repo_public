using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.Sql;
using System.Data.SqlClient;

public partial class admin : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        Page.Title = "admin.aspx";

    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        string str = "Data Source=.\\SQLEXPRESS;Initial Catalog=aitproj;Integrated Security=True";
        SqlConnection con = new SqlConnection(str);
        con.Open();
        string query = "select * from employee where '"+TextBox1.Text+"'=name and '"+TextBox2.Text+"'=password";
        SqlCommand cmd = new SqlCommand(query, con);
        SqlDataReader rd = cmd.ExecuteReader();
        if (!rd.Read())
        {
            Label3.Visible = true;
            Label3.Text = "Incorrect credentials<br>";
        }
        else
        {
            Server.Transfer("PaymentGateway.aspx");
         }
    }
}