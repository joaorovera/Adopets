document.addEventListener('DOMContentLoaded', function() {
    // Elementos do formulário
    const formCadastro = document.getElementById('form-cadastro-pet');
    const fotoInput = document.getElementById('foto-pet');
    const fotoPreview = document.getElementById('foto-preview');
    const modalSucesso = document.getElementById('modal-sucesso');
    const btnOk = document.getElementById('btn-ok');
    const btnVoltar = document.querySelector('.btn-voltar');

    // Preview da foto
    fotoInput.addEventListener('change', function(e) {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            
            reader.onload = function(e) {
                fotoPreview.innerHTML = '';
                const img = document.createElement('img');
                img.src = e.target.result;
                img.alt = 'Preview da foto do pet';
                fotoPreview.appendChild(img);
            }
            
            reader.readAsDataURL(file);
        }
    });

    // Envio do formulário
    formCadastro.addEventListener('submit', async function(e) {
        e.preventDefault();
        
        // Coletar dados do formulário
        const formData = new FormData();
        formData.append('name', document.getElementById('nome-pet').value);
        formData.append('type', document.getElementById('tipo-pet').value === 'dog' ? 'Cachorro' : 'Gato');
        formData.append('age', document.getElementById('idade-pet').value);
        formData.append('size', document.getElementById('porte-pet').value);
        formData.append('gender', document.getElementById('sexo-pet').value);
        formData.append('location', document.getElementById('localizacao-pet').value);
        formData.append('description', document.getElementById('descricao-pet').value);
        formData.append('status', document.getElementById('status-pet').value);
        
        // Adicionar a foto se foi selecionada
        if (fotoInput.files[0]) {
            formData.append('image', fotoInput.files[0]);
        }

        try {
            // Enviar para a API
            const response = await fetch('http://localhost:8080/pets', {
                method: 'POST',
                body: formData
            });

            if (!response.ok) {
                throw new Error('Erro ao cadastrar o pet');
            }

            // Mostrar modal de sucesso
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
        fotoPreview.innerHTML = '<i class="fas fa-camera"></i><span>Adicionar Foto</span>';
    });

    // Fechar modal ao clicar no X
    modalSucesso.querySelector('.modal-close').addEventListener('click', function() {
        modalSucesso.style.display = 'none';
        formCadastro.reset();
        fotoPreview.innerHTML = '<i class="fas fa-camera"></i><span>Adicionar Foto</span>';
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
            fotoPreview.innerHTML = '<i class="fas fa-camera"></i><span>Adicionar Foto</span>';
        }
    });
});