package br.com.victor.jobmq.service;

import br.com.victor.jobmq.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{


    @Override
    public void enviarEmail(Usuario usuario) {
        System.out.println(usuario.toString());
    }


}
