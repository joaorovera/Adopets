// Função de login (exemplo simples, adapte para seu back-end)
document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Aqui você faria a chamada para o seu back-end Java
    console.log('Tentando logar com:', username, password);

    // Simulação de sucesso
    document.getElementById('message').textContent = 'Login bem-sucedido!';
});

// Botão Voltar
document.getElementById('backButton').addEventListener('click', function () {
    window.location.href = 'index.html'; 
});
