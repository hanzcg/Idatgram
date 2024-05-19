using Microsoft.AspNetCore.Mvc.Filters;

namespace idg_web_admin.Permisos
{
    public class ValidarSesionAttribute : ActionFilterAttribute
    {
        public override void OnActionExecuting(ActionExecutingContext context)
        {
            //if (HttpContext.Session.GetString("") != null)
            //{

            //}

            base.OnActionExecuting(context);
        }
    }
}
