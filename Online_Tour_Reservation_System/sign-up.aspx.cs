using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Data;
using System.Configuration;
using System.Data.Sql;
using System.Data.SqlClient;


public partial class sign_up : System.Web.UI.Page
{
    
        
         protected void Page_Load(object sender, EventArgs e)   
     {   

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

        try
        {
            
            String str = "Data Source=.\\SQLEXPRESS;Initial Catalog=aitproj;Integrated Security=True";
            SqlConnection con = new SqlConnection(str);
            con.Open();
            string unique_key = TextBox1.Text + TextBox2.Text;
            //string b = GetStringSha256Hash(a);
            string query = "insert into customer(customer_id,customer_name,customer_age,password1,password2,location,gender) values('" + unique_key + "','" + TextBox1.Text + "','" + TextBox2.Text + "','" + TextBox3.Text + "','" + TextBox4.Text + "','"+TextBox5.Text+"','"+ DropDownList1.SelectedItem.Text +"')";
            SqlCommand cmd = new SqlCommand(query, con);
            cmd.ExecuteNonQuery();
            con.Close();
            Label5.Visible=true;
            Application["username"] = unique_key;
            Session["UniqueKey"] = unique_key;
            Response.Redirect("login.aspx");
        }
        catch(Exception err)
        {
            Label5.Visible = true;
            Label5.ForeColor=System.Drawing.Color.Red;
            Label5.Text = err.Message;
        }
    }
}