const modal = document.getElementById("modal");
const formulario = document.getElementById("form-cadastro");
const mensagemErro = document.getElementById("cadastro-erro");
const mensagemSucesso = document.getElementById("cadastro-feedback");
let tempoMensagem;
let registroEmEdicao = null;
const tituloCadastro = document.getElementById("cadastro-titulo");
const descricaoCadastro = document.getElementById("cadastro-descricao");
const botaoSalvar = formulario.querySelector("button[type='submit']");
const tituloOriginal = tituloCadastro.textContent;
const descricaoOriginal = descricaoCadastro.textContent;
const botaoOriginal = botaoSalvar.textContent;

function abrirModal() {
  modal.showModal();
  document.body.classList.add("cadastro-aberto");
  const primeiroCampo = formulario.querySelector("input, select");
  primeiroCampo.focus();
}

function fecharModal() {
  if (!botaoSalvar.disabled) modal.close();
}

function limparFormulario() {
  formulario.reset();
  mensagemErro.hidden = true;
  registroEmEdicao = null;
  tituloCadastro.textContent = tituloOriginal;
  descricaoCadastro.textContent = descricaoOriginal;
  botaoSalvar.textContent = botaoOriginal;
  if (!document.querySelector("dialog[open]")) {
    document.body.classList.remove("cadastro-aberto");
  }
}

function fecharAoClicarFora(evento) {
  const area = modal.getBoundingClientRect();
  const foraNaHorizontal =
    evento.clientX < area.left || evento.clientX > area.right;
  const foraNaVertical =
    evento.clientY < area.top || evento.clientY > area.bottom;

  if (evento.target === modal && (foraNaHorizontal || foraNaVertical)) {
    fecharModal();
  }
}

function mostrarErro(texto, campo) {
  mensagemErro.textContent = texto;
  mensagemErro.hidden = false;
  campo?.focus();
}

function validarFormulario() {
  mensagemErro.hidden = true;
  const camposObrigatorios = formulario.querySelectorAll("[required]");

  for (const campo of camposObrigatorios) {
    if (campo.value.trim() === "") {
      mostrarErro("Preencha os campos obrigatórios.", campo);
      return false;
    }
  }

  return formulario.reportValidity();
}

function mostrarSucesso(texto = "Cadastro realizado com sucesso. O registro está disponível nesta página.") {
  fecharModal();
  mensagemSucesso.textContent = texto;
  mensagemSucesso.hidden = false;

  clearTimeout(tempoMensagem);
  tempoMensagem = setTimeout(function () {
    mensagemSucesso.hidden = true;
  }, 6000);
}

function aplicarMascaraCPF(evento) {
  const campo = evento.target;
  const numeros = campo.value.replace(/\D/g, "").slice(0, 11);
  let cpf = numeros.slice(0, 3);

  if (numeros.length > 3) cpf += "." + numeros.slice(3, 6);
  if (numeros.length > 6) cpf += "." + numeros.slice(6, 9);
  if (numeros.length > 9) cpf += "-" + numeros.slice(9, 11);

  campo.value = cpf;
}

function aplicarMascaraTelefone(evento) {
  const campo = evento.target;
  const numeros = campo.value.replace(/\D/g, "").slice(0, 11);

  if (numeros.length <= 2) {
    campo.value = numeros;
    return;
  }

  const ddd = numeros.slice(0, 2);
  const telefone = numeros.slice(2);
  const tamanhoPrefixo = telefone.length > 8 ? 5 : 4;
  let texto = "(" + ddd + ") " + telefone.slice(0, tamanhoPrefixo);

  if (telefone.length > tamanhoPrefixo) {
    texto += "-" + telefone.slice(tamanhoPrefixo);
  }

  campo.value = texto;
}

modal.addEventListener("close", limparFormulario);
modal.addEventListener("click", fecharAoClicarFora);
modal.addEventListener("cancel", function (evento) {
  if (botaoSalvar.disabled) evento.preventDefault();
});


function prepararEdicao(registro, titulo) {
    registroEmEdicao = registro;
    tituloCadastro.textContent = titulo;
    descricaoCadastro.textContent = "Atualize os campos e salve as alterações.";
    botaoSalvar.textContent = "Salvar alterações";
    fecharDetalhes();
    abrirModal();
}

const modalDetalhes = document.getElementById("modal-detalhes");
const conteudoDetalhes = document.getElementById("detalhes-conteudo");

function abrirDetalhes(titulo) {
    document.getElementById("detalhes-titulo").textContent = titulo;
    conteudoDetalhes.replaceChildren();
    modalDetalhes.showModal();
    document.body.classList.add("cadastro-aberto");
}

function adicionarDetalhe(rotulo, valor) {
    const campo = document.createElement("div");
    const nome = document.createElement("dt");
    const texto = document.createElement("dd");
    nome.textContent = rotulo;
    texto.textContent = valor || "Não informado";
    campo.appendChild(nome);
    campo.appendChild(texto);
    conteudoDetalhes.appendChild(campo);
}

function fecharDetalhes() {
    modalDetalhes.close();
}

modalDetalhes.addEventListener("close", function () {
    if (!document.querySelector("dialog[open]")) {
        document.body.classList.remove("cadastro-aberto");
    }
});

modalDetalhes.addEventListener("click", function (evento) {
    const area = modalDetalhes.getBoundingClientRect();
    const foraNaHorizontal = evento.clientX < area.left || evento.clientX > area.right;
    const foraNaVertical = evento.clientY < area.top || evento.clientY > area.bottom;
    if (evento.target === modalDetalhes && (foraNaHorizontal || foraNaVertical)) {
        fecharDetalhes();
    }
});

async function requisitarCadastro(recurso, opcoes) {
  const resposta = await fetch(recurso, opcoes).catch(() => {
    throw new Error("Não foi possível conectar ao servidor. Tente novamente.");
  });
  if (!resposta.ok) {
    const problema = await resposta.json().catch(() => null);
    throw new Error(problema?.detail || "Não foi possível concluir a operação. Confira os dados e tente novamente.");
  }
  return resposta.json();
}

async function iniciarListagem(carregar) {
  const botaoNovo = document.querySelector("main button[aria-controls='modal']");
  botaoNovo.disabled = true;
  try {
    await carregar();
    botaoNovo.disabled = false;
  } catch (erro) {
    const aviso = document.getElementById("listagem-erro");
    aviso.textContent = "Não foi possível carregar os cadastros. Recarregue a página para tentar novamente.";
    aviso.hidden = false;
    console.error(erro);
  }
}

function preencherOpcoes(campo, registros, rotulo) {
  campo.options.length = 1;
  for (const registro of registros) {
    campo.add(new Option(rotulo(registro), registro.id));
  }
}

function especialidadeProfissional(profissional) {
  if (profissional.tipo === "clinico") return "Clínica Geral";
  return profissional.especialidade || profissional.areaCirurgica || "";
}

async function salvarRegistro(recurso, dados, tabela, preencherLinha, atualizarListagem) {
  if (botaoSalvar.disabled) return;
  const linhaEmEdicao = registroEmEdicao;
  botaoSalvar.disabled = true;
  let registro;
  try {
    registro = await requisitarCadastro(linhaEmEdicao ? recurso + "/" + linhaEmEdicao.registro.id : recurso, {
      method: linhaEmEdicao ? "PUT" : "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(dados),
    });
  } catch (erro) {
    mostrarErro(erro.message);
    return;
  } finally {
    botaoSalvar.disabled = false;
  }
  preencherLinha(linhaEmEdicao || tabela.insertRow(), registro);
  atualizarListagem();
  if (linhaEmEdicao) {
    mostrarSucesso("Alterações salvas com sucesso.");
  } else {
    mostrarSucesso();
  }
}
