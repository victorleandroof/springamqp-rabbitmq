package br.com.victor.jobmq.listener;

import br.com.victor.jobmq.model.Usuario;
import br.com.victor.jobmq.service.UsuarioService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private UsuarioService usuarioService;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(Receiver.class);

    @Autowired
    public Receiver(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void receiveMessage(byte[] mensagemBytes) {
        String message = new String(mensagemBytes);
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(message, Usuario.class);
        usuarioService.enviarEmail(usuario);
    }

}
