package com.ifrn.sisgestaohospitalar.service;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;

public class AtendimentoRiscoDataTablesService {

	private String[] cols = { "classificacaoDeRisco.prioridade", "cidadao.sexo", "cidadao.nome",
			"profissionalDestino.nome", "tipoServicos", "status.descricao", "triagem.classificacaoDeRisco.nome" };

	public Map<String, Object> execute(AtendimentoRepository atendimentoRepository, HttpServletRequest request) {

		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		int draw = Integer.parseInt(request.getParameter("draw"));

		int current = currentPage(start, length);

		String column = columnName(request);
		Sort.Direction direction = orderBy(request);

		String search = searchBy(request);

		Pageable pageable = PageRequest.of(current, length, direction, column);

		Page<Atendimento> page = queryBy(search, atendimentoRepository, pageable);

		Map<String, Object> json = new LinkedHashMap<>();
		json.put("draw", draw);
		json.put("recordsTotal", page.getTotalElements());
		json.put("recordsFiltered", page.getTotalElements());
		json.put("data", page.getContent());
		return json;
	}

	private Page<Atendimento> queryBy(String search, AtendimentoRepository repository, Pageable pageable) {
		for (Iterator<Atendimento> iterator = repository.findAtendimentos(pageable).iterator(); iterator.hasNext();) {
			Atendimento atendimento = iterator.next();
			System.out.println(atendimento.getCidadao().getNome());
		}
		return repository.findAtendimentos(pageable);
	}

	private String searchBy(HttpServletRequest request) {
		return request.getParameter("search[value]").isEmpty() ? "" : request.getParameter("search[value]");
	}

	private Direction orderBy(HttpServletRequest request) {
		String order = request.getParameter("order[0][dir]");
		Sort.Direction sort = Sort.Direction.ASC;
		if (order.equalsIgnoreCase("desc")) {
			sort = Sort.Direction.DESC;
		}
		return sort;
	}

	private String columnName(HttpServletRequest request) {
		int iCol = Integer.parseInt(request.getParameter("order[0][column]"));
		return cols[iCol];
	}

	private int currentPage(int start, int length) {
		return start / length;
	}
}
