using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.Sql;
using System.Data.SqlClient;

public partial class _Default : System.Web.UI.Page
{
    
    protected void Page_Load(object sender, EventArgs ev)
    {
       
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
    protected void CheckBox4_CheckedChanged(object sender, EventArgs e)
    {
        if (CheckBox4.Checked == true)
            TextBox4.Visible = true;
        else
            TextBox4.Visible = false;
       
    }
    protected void CheckBox5_CheckedChanged(object sender, EventArgs ev)
    {
        if (CheckBox5.Checked == true)
            TextBox5.Visible = true;
        else
            TextBox5.Visible = false;
    }
    protected void Button1_Click(object sender, EventArgs ev)
    {
        string a, b, c, d, e;
      
            a = TextBox1.Text.ToString();
        

           b = TextBox2.Text.ToString();
 
            c = TextBox3.Text.ToString();
   
            d = TextBox4.Text.ToString();
     
            e = TextBox5.Text.ToString();
        
      
        string str = "Data Source=.\\SQLEXPRESS;Initial Catalog=aitproj;Integrated Security=True";
        SqlConnection con = new SqlConnection(str);
        con.Open();
    //    string update_query = "update customer set destinations='" + new_str + "' where customer_id='" + Application["username"] + "'";
        if (CheckBox1.Checked == true)
        {
            string query = "update customer set customer_name='" + a + "' where customer_id='" + Application["username"] + "'";
            SqlCommand cmd = new SqlCommand(query, con);
            cmd.ExecuteNonQuery();
        }
        if (CheckBox2.Checked == true)
        {
            string query = "update customer set customer_age='" + b + "' where customer_id='" + Application["username"] + "'";
            SqlCommand cmd = new SqlCommand(query, con);
            cmd.ExecuteNonQuery();
        }
        if (CheckBox3.Checked == true)
        {
            string query = "update customer set gender='" + c + "' where customer_id='" + Application["username"] + "'";
            SqlCommand cmd = new SqlCommand(query, con);
            cmd.ExecuteNonQuery();
        }
        if (CheckBox4.Checked == true)
        {
            string query = "update customer set location='" + d + "' where customer_id='" + Application["username"] + "'";
            SqlCommand cmd = new SqlCommand(query, con);
            cmd.ExecuteNonQuery();
        }
        if (CheckBox5.Checked == true)
        {
            string query = "update customer set password1='"+e+"' where customer_id='" + Application["username"] + "'";
            SqlCommand cmd = new SqlCommand(query, con);
            cmd.ExecuteNonQuery();
             query = "update customer set password2='" + e + "' where customer_id='" + Application["username"] + "'";
             cmd = new SqlCommand(query, con);
            cmd.ExecuteNonQuery();
            string cool = a + e;
             query = "update customer set customer_id='" + cool + "' where customer_id='" + Application["username"] + "'";
             cmd = new SqlCommand(query, con);
            cmd.ExecuteNonQuery();
        }

        
        con.Close();
    }
    protected void CheckBox3_CheckedChanged1(object sender, EventArgs e)
    {

    }

}