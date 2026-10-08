carregarSidebar();

const tabelaPaciente = document.querySelector("#tabelaPacientes tbody");
let registroSelecionado = null;

document.getElementById("nascimento").max = dataHoje();
document.getElementById("cpf").addEventListener("input", aplicarMascaraCPF);
document.getElementById("telefone").addEventListener("input", aplicarMascaraTelefone);
formulario.addEventListener("submit", cadastrarPaciente);
document.getElementById("pesquisaPaciente").addEventListener("input", pesquisarPaciente);
tabelaPaciente.addEventListener("click", selecionarPaciente);
document.getElementById("editar-detalhes").addEventListener("click", editarPaciente);

for (const linha of tabelaPaciente.rows) {
    const dados = {
        nome: linha.cells[0].textContent.trim(),
        cpf: linha.cells[1].textContent.trim(),
        nascimento: dataParaFormulario(linha.cells[2].textContent.trim()),
        telefone: linha.cells[3].textContent.trim(),
    };
    preencherLinhaPaciente(linha, dados);
}

function preencherLinhaPaciente(linha, dados) {
    linha.registro = dados;
    linha.replaceChildren();
    adicionarCelula(linha, dados.nome);
    adicionarCelula(linha, dados.cpf);
    adicionarCelula(linha, formatarData(dados.nascimento));
    adicionarCelula(linha, dados.telefone);

    const acoes = linha.insertCell();
    adicionarBotao(acoes, "Visualizar", "action-button");
    adicionarBotao(acoes, "Editar", "action-button");
}

function selecionarPaciente(evento) {
    const botao = evento.target.closest("button");
    if (!botao) return;

    registroSelecionado = botao.closest("tr");
    if (botao.textContent.trim() === "Editar") {
        editarPaciente();
    } else {
        visualizarPaciente();
    }
}

function visualizarPaciente() {
    const dados = registroSelecionado.registro;
    abrirDetalhes("Detalhes do paciente");
    adicionarDetalhe("Nome completo", dados.nome);
    adicionarDetalhe("CPF", dados.cpf);
    adicionarDetalhe("Data de nascimento", formatarData(dados.nascimento));
    adicionarDetalhe("Telefone", dados.telefone);
}

function editarPaciente() {
    const dados = registroSelecionado.registro;
    document.getElementById("nome").value = dados.nome;
    document.getElementById("cpf").value = dados.cpf;
    document.getElementById("nascimento").value = dados.nascimento;
    document.getElementById("telefone").value = dados.telefone;
    prepararEdicao(registroSelecionado, "Editar paciente");
}

function cadastrarPaciente(evento) {
    evento.preventDefault();
    if (!validarFormulario()) return;

    const dados = {
        nome: document.getElementById("nome").value.trim(),
        cpf: document.getElementById("cpf").value.trim(),
        nascimento: document.getElementById("nascimento").value.trim(),
        telefone: document.getElementById("telefone").value.trim(),
    };

    if (registroEmEdicao) {
        preencherLinhaPaciente(registroEmEdicao, dados);
        pesquisarPaciente();
        mostrarSucesso("Alterações salvas com sucesso.");
    } else {
        const linha = tabelaPaciente.insertRow();
        preencherLinhaPaciente(linha, dados);
        pesquisarPaciente();
        mostrarSucesso();
    }
}

function pesquisarPaciente() {
    const pesquisa = document.getElementById("pesquisaPaciente").value.toLowerCase();
    const linhas = document.querySelectorAll("#tabelaPacientes tbody tr");

    for (const linha of linhas) {
        const nome = linha.cells[0].textContent.toLowerCase();
        if (nome.includes(pesquisa)) {
            linha.style.display = "";
        } else {
            linha.style.display = "none";
        }
    }
}
