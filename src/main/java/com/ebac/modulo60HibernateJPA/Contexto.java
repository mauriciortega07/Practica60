package com.ebac.modulo60HibernateJPA;

import com.ebac.modulo60HibernateJPA.DTO.Direccion;
import com.ebac.modulo60HibernateJPA.DTO.Telefono;
import com.ebac.modulo60HibernateJPA.DTO.Usuario;
import com.ebac.modulo60HibernateJPA.Model.DireccionModel;
import com.ebac.modulo60HibernateJPA.Model.TelefonoModel;
import com.ebac.modulo60HibernateJPA.Model.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Optional;

public class Contexto {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexionLocalMySQL");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        UsuarioModel usuarioModel = new UsuarioModel(entityManager);
        DireccionModel direccionModel = new DireccionModel(entityManager);
        TelefonoModel telefonoModel = new TelefonoModel(entityManager);

        //USUARIOS
        Usuario usuarioMau = new Usuario();
        usuarioMau.setNombre("Mauricio");
        usuarioMau.setEdad(25);

        Usuario usuarioDavid = new Usuario();
        usuarioDavid.setNombre("David");
        usuarioDavid.setEdad(30);

        Usuario usuarioMario = new Usuario();
        usuarioMario.setNombre("Mario");
        usuarioMario.setEdad(19);

        Usuario usuarioAna = new Usuario();
        usuarioAna.setNombre("Ana");
        usuarioAna.setEdad(22);

        Usuario usuarioGustavo = new Usuario();
        usuarioGustavo.setNombre("Gustavo");
        usuarioGustavo.setEdad(38);

        Usuario usuarioGustavo2 = new Usuario();
        usuarioGustavo2.setNombre("Gustavo");
        usuarioGustavo2.setEdad(38);

        //DIRECCIONES
        Direccion direccion1 = new Direccion();
        direccion1.setIdUsuario(1);
        direccion1.setEstado("Jalisco");
        direccion1.setNumero(24);
        direccion1.setCalle("Emiliano Zapata");

        Direccion direccion2 = new Direccion();
        direccion2.setIdUsuario(2);
        direccion2.setEstado("Zacatecas ");
        direccion2.setNumero(101);
        direccion2.setCalle("Francisco Villa");

        Direccion direccion3 = new Direccion();
        direccion3.setIdUsuario(2);
        direccion3.setEstado("Zacatecas");
        direccion3.setNumero(5002);
        direccion3.setCalle("Avenida De los Insurgentes");

        Direccion direccion4 = new Direccion();
        direccion4.setIdUsuario(3);
        direccion4.setEstado("Queretaro");
        direccion4.setNumero(5);
        direccion4.setCalle("Cerrada de los pinos");

        Direccion direccion5 = new Direccion();
        direccion5.setIdUsuario(4);
        direccion5.setEstado("Guanajuato");
        direccion5.setNumero(900);
        direccion5.setCalle("Privada rosales");

        Direccion direccion6 = new Direccion();
        direccion6.setIdUsuario(4);
        direccion6.setEstado("Guanajuato");
        direccion6.setNumero(246);
        direccion6.setCalle("Avenida centra");

        Direccion direccion7 = new Direccion();
        direccion7.setIdUsuario(4);
        direccion7.setEstado("Guanajuato");
        direccion7.setNumero(246);
        direccion7.setCalle("Avenida centra");

        //TELEFONOS
        Telefono telefono1 = new Telefono();
        telefono1.setIdUsuario(1);
        telefono1.setNumero("+52 654-2121-3232");
        telefono1.setTipoTelefono("celular");

        Telefono telefono2 = new Telefono();
        telefono2.setIdTelefono(1);
        telefono2.setNumero("+52 654-9874-1549");
        telefono2.setTipoTelefono("oficina");

        Telefono telefono3 = new Telefono();
        telefono3.setIdUsuario(2);
        telefono3.setNumero("+52 748-4982-0654");
        telefono3.setTipoTelefono("celular");

        Telefono telefono4 = new Telefono();
        telefono4.setIdUsuario(3);
        telefono4.setNumero("+52 963-0201-0036");
        telefono4.setTipoTelefono("celular");

        Telefono telefono5 = new Telefono();
        telefono5.setIdUsuario(4);
        telefono5.setNumero("+52 508-0014-0809");
        telefono5.setTipoTelefono("casa");

        Telefono telefono6 = new Telefono();
        telefono6.setIdUsuario(4);
        telefono6.setNumero("+52 508-0014-0809");
        telefono6.setTipoTelefono("casa");

        //AGREGRAR USUARIOS
        usuarioModel.save(usuarioMau);
        usuarioModel.save(usuarioDavid);
        usuarioModel.save(usuarioMario);
        usuarioModel.save(usuarioAna);
        usuarioModel.save(usuarioGustavo);
        usuarioModel.save(usuarioGustavo2);



        //AGREGAR DIRECCIONES
        direccionModel.save(direccion1);
        direccionModel.save(direccion2);
        direccionModel.save(direccion3);
        direccionModel.save(direccion4);
        direccionModel.save(direccion5);
        direccionModel.save(direccion6);
        direccionModel.save(direccion7);



        //AGREGAR TELEFONOS
        telefonoModel.save(telefono1);
        telefonoModel.save(telefono2);
        telefonoModel.save(telefono3);
        telefonoModel.save(telefono4);
        telefonoModel.save(telefono5);
        telefonoModel.save(telefono6);


        //MODIFICAR REGISTROS
        Optional<Usuario> registroMau = usuarioModel.getID(1);

        registroMau.ifPresent(registro -> {
            registro.setNombre("MAuricio Ortega");
            usuarioModel.update(registro);
        });

        Optional<Usuario> usuarioActualizado = usuarioModel.getID(1);

        //ELIMINAR REGISTROS
        Optional<Telefono> telefonoAElimar = telefonoModel.getID(6);
        telefonoAElimar.ifPresent(telefonoModel::delete);

        Optional<Direccion> direaccionAEliminar = direccionModel.getID(7);
        direaccionAEliminar.ifPresent(direccionModel::delete);


    }
}
