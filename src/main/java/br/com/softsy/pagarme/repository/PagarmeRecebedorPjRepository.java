package br.com.softsy.pagarme.repository;

import java.util.Optional;

//import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import br.com.softsy.pagarme.model.PagarmeRecebedorPj;

@Repository
public interface PagarmeRecebedorPjRepository extends JpaRepository<PagarmeRecebedorPj, Long> {

	Optional<PagarmeRecebedorPj> findByCnpj(String cnpj);

	boolean existsById(Long idConta);

	boolean existsByConta_IdConta(Long idConta);

	boolean existsByCnpj(String cnpj);

	@Procedure(procedureName = "PROC_INSERIR_RECEBEDOR_PJ")
	void inserirRecebedorPJ(@Param("p_id_recebedor_temp") Long idRecebedorTemp, @Param("p_telefone") String telefone,
			@Param("p_celular") String celular, @Param("p_nome_fantasia") String nomeFantasia,
			@Param("p_razao_social") String razaoSocial, @Param("p_site") String site,
			@Param("p_id_tipo_empresa") Long idTipoEmpresa, @Param("p_dt_fundacao") String dataFundacao,
			@Param("p_receita_anual") Double receitaAnual, @Param("p_id_banco") Long idBanco,
			@Param("p_agencia") String agencia, @Param("p_dv_agencia") String dvAgencia, @Param("p_conta") String conta,
			@Param("p_dv_conta") String dvConta, @Param("p_endereco") String endereco, @Param("p_numero") String numero,
			@Param("p_complemento") String complemento, @Param("p_bairro") String bairro,
			@Param("p_cidade") String cidade, @Param("p_estado") String estado, @Param("p_cep") String cep,
			@Param("p_ponto_referencia") String pontoReferencia, @Param("p_nome_resp_legal") String nomeRespLegal,
			@Param("p_email_resp_legal") String emailRespLegal, @Param("p_cpf_resp_legal") String cpfRespLegal,
			@Param("p_dt_nasc_resp_legal") String dtNascRespLegal,
			@Param("p_nome_mae_resp_legal") String nomeMaeRespLegal, @Param("p_id_ocupacao") Long idOcupacao,
			@Param("p_renda_mensal") Double rendaMensal, @Param("p_endereco_resp_legal") String enderecoRespLegal,
			@Param("p_numero_resp_legal") String numeroRespLegal,
			@Param("p_complemento_resp_legal") String complementoRespLegal,
			@Param("p_bairro_resp_legal") String bairroRespLegal, @Param("p_cidade_resp_legal") String cidadeRespLegal,
			@Param("p_estado_resp_legal") String estadoRespLegal, @Param("p_cep_resp_legal") String cepRespLegal,
			@Param("p_ponto_referencia_resp_legal") String pontoReferenciaRespLegal,
			@Param("p_telefone_resp_legal") String telefoneRespLegal,
			@Param("p_celular_resp_legal") String celularRespLegal);

	Optional<PagarmeRecebedorPj> findById(Long id);

}
