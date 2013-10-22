using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace cookietest
{
    public partial class WebForm2 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Cookies["UserSettings"] != null)
            {
                string userSettings;
                if (Request.Cookies["UserSettings"]["UserID"] != null)
                { userSettings = Request.Cookies["UserSettings"]["UserID"];
                System.Web.HttpContext.Current.Response.Write("<SCRIPT LANGUAGE=\"\"JavaScript\"\">alert(\"Hello this is an Alert" + userSettings + "\")</SCRIPT>");
                }
            }
        }
    }
}