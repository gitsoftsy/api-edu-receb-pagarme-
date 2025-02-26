package br.com.softsy.pagarme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.model.PagarmeRecebedorPF;
import br.com.softsy.pagarme.model.RecebedorTemp;

@Repository
public interface PagarmeRecebedorPfRepository extends JpaRepository<PagarmeRecebedorPF, Long> {

	List<PagarmeRecebedorPF> findAll();

	boolean existsById(Long idConta);

	Optional<PagarmeRecebedorPF> findByCpf(String cpf);

	boolean existsByEntidadeConta_IdConta(Long idConta);

	boolean existsByCpf(String cpf);

	@Procedure(procedureName = "PROC_INSERIR_RECEBEDOR_PF")
	void inserirRecebedorPF(@Param("p_id_recebedor_temp") Long idRecebedorTemp, @Param("p_telefone") String telefone,
			@Param("p_celular") String celular, @Param("p_nome_da_mae") String nomeDaMae,
			@Param("p_dt_nascimento") String dataNascimento, @Param("p_id_ocupacao") Long idOcupacao,
			@Param("p_renda_mensal") Double rendaMensal, @Param("p_id_banco") Long idBanco,
			@Param("p_agencia") String agencia, @Param("p_dv_agencia") String dvAgencia, @Param("p_conta") String conta,
			@Param("p_dv_conta") String dvConta, @Param("p_endereco") String endereco, @Param("p_numero") String numero,
			@Param("p_complemento") String complemento, @Param("p_bairro") String bairro,
			@Param("p_cidade") String cidade, @Param("p_estado") String estado, @Param("p_cep") String cep,
			@Param("p_ponto_referencia") String pontoReferencia);

}
