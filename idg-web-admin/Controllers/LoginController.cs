using idg_web_admin.Models;
using Microsoft.AspNetCore.Mvc;

namespace idg_web_admin.Controllers
{
    public class LoginController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public IActionResult VerifyAccess(AdminModel admin)
        {
            if (admin == null)
            {
                return RedirectToAction("Index");
            } 
            else
            {
                if(admin.User == "Admin" && admin.Password == "Admin123.")
                {
                    //HttpContext.Session.SetString("usuario", admin.User);
                    return RedirectToAction("Index", "Home");
                }
                else
                {
                    return RedirectToAction("Index");
                }
            }
        }
    }
}
