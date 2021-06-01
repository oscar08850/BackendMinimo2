package edu.upc.dsa.services;

import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.Credentials;
import edu.upc.dsa.models.Medalla;
import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/MINIMO2", description = "no")
@Path("")

public class AuthenticationService {

    private final GameManagerImpl gm;

    public AuthenticationService() {
        this.gm = GameManagerImpl.getInstance();
        if (gm.usuariosSize() == 0) {
            gm.registrar("toni11", "hola", "toni11@hotmail.com");
            gm.registrar("juan6", "quetal", "juan6@gmail.com");
            gm.registrar("miguel18", "adios", "miguel18@outlook.es");
            gm.registrarMedalla("escudomadera","C:\\Users\\SOBREMESA\\Documents\\Git\\Examen\\DSA_BackendProject\\public\\imagenes\\escudomadera.jpeg");
            gm.registrarMedalla("escudoro","C:\\Users\\SOBREMESA\\Documents\\Git\\Examen\\DSA_BackendProject\\public\\imagenes\\escudoro.jpeg");
            gm.registrarMedalla("escudoplata","C:\\Users\\SOBREMESA\\Documents\\Git\\Examen\\DSA_BackendProject\\public\\imagenes\\escudoplata.jpeg");
            gm.registrarMedalla("flechamadera","C:\\Users\\SOBREMESA\\Documents\\Git\\Examen\\DSA_BackendProject\\public\\imagenes\\flechamadera.jpeg");
            gm.addMedalla("toni11","escudomadera");
            gm.addMedalla("toni11","escudoro");
            gm.addMedalla("toni11","escudoplata");
        }
    }




    @GET //Ver todas las medallas
    @ApiOperation(value = "medallas", notes = "Devuelve todos las medallas del juego")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer="List"),
    })
    @Path("/Badges")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedallas() {

        List<Medalla> medallas = this.gm.getAllMedallas();
        GenericEntity<List<Medalla>> entity = new GenericEntity<List<Medalla>>(medallas) {};
        return Response.status(201).entity(entity).build();

    }

    @GET //Ver todas las medallas
    @ApiOperation(value = "medallas", notes = "Devuelve todos las medallas del juego")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer="List"),
    })
    @Path("/users/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedallasByUser() {


        List<Medalla> medallas = this.gm.getAllMedallas();
        GenericEntity<List<Medalla>> entity = new GenericEntity<List<Medalla>>(medallas) {};
        return Response.status(201).entity(entity).build();

    }




    @POST
    @ApiOperation(value = "añadimos usuario a la lista", notes = "No notes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/registrarUsuario")///addUsuario/{username}/{contraseña}/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(Usuario usuario) {

        if (usuario.getUsername()==null || usuario.getEmail()==null || usuario.getPassword()==null)  return Response.status(500).entity(usuario).build();
        gm.registrar(usuario.getUsername(), usuario.getPassword(), usuario.getEmail());
        return Response.status(201).entity(usuario).build();

    }


    @POST // Comprobar autenticación
    @ApiOperation(value = "Autenticacion", notes = "Autenticacion")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Usuario.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/iniciarSesion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response iniciarSesion(Credentials credentials) {

        if (gm.logIn(credentials.getUsername(), credentials.getPassword()))
            return Response.status(201).entity(credentials).build();

        else return Response.status(404).build();
    }

}
