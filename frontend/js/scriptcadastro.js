document.addEventListener('DOMContentLoaded', function() {
    // Elementos do formulário
    const formCadastro = document.getElementById('form-cadastro-pet');
    const modalSucesso = document.getElementById('modal-sucesso');
    const btnOk = document.getElementById('btn-ok');
    const btnVoltar = document.querySelector('.btn-voltar');

    // Envio do formulário
    formCadastro.addEventListener('submit', async function(e) {
        e.preventDefault();
        // Coletar dados do formulário
        const petData = {
            name: document.getElementById('nome-pet').value,
            type: document.getElementById('tipo-pet').value === 'dog' ? 'Cachorro' : 'Gato',
            age: String(document.getElementById('idade-pet').value),
            gender: document.getElementById('sexo-pet').value === 'macho' ? 'M' : 'F',
            city: document.getElementById('localizacao-pet').value,
            status: document.getElementById('status-pet').value,
            description: document.getElementById('descricao-pet').value
        };

        try {
            const response = await fetch('http://localhost:8080/pet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(petData)
            });

            if (!response.ok) {
                throw new Error('Erro ao cadastrar o pet');
            }

            modalSucesso.style.display = 'block';

        } catch (error) {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao cadastrar o pet. Por favor, tente novamente.');
        }
    });

    // Fechar modal
    btnOk.addEventListener('click', function() {
        modalSucesso.style.display = 'none';
        formCadastro.reset();
        // fotoPreview removido
    });

    // Fechar modal ao clicar no X
    modalSucesso.querySelector('.modal-close').addEventListener('click', function() {
        modalSucesso.style.display = 'none';
        formCadastro.reset();
        // fotoPreview removido
    });

    // Voltar para a página inicial
    btnVoltar.addEventListener('click', function(e) {
        e.preventDefault();
        window.location.href = 'index.html';
    });

    // Fechar modal ao clicar fora dele
    window.addEventListener('click', function(e) {
        if (e.target === modalSucesso) {
            modalSucesso.style.display = 'none';
            formCadastro.reset();
            // fotoPreview removido
        }
    });
});