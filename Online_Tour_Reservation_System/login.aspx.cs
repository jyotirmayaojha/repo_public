using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using System.Data.Sql;

public partial class login : System.Web.UI.Page
{

    
    protected void Page_Load(object sender, EventArgs e)
    {
        Label4.Visible = false;
    }
    internal static string GetStringSha256Hash(string text)
    {
        if (String.IsNullOrEmpty(text))
            return String.Empty;

        using (var sha = new System.Security.Cryptography.SHA256Managed())
        {
            byte[] textData = System.Text.Encoding.UTF8.GetBytes(text);
            byte[] hash = sha.ComputeHash(textData);
            return BitConverter.ToString(hash).Replace("-", String.Empty);
        }
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        string str = "Data Source=.\\SQLEXPRESS;Initial Catalog=aitproj;Integrated Security=True";
        SqlConnection con;
        string a = TextBox1.Text;
        string b = TextBox2.Text;
        con = new SqlConnection(str);
        con.Open();
        string query = "select * from customer where '"+a+"'=customer_name and '"+b+"'= password1";
        SqlCommand cmd = new SqlCommand(query, con);
        SqlDataReader rd = cmd.ExecuteReader();
        if (!rd.Read())
        {
            Label4.Visible = true;
            Label4.Text = "You aren't our current user, kindly register yourself.<br>";
            Button2.Visible = true;
        }
        else
        {
            Label4.Visible = true;
            Label4.Text = "Welcome!<br>";
            Application["CustomerName"]=TextBox1.Text;
            Session["UserName"] = rd["customer_name"]; 
            Session["Age"] = rd["customer_age"];
            Session["UniqueKey"] = rd["customer_id"]; 
            Button2.Visible = false;
            Server.Transfer("home.aspx");
        }
        con.Close();
    }
    protected void Button3_Click(object sender, EventArgs e)
    {
        Response.Redirect("admin.aspx");
    }
}