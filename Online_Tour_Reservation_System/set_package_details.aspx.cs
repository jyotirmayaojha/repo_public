using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Data.Sql;
using System.Data;

public partial class _Default : System.Web.UI.Page
{
    string str = "Data Source=.\\SQLEXPRESS;Initial Catalog=aitproj;Integrated Security=True";
    string query = "";
    SqlConnection con;
    protected void Page_Load(object sender, EventArgs e)
    {
        con = new SqlConnection(str);
        con.Open();
        
    }
    protected void MultiView1_ActiveViewChanged(object sender, EventArgs e)
    {

    }
    protected void Calendar1_SelectionChanged(object sender, EventArgs e)
    {
        foreach (DateTime dt in Calendar1.SelectedDates)
        {
            Label1.Text = dt.ToLongDateString();
            Session["SelectedDate"] = dt;
        }
        
    }
    protected void lstForeColor_SelectedIndexChanged(object sender, EventArgs e)
    {
        Session["Place"] = lstForeColor.SelectedItem.Text;
        MultiView1.SetActiveView(View2);
    }
    protected void Button2_Click(object sender, EventArgs e)
    {
        Session["StartDate"] = Session["SelectedDate"]; 
        MultiView1.SetActiveView(View3);
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        Session["EndDate"] = Session["SelectedDate"];
        MultiView1.SetActiveView(View4);
    }
    protected void Button3_Click(object sender, EventArgs e)
    {
        Session["NumberOfPeople"] = Convert.ToInt64(TextBox1.Text);
        MultiView1.SetActiveView(View5);
    }
    protected void Calendar2_SelectionChanged(object sender, EventArgs e)
    {
        foreach (DateTime dt in Calendar2.SelectedDates)
        {
            Label6.Text = dt.ToLongDateString();
            Session["SelectedDate"] = dt;
        }
    }
    protected void View5_Activate(object sender, EventArgs e)
    {
        DateTime start_date = (DateTime)Session["StartDate"];
        DateTime end_date = (DateTime)Session["EndDate"];
        Int64 number_of_people = (Int64)Session["NumberOfPeople"];
        Label7.Text += ": " + (start_date).ToLongDateString();
        Label8.Text += ": " + (end_date).ToLongDateString();
        Label9.Text += ": " + (number_of_people).ToString();
        double daysBetween = (end_date - start_date).TotalDays + 1;
        Session["NoOfDays"] = daysBetween;
        double total_cost = daysBetween*number_of_people*100;
        Session["TotalCost"] = total_cost;
        Label10.Text += ": " + total_cost.ToString();
    }
    protected void Button4_Click(object sender, EventArgs e)
    {
        Random r = new Random();
        int t_ID = r.Next(1000, 10000);
        Session["TransactionID"] = t_ID.ToString() + Session["Age"].ToString();
        query = "insert into package(place_name, number_of_days, cost, number_of_people, customer_name, transaction_id, start_date) values('" + Session["Place"] + "','" + Session["NoOfDays"].ToString() + "','" + Session["TotalCost"].ToString() + "','" + Session["NumberOfPeople"] + "','" + Session["UserName"] + "','" + Session["TransactionID"].ToString() + "','" + Session["StartDate"].ToString() + "')";
        SqlCommand cmd = new SqlCommand(query, con);
        cmd.ExecuteNonQuery();
        con.Close();
        Response.Redirect("PaymentGateway.aspx");
    }
    protected void Calendar1_DayRender(object sender, DayRenderEventArgs e)
    {
        if (e.Day.Date.CompareTo(DateTime.Now.Date) < 0)
        {
            e.Cell.Text = e.Day.DayNumberText;

        }
    }
    protected void Calendar2_DayRender(object sender, DayRenderEventArgs e)
    {
        if (e.Day.Date.CompareTo(((DateTime)Session["StartDate"]).AddDays(1)) < 0)
        {
            e.Cell.Text = e.Day.DayNumberText;
        }

    }
}