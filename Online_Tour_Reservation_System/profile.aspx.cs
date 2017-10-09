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
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void Table1_Init(object sender, EventArgs e)
    {
        string str = "Data Source=.\\SQLEXPRESS;Initial Catalog=aitproj;Integrated Security=True";
        SqlConnection con = new SqlConnection(str);
        con.Open();
        string query = "select * from customer where customer_id='" + Session["UniqueKey"] + "'";
        SqlCommand cmd = new SqlCommand(query, con);
        SqlDataReader rd = cmd.ExecuteReader();
        TableRow NewRow1 = new TableRow();

        TableCell NewCell11 = new TableCell();
        Label newLable11 = new Label();
        newLable11.Text = "Name";
        NewCell11.Controls.Add(newLable11);
        NewRow1.Cells.Add(NewCell11);

        TableRow NewRow2 = new TableRow();

        TableCell NewCell21 = new TableCell();
        Label newLable21 = new Label();
        newLable21.Text = "Age";
        NewCell21.Controls.Add(newLable21);
        NewRow2.Cells.Add(NewCell21);

        TableRow NewRow3 = new TableRow();

        TableCell NewCell31 = new TableCell();
        Label newLable31 = new Label();
        newLable31.Text = "Location";
        NewCell31.Controls.Add(newLable31);
        NewRow3.Cells.Add(NewCell31);

        TableRow NewRow4 = new TableRow();

        TableCell NewCell41 = new TableCell();
        Label newLable41 = new Label();
        newLable41.Text = "Gender";
        NewCell41.Controls.Add(newLable41);
        NewRow4.Cells.Add(NewCell41);

        while (rd.Read())
        {
           
            TableCell NewCell12 = new TableCell();
            Label newLable12 = new Label();
            newLable12.Text = rd["customer_name"].ToString();
            NewCell12.Controls.Add(newLable12);
            NewRow1.Cells.Add(NewCell12);

            
            TableCell NewCell22 = new TableCell();
            Label newLable22 = new Label();
            newLable22.Text = rd["customer_age"].ToString();
            NewCell22.Controls.Add(newLable22);
            NewRow2.Cells.Add(NewCell22);

            TableCell NewCell32 = new TableCell();
            Label newLable32 = new Label();
            newLable32.Text = rd["location"].ToString();
            NewCell32.Controls.Add(newLable32);
            NewRow3.Cells.Add(NewCell32);

            TableCell NewCell42 = new TableCell();
            Label newLable42 = new Label();
            newLable42.Text = rd["gender"].ToString();
            NewCell42.Controls.Add(newLable42);
            NewRow4.Cells.Add(NewCell42);

        }
        Table1.Rows.Add(NewRow1);
        Table1.Rows.Add(NewRow2);
        Table1.Rows.Add(NewRow3);
        Table1.Rows.Add(NewRow4);

        con.Close();
    }
}