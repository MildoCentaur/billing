package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.Role;
import ar.com.adriabe.model.RolePermission;
import ar.com.adriabe.model.User;
import ar.com.adriabe.services.UserService;
import ar.com.adriabe.web.controllers.adapters.UserJSONAdapter;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import ar.com.adriabe.web.model.WebPageResponse;
import ar.com.adriabe.web.model.json.UserPermissionJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserPageController extends AbstractPageController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/list-users", method = RequestMethod.GET)
    public String  searchUser(Model model) {

        List<User> users = userService.findAll();
        WebPageModel webPageModel = createWebPageModel("Listado de Usuarios");
        model.addAttribute("page",webPageModel);
        model.addAttribute("users", users);
        return "users/list-users";
    }

    @RequestMapping(value = "/assign-permission", method = RequestMethod.GET)
    public String  assignPermissionToUser(Model model) {

        List<User> users = userService.findAll();
        List<Role> roles = userService.findAllRoles();
        WebPageModel webPageModel = createWebPageModel("Formulario de asignación de permisos");
        model.addAttribute("page",webPageModel);
        model.addAttribute("users",users);
        model.addAttribute("roles",roles);
        return "users/assign-permission-user";
    }
    @RequestMapping(value = "/assign-permission-process", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    WebPageResponse processAssignPermissionPage(@RequestBody UserPermissionJSON[] userPermissions, BindingResult result, SessionStatus status) {
        WebPageResponse webPageResponse = new WebPageResponse();

        try {
            logger.info("Assign permission process");
            List<User> users = userService.findAll();
            List<RolePermission> permissions = userService.findAllPermissions();
            UserJSONAdapter userAdapter = new UserJSONAdapter();
            users = userAdapter.adaptUserPermissionJSONToUser(Arrays.asList(userPermissions), users, permissions);
            userService.assignPermissions(users);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("No se pudo realizar la operación.");
        }

        return webPageResponse;
    }
    @RequestMapping(value = "/new-user", method = RequestMethod.GET)
    public String  newUser(Model model) {
        return renderAddOrEditUserForm(model, new User(0l));
    }

    @RequestMapping(value = "/edit-user", method = RequestMethod.GET)
    public String  editUser(Model model,@RequestParam("id") Long id) {
        User user = null;
        try {
            user = userService.findUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return renderAddOrEditUserForm(model, user);
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public
    @ResponseBody
    WebPageResponse processNewUser(@ModelAttribute User user, BindingResult result, SessionStatus status) {
        WebPageResponse webPageResponse = new WebPageResponse();

        try {
            logger.info("new user process");
            userService.saveOrUpdate(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("No se pudo realizar la operación.");
        }

        return webPageResponse;
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    WebPageResponse processDeleteUser(@RequestParam("id-user") Long id) {
        WebPageResponse webPageResponse = new WebPageResponse();

        try {
            logger.info("deleteuser process");
            userService.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("No se pudo realizar la operación.");
        }

        return webPageResponse;
    }

    private String renderAddOrEditUserForm(Model model, User user) {
        WebPageModel webPageModel = createWebPageModel("");
        String actionButton="";
        String panelHeader="";
        if(user == null){
            webPageModel.addErrorMessage("Usuario no encontrado.");
        }else if(user.getId()==0){
            webPageModel.setTitle( "Agregar Nuevo Usuario");
            actionButton="Agregar Usuario";
            panelHeader="Formulario de alta de usuarios";
        }else{
            webPageModel.setTitle( "Editar Usuario");
            actionButton="Editar Ususario";
            panelHeader = "Formulario de modificación de usuarios";
        }
        model.addAttribute("page",webPageModel);
        model.addAttribute("user",user);
        model.addAttribute("actionButton",actionButton);
        model.addAttribute("panelHeader",panelHeader);

        return "users/new-user";
    }


    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_FABRICA;
    }
}
