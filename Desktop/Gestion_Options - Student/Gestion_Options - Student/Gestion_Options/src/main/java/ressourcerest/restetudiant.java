package ressourcerest;
import entities.Etudiant;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Path("etudiant")
public class restetudiant {
    public static EtudiantBusiness etdB=new EtudiantBusiness();
    public static OptionBusiness optB = new OptionBusiness();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response addEtudiant (Etudiant etd) {
        if (etdB.addEtudiant(etd)) {
            return Response.status(201).build();
        } else {
            return Response.status(404).build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantByIdentifiant(@QueryParam("id") String o){
        List<Etudiant>l=new ArrayList<Etudiant>();
        if(o==null){ l=etdB.getAllEtudiants();}
        else {
            l = Collections.singletonList((Etudiant) etdB.getEtudiantByIdentifiant(o));
        }

        if (l.isEmpty()){return  Response.status(Response.Status.NO_CONTENT).build();}
        return Response.status(201).entity(l).build();
    }

    @GET
    @Path("option")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantsByOption(@QueryParam("code") int code) {
        Option op =optB.getOptionByCode(code);
        if (op.getCodeOption() == -1) {
            return Response.status(404).build();
        }
        else {
            List<Etudiant> l = etdB.getEtudiantsByOption(op);
            return Response.status(200).entity(l).build();
        }
    }
    @DELETE
    @Path("{id}")
    public Response deleteEtudiant(@PathParam("id") String id) {
        if (etdB.deleteEtudiant(id)) {
            return Response.status(204).build();
        } else {
            return Response.status(404).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(@PathParam("id") String id, Etudiant etudiant) {
        if (etdB.updateEtudiant(id, etudiant)) {
            return Response.status(200).build();
        } else {
            return Response.status(404).build();
        }
    }





}
