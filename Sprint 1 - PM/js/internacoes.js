carregarSidebar();

const tabelaInternacao = document.querySelector("main table tbody");
let registroSelecionado = null;

document.getElementById("entrada").value = dataHoje();
modal.addEventListener("close", preencherDataEntrada);
formulario.addEventListener("submit", cadastrarInternacao);
tabelaInternacao.addEventListener("click", selecionarInternacao);
document.getElementById("editar-detalhes").addEventListener("click", editarInternacao);

for (const linha of tabelaInternacao.rows) {
    const dados = {
        paciente: linha.cells[0].textContent.trim(),
        quarto: linha.cells[1].textContent.trim(),
        leito: linha.cells[2].textContent.trim(),
        entrada: dataParaFormulario(linha.cells[3].textContent.trim()),
        medico: linha.cells[4].textContent.trim(),
        status: linha.cells[5].textContent.trim(),
    };
    preencherLinhaInternacao(linha, dados);
}

function preencherLinhaInternacao(linha, dados) {
    linha.registro = dados;
    linha.replaceChildren();
    adicionarCelula(linha, dados.paciente);
    adicionarCelula(linha, dados.quarto);
    adicionarCelula(linha, dados.leito);
    adicionarCelula(linha, formatarData(dados.entrada));
    adicionarCelula(linha, dados.medico);
    adicionarStatus(linha, dados.status);

    const acoes = linha.insertCell();
    adicionarBotao(acoes, "Visualizar", "action-button");
    adicionarBotao(acoes, "Editar", "action-button");
}

function selecionarInternacao(evento) {
    const botao = evento.target.closest("button");
    if (!botao) return;

    registroSelecionado = botao.closest("tr");
    if (botao.textContent.trim() === "Editar") {
        editarInternacao();
    } else {
        visualizarInternacao();
    }
}

function visualizarInternacao() {
    const dados = registroSelecionado.registro;
    abrirDetalhes("Detalhes da internação");
    adicionarDetalhe("Paciente", dados.paciente);
    adicionarDetalhe("Quarto", dados.quarto);
    adicionarDetalhe("Leito", dados.leito);
    adicionarDetalhe("Data de entrada", formatarData(dados.entrada));
    adicionarDetalhe("Médico responsável", dados.medico);
    adicionarDetalhe("Status", dados.status);
}

function editarInternacao() {
    const dados = registroSelecionado.registro;
    document.getElementById("paciente").value = dados.paciente;
    document.getElementById("quarto").value = dados.quarto;
    document.getElementById("leito").value = dados.leito;
    document.getElementById("entrada").value = dados.entrada;
    document.getElementById("medico").value = dados.medico;
    document.getElementById("status").value = dados.status;
    prepararEdicao(registroSelecionado, "Editar internação");
}

function cadastrarInternacao(evento) {
    evento.preventDefault();
    if (!validarFormulario()) return;

    const dados = {
        paciente: document.getElementById("paciente").value.trim(),
        quarto: document.getElementById("quarto").value.trim(),
        leito: document.getElementById("leito").value.trim(),
        entrada: document.getElementById("entrada").value.trim(),
        medico: document.getElementById("medico").value.trim(),
        status: document.getElementById("status").value.trim(),
    };

    if (leitoEstaOcupado(dados.quarto, dados.leito)) {
        mostrarErro("Esse leito já está ocupado. Informe outro quarto ou leito.", document.getElementById("leito"));
        return;
    }

    if (registroEmEdicao) {
        atualizarAltaPrevista(registroEmEdicao.registro.status, dados.status);
        preencherLinhaInternacao(registroEmEdicao, dados);
        mostrarSucesso("Alterações salvas com sucesso.");
    } else {
        const linha = tabelaInternacao.insertRow();
        preencherLinhaInternacao(linha, dados);
        atualizarContadores(dados.status);
        mostrarSucesso();
    }
}

function preencherDataEntrada() {
    document.getElementById("entrada").value = dataHoje();
}

function leitoEstaOcupado(quarto, leito) {
    for (const linha of tabelaInternacao.rows) {
        // A própria internação pode manter o quarto e o leito durante a edição.
        if (linha === registroEmEdicao) continue;
        const dados = linha.registro;
        if (dados.quarto.toLowerCase() === quarto.toLowerCase() && dados.leito.toLowerCase() === leito.toLowerCase()) {
            return true;
        }
    }
    return false;
}

function atualizarContadores(status) {
    const contadores = document.querySelectorAll(".cards .card strong");
    contadores[0].textContent = Number(contadores[0].textContent) + 1;
    contadores[2].textContent = Number(contadores[2].textContent) + 1;
    if (status === "Alta prevista") {
        contadores[1].textContent = Number(contadores[1].textContent) + 1;
    }
}

function atualizarAltaPrevista(statusAnterior, statusNovo) {
    const contador = document.querySelectorAll(".cards .card strong")[1];
    let quantidade = Number(contador.textContent);
    if (statusAnterior === "Alta prevista") quantidade -= 1;
    if (statusNovo === "Alta prevista") quantidade += 1;
    contador.textContent = quantidade;
}
