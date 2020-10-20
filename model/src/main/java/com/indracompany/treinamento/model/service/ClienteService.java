package com.indracompany.treinamento.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscarClientePorCpf(String cpf) {
		if (!cpfEhValido(cpf)){
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO);
		}
		return clienteRepository.findByCpf(cpf);
	}
	
	private boolean cpfEhValido(String cpf) {
		return CpfUtil.validaCPF(cpf);
	}
	public Cliente buscarClientePorEmail(String email) {
		
		Optional<Cliente> cliente = Optional.ofNullable(clienteRepository.findByEmail(email));

		return cliente.orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ERRO_EMAIL_NAO_ENCONTRADO));

	}
	
	

}
