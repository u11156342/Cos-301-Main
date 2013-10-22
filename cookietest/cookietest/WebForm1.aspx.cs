using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace cookietest
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

          

            HttpCookie myCookie = new HttpCookie("UserSettings");
            myCookie.Expires = DateTime.Now.AddDays(20d);
            myCookie["UserID"] = Convert.ToString(Session["UserID"]); // the Session["UserID"] part we will need the userid so that we can fetch the appropiate  characters
            Response.Cookies.Add(myCookie);

        }
    }
}