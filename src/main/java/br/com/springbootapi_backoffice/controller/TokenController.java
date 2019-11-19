package br.com.springbootapi_backoffice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbootapi_backoffice.entity.Cliente;
import br.com.springbootapi_backoffice.entity.Token;
import br.com.springbootapi_backoffice.repository.ClienteRepository;
import br.com.springbootapi_backoffice.repository.TokenRepository;
import br.com.springbootapi_backoffice.utils.*;

@RestController
public class TokenController {

    @Autowired
    private TokenRepository _tokenRepository;
    private ClienteRepository _clienteRepository;
    private JWTToken _jwttoken;

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public List<Token> Get() {
        return _tokenRepository.findAll();
    }

    @RequestMapping(value = "/token/{id}", method = RequestMethod.GET)
    public ResponseEntity<Token> GetById(@PathVariable(value = "id") long id) {
        Optional<Token> token = _tokenRepository.findById(id);
        if (token.isPresent())
            return new ResponseEntity<Token>(token.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/token/{clienteId}", method = RequestMethod.POST)
    public String Post(@PathVariable(value = "clienteId") long clienteId) {
        Optional<Cliente> cliente = _clienteRepository.findById(clienteId);
        if (cliente.isPresent()) {
            long ttl = 10 * 60 * 1000;
            String jwttoken = _jwttoken.createJWT(Long.toString(clienteId), "", "", ttl);
            Token token = new Token();
            token.setToken(jwttoken);
            token.setClienteId(clienteId);
            token.setValidated(false);
            _tokenRepository.save(token);
            return jwttoken;
        } else
            return "Cliente não encontrado";
    }

}