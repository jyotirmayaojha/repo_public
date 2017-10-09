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

    }
    protected void Table1_Init(object sender, EventArgs e)
    {
        TableRow NewRow1 = new TableRow();

        TableCell NewCell1 = new TableCell();
        Label newLable1 = new Label();
        newLable1.Text = "Location";
        NewCell1.Controls.Add(newLable1);
        NewRow1.Cells.Add(NewCell1);

        TableCell NewCell2 = new TableCell();
        Label newLable2 = new Label();
        newLable2.Text = "Number of Days";
        NewCell2.Controls.Add(newLable2);
        NewRow1.Cells.Add(NewCell2);

        TableCell NewCell3 = new TableCell();
        Label newLable3 = new Label();
        newLable3.Text = "Number of Guests";
        NewCell3.Controls.Add(newLable3);
        NewRow1.Cells.Add(NewCell3);

        TableCell NewCell4 = new TableCell();
        Label newLable4 = new Label();
        newLable4.Text = "Start Date";
        NewCell4.Controls.Add(newLable4);
        NewRow1.Cells.Add(NewCell4);

        TableCell NewCell5 = new TableCell();
        Label newLable5 = new Label();
        newLable5.Text = "Booking Name";
        NewCell5.Controls.Add(newLable5);
        NewRow1.Cells.Add(NewCell5);

        TableCell NewCell6 = new TableCell();
        Label newLable6 = new Label();
        newLable6.Text = "Transaction ID";
        NewCell6.Controls.Add(newLable6);
        NewRow1.Cells.Add(NewCell6);

        Table1.Rows.Add(NewRow1);

        String str = "Data Source=.\\SQLEXPRESS;Initial Catalog=aitproj;Integrated Security=True";
        SqlConnection con = new SqlConnection(str);
        con.Open();
        string query = "select * from package where transaction_id='" + Session["TransactionID"] + "'";
        SqlCommand cmd = new SqlCommand(query, con);
        SqlDataReader rd = cmd.ExecuteReader();
        while (rd.Read())
        {
            TableRow NewRow2 = new TableRow();

            TableCell NewCell12 = new TableCell();
            Label newLable12 = new Label();
            newLable12.Text = rd["place_name"].ToString();
            NewCell12.Controls.Add(newLable12);
            NewRow2.Cells.Add(NewCell12);

            TableCell NewCell22 = new TableCell();
            Label newLable22 = new Label();
            newLable22.Text = rd["number_of_days"].ToString();
            NewCell22.Controls.Add(newLable22);
            NewRow2.Cells.Add(NewCell22);

            TableCell NewCell32 = new TableCell();
            Label newLable32 = new Label();
            newLable32.Text = rd["number_of_people"].ToString();
            NewCell32.Controls.Add(newLable32);
            NewRow2.Cells.Add(NewCell32);

            TableCell NewCell42 = new TableCell();
            Label newLable42 = new Label();
            newLable42.Text = rd["start_date"].ToString();
            NewCell42.Controls.Add(newLable42);
            NewRow2.Cells.Add(NewCell42);

            TableCell NewCell52 = new TableCell();
            Label newLable52 = new Label();
            newLable52.Text = rd["customer_name"].ToString();
            NewCell52.Controls.Add(newLable52);
            NewRow2.Cells.Add(NewCell52);

            TableCell NewCell62 = new TableCell();
            Label newLable62 = new Label();
            newLable62.Text = rd["transaction_id"].ToString();
            NewCell62.Controls.Add(newLable62);
            NewRow2.Cells.Add(NewCell62);

            Table1.Rows.Add(NewRow2);
        }

        con.Close();

    }
}