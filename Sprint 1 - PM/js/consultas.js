const especialidadesMedicos = {
  "Dr. Carlos Oliveira": "Clínica Geral",
  "Dra. Maria Santos": "Cardiologia",
  "Dr. Pedro Almeida": "Ortopedia",
  "Dra. Ana Souza": "Pediatria",
}; //FRANCISCO - DADOS MOCKADOS PARA TESTE

carregarSidebar();

const tabelaConsulta = document.querySelector("main table tbody");
let registroSelecionado = null;

document.getElementById("medico").addEventListener("change", preencherEspecialidade);
formulario.addEventListener("submit", cadastrarConsulta);
tabelaConsulta.addEventListener("click", selecionarConsulta);
document.getElementById("editar-detalhes").addEventListener("click", editarConsulta);

for (const linha of tabelaConsulta.rows) {
    const dados = {
        paciente: linha.cells[0].textContent.trim(),
        medico: linha.cells[1].textContent.trim(),
        especialidade: linha.cells[2].textContent.trim(),
        data: dataParaFormulario(linha.cells[3].textContent.trim()),
        horario: linha.cells[4].textContent.trim(),
        status: linha.cells[5].textContent.trim(),
    };
    preencherLinhaConsulta(linha, dados);
}

function preencherLinhaConsulta(linha, dados) {
    linha.registro = dados;
    linha.replaceChildren();
    adicionarCelula(linha, dados.paciente);
    adicionarCelula(linha, dados.medico);
    adicionarCelula(linha, dados.especialidade);
    adicionarCelula(linha, formatarData(dados.data));
    adicionarCelula(linha, dados.horario);
    adicionarStatus(linha, dados.status);

    const acoes = linha.insertCell();
    adicionarBotao(acoes, "Visualizar", "action-button");
    adicionarBotao(acoes, "Editar", "action-button");
}

function selecionarConsulta(evento) {
    const botao = evento.target.closest("button");
    if (!botao) return;

    registroSelecionado = botao.closest("tr");
    if (botao.textContent.trim() === "Editar") {
        editarConsulta();
    } else {
        visualizarConsulta();
    }
}

function visualizarConsulta() {
    const dados = registroSelecionado.registro;
    abrirDetalhes("Detalhes da consulta");
    adicionarDetalhe("Paciente", dados.paciente);
    adicionarDetalhe("Médico", dados.medico);
    adicionarDetalhe("Especialidade", dados.especialidade);
    adicionarDetalhe("Data da consulta", formatarData(dados.data));
    adicionarDetalhe("Horário", dados.horario);
    adicionarDetalhe("Status", dados.status);
}

function editarConsulta() {
    const dados = registroSelecionado.registro;
    document.getElementById("paciente").value = dados.paciente;
    document.getElementById("medico").value = dados.medico;
    document.getElementById("especialidade").value = dados.especialidade;
    document.getElementById("data").value = dados.data;
    document.getElementById("horario").value = dados.horario;
    document.getElementById("status").value = dados.status;
    prepararEdicao(registroSelecionado, "Editar consulta");
}

function cadastrarConsulta(evento) {
    evento.preventDefault();
    if (!validarFormulario()) return;

    const dados = {
        paciente: document.getElementById("paciente").value.trim(),
        medico: document.getElementById("medico").value.trim(),
        especialidade: document.getElementById("especialidade").value.trim(),
        data: document.getElementById("data").value.trim(),
        horario: document.getElementById("horario").value.trim(),
        status: document.getElementById("status").value.trim(),
    };

    if (registroEmEdicao) {
        preencherLinhaConsulta(registroEmEdicao, dados);
        pesquisarConsulta();
        mostrarSucesso("Alterações salvas com sucesso.");
    } else {
        const linha = tabelaConsulta.insertRow();
        preencherLinhaConsulta(linha, dados);
        pesquisarConsulta();
        mostrarSucesso();
    }
}

function preencherEspecialidade() {
    const medico = document.getElementById("medico").value;
    document.getElementById("especialidade").value = especialidadesMedicos[medico] || "";
}

const pesquisaConsulta = document.querySelector(".search-box input");
pesquisaConsulta.addEventListener("input", pesquisarConsulta);

function pesquisarConsulta() {
    const texto = pesquisaConsulta.value.toLowerCase();
    for (const linha of tabelaConsulta.rows) {
        const dados = linha.registro;
        const corresponde = (dados.paciente + " " + dados.medico).toLowerCase().includes(texto);
        linha.hidden = !corresponde;
    }
}
