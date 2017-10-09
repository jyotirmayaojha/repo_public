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
    protected void ImageButton1_Click(object sender, ImageClickEventArgs e)
    {
        On_ImageButton_Click(ImageButton1.AlternateText);
        Server.Transfer("CompletePackage.aspx");
        
    }
    protected void ImageButton3_Click(object sender, ImageClickEventArgs e)
    {
        On_ImageButton_Click(ImageButton3.AlternateText);
        Server.Transfer("CompletePackage.aspx");
    }
    protected void ImageButton4_Click(object sender, ImageClickEventArgs e)
    {
        On_ImageButton_Click(ImageButton4.AlternateText);
        Server.Transfer("CompletePackage.aspx");
    }
    protected void ImageButton2_Click(object sender, ImageClickEventArgs e)
    {
        On_ImageButton_Click(ImageButton2.AlternateText);
        Server.Transfer("CompletePackage.aspx");
    }
    protected void On_ImageButton_Click(String ImageName)
    {
        Application["package"] = ImageName.ToString();
        string str = "Data Source=.\\SQLEXPRESS;Initial Catalog=aitproj;Integrated Security=True";
        SqlConnection con = new SqlConnection(str);
        con.Open();
        string select_query = "select destinations from customer where customer_id='" + Application["username"] + "'";
        SqlCommand cmd = new SqlCommand(select_query, con);
        SqlDataReader rd = cmd.ExecuteReader();
        string destination = "";
        while (rd.Read())
        {
            destination = rd["destinations"].ToString();
        }
        con.Close();

        con.Open();
        //string new_str= destination + "," + ImageName ;
        string update_query = "update customer set destinations='" +ImageName+ "' where customer_id='" + Application["username"] + "'";
        //string query = "update customer set destinations='" + ImageName + "' where customer_id='arpangooner'";
        SqlCommand comd = new SqlCommand(update_query, con);
        comd.ExecuteNonQuery();
        con.Close();

    }

}