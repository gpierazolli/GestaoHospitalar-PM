function carregarSidebar() {

    const sidebar = document.getElementById("sidebar");

    if (!sidebar) {
        return;
    }

    fetch("components/sidebar.html")
        .then(response => {

            if (!response.ok) {
                throw new Error("Não foi possível carregar a sidebar.");
            }

            return response.text();

        })
        .then(data => {

            sidebar.innerHTML = data;

            destacarPagina();

        })
        .catch(error => {

            console.error("Erro ao carregar a sidebar:", error);

        });

}

function destacarPagina() {

    const paginaAtual = window.location.pathname.split("/").pop();

    const links = document.querySelectorAll(".menu-item");

    links.forEach(link => {

        const paginaLink = link.getAttribute("href");

        if (paginaLink === paginaAtual) {

            link.classList.add("active");

        }

    });

}

function formatarData(data) {
    return data.split("-").reverse().join("/");
}

function dataHoje() {
    const hoje = new Date();
    return [hoje.getFullYear(), String(hoje.getMonth() + 1).padStart(2, "0"), String(hoje.getDate()).padStart(2, "0")].join("-");
}

// Usa textContent para mostrar o texto digitado sem interpretá-lo como HTML.
function adicionarCelula(linha, texto) {
    const celula = linha.insertCell();
    celula.textContent = texto;
    return celula;
}

function adicionarStatus(linha, texto) {
    const celula = linha.insertCell();
    const status = document.createElement("span");
    status.textContent = texto;
    status.className = "status confirmado";

    if (texto === "Aguardando" || texto === "Alta prevista") {
        status.className = "status aguardando";
    }

    if (texto === "Cancelada") {
        status.className = "status cancelada";
    }

    celula.appendChild(status);
}

function adicionarBotao(celula, texto, classe) {
    const botao = document.createElement("button");
    botao.type = "button";
    botao.textContent = texto;
    botao.className = classe;
    celula.appendChild(botao);
    return botao;
}

// Converte a data da tabela (dd/mm/aaaa) para o campo de formulário (aaaa-mm-dd).
function dataParaFormulario(data) {
    return data.split("/").reverse().join("-");
}
