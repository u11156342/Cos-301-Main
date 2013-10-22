using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace cookietest
{
    public partial class _Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            string UserID = "DE47D939-8920-4CCB-B1D1-FE5FE54F2CC6";
            string UserName = "Smith";
           
            Session["UserID"] = UserID;
            Session["UserName"] = UserName;
            
        }
    }
}
