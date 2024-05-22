using idg_web_admin.Data;
using idg_web_admin.Models;
using Microsoft.AspNetCore.Mvc;

namespace idg_web_admin.Controllers
{
    public class UsersController : Controller
    {
        UserData userData = new();

        [HttpGet]
        public IActionResult Index()
        {
            List<UserModel> oLista = userData.GetAll();

            return View(oLista);
        }

        public IActionResult Details(int id) {
            UserModel userModel = userData.GetById(id); 

            return View(userModel); 
        }


        public IActionResult Create(UserModel userModel)
        {
            UserData usr = new();
            bool rpta = usr.Add(userModel);

            if (rpta)
                return RedirectToAction("Index");
            else
                return View();
        }

        public IActionResult Edit(int id)
        {
            UserModel userModel = userData.GetById(id);

            return View(userModel);
        }

        [HttpPost]
        public IActionResult Edit(UserModel userModel)
        {
            UserData usr = new();
            bool rpta = usr.Upd(userModel);

            if (rpta)
                return RedirectToAction("Index");
            else
                return View();
        }

        public IActionResult Delete(int id)
        {
            UserModel userModel = userData.GetById(id);

            return View(userModel);
        }

        [HttpPost]
        public IActionResult Delete(UserModel userModel)
        {
            UserData usr = new();
            bool rpta = usr.Del(userModel.Id);

            if (rpta)
                return RedirectToAction("Index");
            else
                return View();
        }
    }
}
