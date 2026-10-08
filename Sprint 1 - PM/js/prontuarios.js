carregarSidebar();

const tabelaProntuario = document.querySelector("main table tbody");
let registroSelecionado = null;

document.getElementById("nascimento").max = dataHoje();
document.getElementById("cpf").addEventListener("input", aplicarMascaraCPF);
formulario.addEventListener("submit", salvarProntuario);
tabelaProntuario.addEventListener("click", selecionarProntuario);
document.getElementById("editar-detalhes").addEventListener("click", editarProntuario);

for (const linha of tabelaProntuario.rows) {
    const dados = {
        paciente: linha.cells[0].textContent.trim(),
        cpf: linha.cells[1].textContent.trim(),
        nascimento: dataParaFormulario(linha.cells[2].textContent.trim()),
        ultimaConsulta: dataParaFormulario(linha.cells[3].textContent.trim()),
        medico: linha.cells[4].textContent.trim(),
        observacoes: "",
    };
    preencherLinhaProntuario(linha, dados);
}

function preencherLinhaProntuario(linha, dados) {
    linha.registro = dados;
    linha.replaceChildren();
    adicionarCelula(linha, dados.paciente);
    adicionarCelula(linha, dados.cpf);
    adicionarCelula(linha, formatarData(dados.nascimento));
    adicionarCelula(linha, formatarData(dados.ultimaConsulta));
    adicionarCelula(linha, dados.medico);

    const acoes = linha.insertCell();
    adicionarBotao(acoes, "Ver prontuário", "action-button");
    adicionarBotao(acoes, "Editar", "action-button");
}

function selecionarProntuario(evento) {
    const botao = evento.target.closest("button");
    if (!botao) return;

    registroSelecionado = botao.closest("tr");
    if (botao.textContent.trim() === "Editar") {
        editarProntuario();
    } else {
        visualizarProntuario();
    }
}

function visualizarProntuario() {
    const dados = registroSelecionado.registro;
    abrirDetalhes("Prontuário do paciente");
    adicionarDetalhe("Paciente", dados.paciente);
    adicionarDetalhe("CPF", dados.cpf);
    adicionarDetalhe("Data de nascimento", formatarData(dados.nascimento));
    adicionarDetalhe("Última consulta", formatarData(dados.ultimaConsulta));
    adicionarDetalhe("Médico responsável", dados.medico);
    adicionarDetalhe("Observações", dados.observacoes || "Nenhuma observação registrada.");
}

function editarProntuario() {
    const dados = registroSelecionado.registro;
    document.getElementById("paciente").value = dados.paciente;
    document.getElementById("cpf").value = dados.cpf;
    document.getElementById("nascimento").value = dados.nascimento;
    document.getElementById("ultimaConsulta").value = dados.ultimaConsulta;
    document.getElementById("medico").value = dados.medico;
    document.getElementById("observacoes").value = dados.observacoes;
    prepararEdicao(registroSelecionado, "Editar prontuário");
}

function salvarProntuario(evento) {
    evento.preventDefault();
    if (!validarFormulario()) return;

    const dados = {
        paciente: document.getElementById("paciente").value.trim(),
        cpf: document.getElementById("cpf").value.trim(),
        nascimento: document.getElementById("nascimento").value.trim(),
        ultimaConsulta: document.getElementById("ultimaConsulta").value.trim(),
        medico: document.getElementById("medico").value.trim(),
        observacoes: document.getElementById("observacoes").value.trim(),
    };

    preencherLinhaProntuario(registroEmEdicao, dados);
    pesquisarProntuario();
    mostrarSucesso("Prontuário atualizado com sucesso.");
}

const pesquisaProntuario = document.querySelector(".search-box input");
pesquisaProntuario.addEventListener("input", pesquisarProntuario);

function pesquisarProntuario() {
    const texto = pesquisaProntuario.value.toLowerCase();
    for (const linha of tabelaProntuario.rows) {
        linha.hidden = !linha.registro.paciente.toLowerCase().includes(texto);
    }
}
