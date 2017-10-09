<%@ Page Title="" Language="C#" MasterPageFile="~/Mst.master" AutoEventWireup="true" CodeFile="set_package_details.aspx.cs" Inherits="_Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <p>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
    <asp:MultiView ID="MultiView1" runat="server" ActiveViewIndex="0" >  
        <asp:View ID="View1" runat="server">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:DropDownList ID="lstForeColor" runat="server" AutoPostBack="True" OnSelectedIndexChanged="lstForeColor_SelectedIndexChanged" Height="86px" Width="211px">
                <asp:ListItem>Kottayam</asp:ListItem>
                <asp:ListItem>Shillong</asp:ListItem>
                <asp:ListItem>Jaipur</asp:ListItem>
                <asp:ListItem>Manali</asp:ListItem>
             </asp:DropDownList>
        </asp:View>
        <asp:View ID="View2" runat="server">
                &nbsp;&nbsp;
                <asp:Calendar ID="Calendar1" runat="server" OnDayRender="Calendar1_DayRender" OnSelectionChanged="Calendar1_SelectionChanged" style="margin-left: 259px"></asp:Calendar>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <br />
                <asp:Label ID="Label1" runat="server" Text="Start Date"></asp:Label>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <br />
            <br />
            <asp:Button ID="Button2" runat="server" OnClick="Button2_Click" Text="Confirm" />
            &nbsp;&nbsp;
            <br />
            <br />
        </asp:View>
        <asp:View ID="View3" runat="server">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <br />
            &nbsp;&nbsp;<asp:Calendar ID="Calendar2" runat="server" OnSelectionChanged="Calendar2_SelectionChanged" OnDayRender="Calendar2_DayRender" style="margin-left: 256px" ></asp:Calendar>
                &nbsp;<br />
            <asp:Label ID="Label6" runat="server" Text="End Date"></asp:Label>
                <br />
            <br />
            <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="Confirm" />
            &nbsp;&nbsp;
                <br />
                <br />
        </asp:View>
        <asp:View ID="View4" runat="server">
            <asp:TextBox ID="TextBox1" runat="server" placeholder="Number of people" AutoPostBack="True"></asp:TextBox>
            <asp:RangeValidator ID="RangeValidator1" runat="server" ControlToValidate="TextBox1" EnableClientScript="False" ErrorMessage="Not an Integer/Not in Range" MaximumValue="100" MinimumValue="1" Type="Integer"></asp:RangeValidator>
            <br />
            <asp:Button ID="Button3" runat="server" Text="Confirm" OnClick="Button3_Click" />
        </asp:View>
        <asp:View ID="View5" runat="server" OnActivate="View5_Activate">      
            
            <asp:Label ID="Label11" runat="server" Text="Summary"></asp:Label>
            <br />
            
            <br />
            <asp:Label ID="Label7" runat="server" Text="Starting Date"></asp:Label>
            <br />
            <asp:Label ID="Label8" runat="server" Text="Ending Date"></asp:Label>
            <br />
            <asp:Label ID="Label9" runat="server" Text="Number of People"></asp:Label>
            <br />
            <asp:Label ID="Label10" runat="server" Text="Total Cost"></asp:Label>
            <br />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:Button ID="Button4" runat="server" OnClick="Button4_Click" Text="Proceed To Payment" />
            <br />
            
        </asp:View>
    </asp:MultiView>
    </p>
</asp:Content>

