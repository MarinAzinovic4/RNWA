using System.Web.Services;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MySql.Data;
using System.Data;

namespace WebApp
{
    /// <summary>
    /// Summary description for WebService1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class WebService1 : System.Web.Services.WebService
    {
        [System.Web.Services.WebMethod]
        public moneyConvert(int num, string cur1, string cur2)
        {
            if (num == ""){
                return "You must enter amount!";
            }
            if (cur1 == "EUR" && cur2 == "BAM"){
                return num * 1.94 + " BAM";
            }else if (cur1 == "BAM" && cur2 == "EUR"){
                return num / 1.94 + " EUR";
            }else if (cur1 == "BAM" && cur2 == "USD"){
                return num / 1.81 + " USD";
            }else if (cur1 == "USD" && cur2 == "BAM"){
                return num * 1.81 + " BAM";
            }

            return null;
        }
    }
}
