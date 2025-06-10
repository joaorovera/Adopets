document.addEventListener('DOMContentLoaded', function() {
    // Verificar autenticação
    const token = localStorage.getItem('adminToken');
    if (!token) {
        window.location.href = 'login.html';
        return;
    }

    // Elementos da página
    const logoutBtn = document.getElementById('logout-btn');
    const recentPetsGrid = document.getElementById('recent-pets-grid');

    // Logout
    logoutBtn.addEventListener('click', function() {
        localStorage.removeItem('adminToken');
        window.location.href = 'login.html';
    });

    // Carregar dados do dashboard
    async function loadDashboardData() {
        try {
            const response = await fetch('http://localhost:8080/admin/dashboard', {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (response.status === 401) {
                // Token inválido ou expirado
                localStorage.removeItem('adminToken');
                window.location.href = 'login.html';
                return;
            }

            if (!response.ok) {
                throw new Error('Erro ao carregar dados');
            }

            const data = await response.json();

            // Atualizar estatísticas
            document.getElementById('total-pets').textContent = data.totalPets;
            document.getElementById('urgent-pets').textContent = data.urgentPets;
            document.getElementById('adopted-pets').textContent = data.recentAdoptions;

            // Carregar pets recentes
            loadRecentPets(data.recentPets);
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao carregar dados do painel');
        }
    }

    // Carregar pets recentes
    function loadRecentPets(pets) {
        recentPetsGrid.innerHTML = '';

        if (pets.length === 0) {
            recentPetsGrid.innerHTML = '<p class="no-pets">Nenhum pet cadastrado recentemente.</p>';
            return;
        }

        pets.forEach(pet => {
            const petCard = document.createElement('div');
            petCard.className = 'pet-card';
            petCard.innerHTML = `
                <div class="pet-type-bg ${pet.type === 'Cachorro' ? 'dog-bg' : 'cat-bg'}"></div>
                <img src="${pet.imageUrl || 'default-pet.jpg'}" alt="${pet.name}" class="pet-image">
                <div class="pet-info">
                    <h3 class="pet-name">${pet.name}</h3>
                    <p class="pet-location">${pet.location}</p>
                    <span class="pet-type">${pet.type}</span>
                    ${pet.status === 'urgente' ? '<span class="pet-status">Urgente</span>' : ''}
                </div>
                <div class="pet-actions">
                    <button class="btn-edit" data-id="${pet.id}"><i class="fas fa-edit"></i></button>
                    <button class="btn-delete" data-id="${pet.id}"><i class="fas fa-trash"></i></button>
                </div>
            `;
            recentPetsGrid.appendChild(petCard);
        });
    }

    // Iniciar carregamento dos dados
    loadDashboardData();
});