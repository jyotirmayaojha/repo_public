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
        Label1.Text = (string)Application["package"];
    }
    protected void Button1_Click(object sender, EventArgs e)
    {

        string str = "Data Source=.\\SQLEXPRESS;Initial Catalog=aitproj;Integrated Security=True";
        SqlConnection con = new SqlConnection(str);
        con.Open();
        if (CheckBox1.Checked == true)
        {
            string query = "update package set number_of_days='" + TextBox1.Text + "' where place_name='" + Application["package"] + "'";
            SqlCommand cmd = new SqlCommand(query, con);
            cmd.ExecuteNonQuery();
        }
        if (CheckBox2.Checked == true)
        {
            string query = "update package set number_of_days='" + TextBox2.Text + "' where place_name='" + Application["package"] + "'";
            SqlCommand cmd = new SqlCommand(query, con);
            cmd.ExecuteNonQuery();
        }
        if (CheckBox3.Checked == true)
        {
            string query = "update package set number_of_days='" + TextBox3.Text + "' where place_name='" + Application["package"] + "'";
            SqlCommand cmd = new SqlCommand(query, con);
            cmd.ExecuteNonQuery();
        }
    }
    protected void CheckBox1_CheckedChanged(object sender, EventArgs e)
    {
        if (CheckBox1.Checked == true)
            TextBox1.Visible = true;
        else
            TextBox1.Visible = false;
    }
    protected void CheckBox2_CheckedChanged(object sender, EventArgs e)
    {
        if (CheckBox2.Checked == true)
            TextBox2.Visible = true;
        else
            TextBox2.Visible = false;
    }
    protected void CheckBox3_CheckedChanged(object sender, EventArgs e)
    {
        if (CheckBox3.Checked == true)
            TextBox3.Visible = true;
        else
            TextBox3.Visible = false;
    }
}