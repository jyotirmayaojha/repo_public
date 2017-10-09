using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        Label4.Text = (string)Application["package"]; 
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        if (Page.IsValid)
        {
            String str = "Data Source=.\\SQLEXPRESS;Initial Catalog=aitproj;Integrated Security=True";
            SqlConnection con = new SqlConnection(str);
            con.Open();
            string query = "insert into package values('" + Application["package"] + "','" + TextBox1.Text + "','" + TextBox2.Text + "','" + TextBox3.Text + "','" + Application["username"] + "')";
            SqlCommand cmd = new SqlCommand(query, con);
            cmd.ExecuteNonQuery();
            con.Close();
            Server.Transfer("PaymentGateway.aspx");
        }
    }
    protected void Calendar1_SelectionChanged(object sender, EventArgs e)
    {
        Label5.Text= "You selected these dates:<br />";
        foreach (DateTime dt in Calendar1.SelectedDates)
        {
            Label5.Text += dt.ToLongDateString() + "<br />";
        }
    }
}