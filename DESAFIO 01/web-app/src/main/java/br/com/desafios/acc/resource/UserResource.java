package br.com.desafios.acc.resource;

import br.com.desafios.acc.dao.UserDao;
import br.com.desafios.acc.model.User;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserDao userDao = new UserDao();

    //metodo GET para listar usuarios
    @GET
    public Response getAllUsers() {
        try {
            List<User> users = userDao.listUsers();
            return Response.ok(users).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Erro ao listar usuários: " + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    //metodo POST para criar usuario
    @POST
    public Response createUser(User user) {
        try {
            userDao.addUser(user);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\": \"Usuário cadastrado com sucesso!\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    //mettodo PUT para atualizar usuario
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") int id, User user) {
        try {
            user.setId(id);
            userDao.updateUser(user);
            return Response.ok("{\"message\": \"Usuário atualizado com sucesso!\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    //metodo DELETE para excluir usurio
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        try {
            userDao.deleteUser(id);
            return Response.ok("{\"message\": \"Usuário excluído com sucesso!\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
