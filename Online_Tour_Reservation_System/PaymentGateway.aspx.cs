using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.Sql;
using System.Data.SqlClient;
using System.Data;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        Label3.Text = "Your selected package is : " + Application["package"];

        if (Page.PreviousPage != null && Page.PreviousPage.Title == "admin.aspx")
        {
            Button3.Visible = false;
            Label4.Text = "<b>Validated. Make payment at your nearest outlet.</b>";
        }

    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        string str = "Data Source=.\\SQLEXPRESS;Initial Catalog=aitproj;Integrated Security=True";
        SqlConnection con = new SqlConnection(str);
        con.Open();
        string query = "select * from employee where '" + TextBox1.Text + "'=name";
        SqlCommand cmd = new SqlCommand(query, con);
        SqlDataReader rd = cmd.ExecuteReader();
        if (!rd.Read())
        {
            Label4.Visible = true;
            Label4.Text = "Staff doesn't exist. Ghosts can't make transactions.";
        }
        else
        {
            Label4.Visible = true;
            
            Label4.Text = "Transaction ID generated :" + Session["TransactionID"].ToString();
            Button3.Visible = true;
        }
    }
    protected void Button2_Click(object sender, EventArgs e)
    {
      
        Server.Transfer("UpdateTourPackage.aspx");
    }
    protected void Button3_Click(object sender, EventArgs e)
    {
        Server.Transfer("admin.aspx");

    }
    protected void Button4_Click(object sender, EventArgs e)
    {
        string str = "Data Source=.\\SQLEXPRESS;Initial Catalog=aitproj;Integrated Security=True";
        SqlConnection con = new SqlConnection(str);
        con.Open();
        string query="delete from package where transaction_id="+ Session["TransactionID"];
        SqlCommand cmd = new SqlCommand(query, con);
        cmd.ExecuteNonQuery();
        con.Close();
        Response.Redirect("home.aspx");
    }
}