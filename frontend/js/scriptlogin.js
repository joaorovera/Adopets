// Enviar email e senha para o back-end
document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch('http://localhost:8080/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    })
    .then(response => {
        if (response.ok) {
            return response.json(); // Se o back retornar um JSON
        } else {
            throw new Error('E-mail ou senha inválidos');
        }
    })
    .then(data => {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').textContent = 'Login bem-sucedido!';
        console.log('Login realizado com sucesso:', data);

        // Redireciona após 1 segundo para a tela de cadastro
        setTimeout(() => {
            window.location.href = 'telacadastro.html'; // Redireciona para a tela de cadastro
        }, 1000);
    })
    .catch(error => {
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').textContent = error.message;
        console.error('Erro no login:', error);
    });
});
