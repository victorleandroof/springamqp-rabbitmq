package br.com.victor.jobmq.service;

import br.com.victor.jobmq.model.Usuario;

public interface UsuarioService {

    void enviarEmail(Usuario usuario);
}
