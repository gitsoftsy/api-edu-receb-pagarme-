package br.com.softsy.pagarme.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softsy.pagarme.dto.CadastroContaDTO;
import br.com.softsy.pagarme.dto.ContaDTO;
import br.com.softsy.pagarme.infra.exception.UniqueException;
import br.com.softsy.pagarme.model.Conta;
import br.com.softsy.pagarme.repository.ContaRepository;
import br.com.softsy.pagarme.utils.ImageManager;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;

	public List<Conta> listarTudo() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public ContaDTO buscarPorId(Long id) {
		return new ContaDTO(repository.getReferenceById(id));
	}

	public String getLogoById(Long idConta) throws IOException {
		Optional<Conta> contaOptional = repository.findById(idConta);

		String imagemCarregada;
		imagemCarregada = ImageManager.buscaImagem(contaOptional.get().getLogoConta());

		if (contaOptional.isPresent()) {
			return imagemCarregada;
		} else {
			return null;
		}
	}

	@Transactional
	public CadastroContaDTO salvar(CadastroContaDTO dto) throws IOException {
		validarConta(dto.getConta());

		String base64 = "";
		Conta conta = criarContaAPartirDTO(dto);

		base64 = conta.getLogoConta();

		conta.setLogoConta(null);
		// Salvando a escola no banco de dados após tratar a imagem
		conta = repository.save(conta);

		// Manipulando a imagem e obtendo o caminho
		String caminhoIMG = ImageManager.salvaImagemConta(base64, conta.getIdConta(),"logoConta" + dto.getConta());

		// Setando a imagem diretamente no objeto escola
		conta.setLogoConta(caminhoIMG);
		dto.setLogoConta(caminhoIMG);
		dto.setIdConta(conta.getIdConta());

		atualizaDados(conta, dto);

		// Criando o DTO com os dados atualizados da escola
		CadastroContaDTO contaCriada = new CadastroContaDTO(conta);

		return contaCriada;
	}

	private Conta criarContaAPartirDTO(CadastroContaDTO dto) {
		Conta conta = new Conta();
		BeanUtils.copyProperties(dto, conta, "idConta", "ativo", "dataCadastro");
		conta.setAtivo('S');
		conta.setDataCadastro(LocalDateTime.now());
		return conta;
	}

	@Transactional
	public ContaDTO atualizar(CadastroContaDTO dto) throws IOException {
		Conta conta = repository.getReferenceById(dto.getIdConta());
		atualizaDados(conta, dto);
		return new ContaDTO(conta);
	}
	
	@Transactional
	public ContaDTO alterarImagemConta(Long idConta, String novaImagemBase64) throws IOException {
		Conta conta = repository.findById(idConta).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

	    // Verificar se já existe uma imagem e apagar do servidor
	    if (conta.getLogoConta() != null) {
	        File imagemExistente = new File(conta.getLogoConta());
	        if (imagemExistente.exists()) {
	            imagemExistente.delete();
	        }
	    }

	    // Salvar a nova imagem
	    String novoCaminhoIMG = ImageManager.salvaImagemConta(novaImagemBase64, idConta, "conta" + conta.getConta());

	    // Atualizar o caminho da imagem no banco de dados
	    conta.setLogoConta(novoCaminhoIMG);
	    repository.save(conta);

	    // Criar e retornar o DTO atualizado
	    ContaDTO contaAtualizada = new ContaDTO(conta);
	    return contaAtualizada;
	}

	@Transactional
	public void ativaDesativa(char status, Long idConta) {
		Conta conta = repository.getReferenceById(idConta);
		conta.setAtivo(status);
	}

	private void validarConta(String conta) {
		Optional<Conta> contaExistente = repository.findByConta(conta).stream().findFirst();
		if (contaExistente.isPresent()) {
			throw new UniqueException("Essa conta já existe.");
		}
	}

	private void atualizaDados(Conta destino, CadastroContaDTO origem) {
		BeanUtils.copyProperties(origem, destino, "idConta", "ativo", "dataCadastro");
	}
	

}