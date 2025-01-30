package br.com.softsy.pagarme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softsy.pagarme.model.RecebedorTemp;


@Repository
public interface RecebedorTempRepository extends JpaRepository<RecebedorTemp, Long> {
	List<RecebedorTemp> findByTipoPessoa(Character tipoPessoa);

	@Procedure(procedureName = "PROC_INSERIR_RECEBEDOR_TEMP")
	void inserirRecebedorTemp(@Param("p_ID_CONTA") Long idConta, @Param("p_ID_USUARIO") Long idUsuario,
			@Param("p_TIPO_PESSOA") String tipoPessoa, @Param("p_NOME") String nome,
			@Param("p_DOCUMENTO") String documento, @Param("p_E_MAIL") String email,
			@Param("p_TRANSF_AUTOMATICA") Character transfAutomatica,
			@Param("p_TRANSF_INTERVALO") Character transfIntervalo, @Param("p_TRANSF_DIA") Integer transfDia,
			@Param("p_ANTECIP_AUT") Character antecipAut);

}
