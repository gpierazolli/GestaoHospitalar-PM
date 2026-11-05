carregarSidebar();

const tabelaMedicos = document.querySelector("#tabelaMedicos tbody");
let medicoSelecionado = null;

document.getElementById("telefone").addEventListener("input", aplicarMascaraTelefone);
formulario.addEventListener("submit", cadastrarMedico);
tabelaMedicos.addEventListener("click", selecionarMedico);
document.getElementById("editar-detalhes").addEventListener("click", editarMedico);

for (const linha of tabelaMedicos.rows) {
    const dados = {
        nome: linha.cells[0].textContent.trim(),
        crm: linha.cells[1].textContent.trim(),
        especialidade: linha.cells[2].textContent.trim(),
        uf: "",
        telefone: "",
        email: ""
    };
    preencherLinhaMedico(linha, dados);
}

function preencherLinhaMedico(linha, dados) {
    linha.registro = dados;
    linha.replaceChildren();
    adicionarCelula(linha, dados.nome);

    let crm = dados.crm;
    if (dados.uf) crm += " / " + dados.uf;
    adicionarCelula(linha, crm);
    adicionarCelula(linha, dados.especialidade);

    const acoes = linha.insertCell();
    adicionarBotao(acoes, "Ver perfil", "action-button");
    adicionarBotao(acoes, "Editar", "action-button");
}

function selecionarMedico(evento) {
    const botao = evento.target.closest("button");
    if (!botao) return;
    medicoSelecionado = botao.closest("tr");
    if (botao.textContent === "Editar") {
        editarMedico();
    } else {
        visualizarMedico();
    }
}

function visualizarMedico() {
    const dados = medicoSelecionado.registro;
    abrirDetalhes("Perfil do médico");
    adicionarDetalhe("Nome completo", dados.nome);
    adicionarDetalhe("CRM", dados.crm);
    adicionarDetalhe("UF do CRM", dados.uf);
    adicionarDetalhe("Especialidade", dados.especialidade);
    adicionarDetalhe("Telefone", dados.telefone);
    adicionarDetalhe("E-mail", dados.email);
}

function editarMedico() {
    const dados = medicoSelecionado.registro;
    document.getElementById("nome").value = dados.nome;
    document.getElementById("crm").value = dados.crm;
    document.getElementById("uf").value = dados.uf;
    document.getElementById("especialidade").value = dados.especialidade;
    document.getElementById("telefone").value = dados.telefone;
    document.getElementById("email").value = dados.email;
    prepararEdicao(medicoSelecionado, "Editar médico");
}

function cadastrarMedico(evento) {
    evento.preventDefault();
    if (!validarFormulario()) return;

    const dados = {
        nome: document.getElementById("nome").value.trim(),
        crm: document.getElementById("crm").value,
        uf: document.getElementById("uf").value,
        especialidade: document.getElementById("especialidade").value,
        telefone: document.getElementById("telefone").value,
        email: document.getElementById("email").value.trim()
    };

    if (registroEmEdicao) {
        preencherLinhaMedico(registroEmEdicao, dados);
        pesquisarMedico();
        mostrarSucesso("Alterações salvas com sucesso.");
    } else {
        const linha = tabelaMedicos.insertRow();
        preencherLinhaMedico(linha, dados);
        pesquisarMedico();
        mostrarSucesso();
    }
}

const pesquisaMedico = document.getElementById("pesquisaMedico");
pesquisaMedico.addEventListener("input", pesquisarMedico);

function pesquisarMedico() {
    const texto = pesquisaMedico.value.toLowerCase();
    for (const linha of tabelaMedicos.rows) {
        const dados = linha.registro;
        const informacoes = dados.nome + " " + dados.crm + " " + dados.uf + " " + dados.especialidade;
        linha.hidden = !informacoes.toLowerCase().includes(texto);
    }
}
